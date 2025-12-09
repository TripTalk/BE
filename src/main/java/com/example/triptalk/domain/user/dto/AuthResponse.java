package com.example.triptalk.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AuthResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @Schema(description = "토큰 응답")
    public static class TokenDTO {
        @Schema(description = "액세스 토큰")
        private String accessToken;

        @Schema(description = "리프레시 토큰")
        private String refreshToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @Schema(description = "회원가입 응답")
    public static class SignUpDTO {
        @Schema(description = "사용자 ID", example = "1")
        private Long userId;

        @Schema(description = "이메일", example = "user@example.com")
        private String email;

        @Schema(description = "닉네임", example = "여행자")
        private String nickName;
    }
}

