package com.example.triptalk.global.security;

import com.example.triptalk.domain.user.entity.User;
import com.example.triptalk.domain.user.repository.UserRepository;
import com.example.triptalk.global.apiPayload.code.status.ErrorStatus;
import com.example.triptalk.global.apiPayload.exception.handler.ErrorHandler;
import com.example.triptalk.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    /**
     * HTTP 요청에서 JWT 토큰을 추출하여 사용자 ID를 반환
     * @param request HTTP 요청
     * @return 사용자 ID
     */
    public Long getUserIdFromRequest(HttpServletRequest request) {
        String token = resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            throw new ErrorHandler(ErrorStatus._UNAUTHORIZED);
        }

        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        return user.getId();
    }

    /**
     * HTTP 요청 헤더에서 JWT 토큰 추출
     * @param request HTTP 요청
     * @return JWT 토큰 (없으면 null)
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

