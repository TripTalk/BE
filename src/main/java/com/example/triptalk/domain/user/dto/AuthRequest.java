package com.example.triptalk.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원가입 요청")
    public static class SignUpDTO {
        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @Schema(description = "비밀번호", example = "password123")
        private String password;

        @Schema(description = "닉네임", example = "여행자")
        private String nickName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "로그인 요청")
    public static class LoginDTO {
        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @Schema(description = "비밀번호", example = "password123")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "토큰 재발급 요청")
    public static class ReissueDTO {
        @Schema(description = "리프레시 토큰")
        private String refreshToken;
    }
}

