package com.example.triptalk.domain.user.controller;

import com.example.triptalk.domain.user.dto.AuthRequest;
import com.example.triptalk.domain.user.dto.AuthResponse;
import com.example.triptalk.domain.user.service.AuthService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "이메일, 비밀번호, 닉네임으로 회원가입합니다.")
    @PostMapping("/signup")
    public ApiResponse<AuthResponse.SignUpDTO> signUp(@RequestBody AuthRequest.SignUpDTO request) {
        return ApiResponse.onSuccess(authService.signUp(request));
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하여 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ApiResponse<AuthResponse.TokenDTO> login(@RequestBody AuthRequest.LoginDTO request) {
        return ApiResponse.onSuccess(authService.login(request));
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token으로 Access Token을 재발급받습니다.")
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse.TokenDTO> reissue(@RequestBody AuthRequest.ReissueDTO request) {
        return ApiResponse.onSuccess(authService.reissue(request));
    }

    @Operation(summary = "로그아웃", description = "로그아웃하여 Refresh Token을 삭제합니다.")
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {
        authService.logout(userDetails.getUsername());
        return ApiResponse.onSuccess(null);
    }
}

