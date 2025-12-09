package com.example.triptalk.domain.user.service;

import com.example.triptalk.domain.user.converter.AuthConverter;
import com.example.triptalk.domain.user.dto.AuthRequest;
import com.example.triptalk.domain.user.dto.AuthResponse;
import com.example.triptalk.domain.user.entity.RefreshToken;
import com.example.triptalk.domain.user.entity.User;
import com.example.triptalk.domain.user.repository.RefreshTokenRepository;
import com.example.triptalk.domain.user.repository.UserRepository;
import com.example.triptalk.global.apiPayload.code.status.ErrorStatus;
import com.example.triptalk.global.apiPayload.exception.handler.ErrorHandler;
import com.example.triptalk.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthConverter authConverter;

    @Override
    public AuthResponse.SignUpDTO signUp(AuthRequest.SignUpDTO request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ErrorHandler(ErrorStatus.AUTH_DUPLICATE_EMAIL);
        }

        // 비밀번호 인코딩 후 유저 생성
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = authConverter.toUser(request, encodedPassword);
        User savedUser = userRepository.save(user);

        return authConverter.toSignUpResponse(savedUser);
    }

    @Override
    public AuthResponse.TokenDTO login(AuthRequest.LoginDTO request) {
        // 유저 조회
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ErrorHandler(ErrorStatus.AUTH_INVALID_PASSWORD);
        }

        // 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        // RefreshToken 저장
        saveRefreshToken(user.getId(), refreshToken);

        return authConverter.toTokenResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse.TokenDTO reissue(AuthRequest.ReissueDTO request) {
        // RefreshToken 유효성 검증
        if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
            throw new ErrorHandler(ErrorStatus.AUTH_INVALID_TOKEN);
        }

        // DB에서 RefreshToken 조회
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.AUTH_TOKEN_NOT_FOUND));

        // 만료 여부 확인
        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new ErrorHandler(ErrorStatus.AUTH_EXPIRED_TOKEN);
        }

        // 유저 조회
        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        // 새로운 토큰 생성
        String newAccessToken = jwtTokenProvider.createAccessToken(user.getEmail());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        // 기존 RefreshToken 삭제 후 새로운 토큰 저장
        refreshTokenRepository.delete(refreshToken);
        saveRefreshToken(user.getId(), newRefreshToken);

        return authConverter.toTokenResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void logout(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));
        refreshTokenRepository.deleteByUserId(user.getId());
    }

    private void saveRefreshToken(Long userId, String token) {
        // 기존 토큰 삭제
        refreshTokenRepository.deleteByUserId(userId);

        // 새 토큰 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .userId(userId)
                .expiryDate(LocalDateTime.now().plusSeconds(
                        jwtTokenProvider.getRefreshTokenExpiration() / 1000))
                .build();

        refreshTokenRepository.save(refreshToken);
    }
}

