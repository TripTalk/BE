package com.example.triptalk.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "회원가입 응답")
    public static class SignUpDTO {

        @Schema(description = "유저 ID", example = "1")
        private Long userId;

        @Schema(description = "이메일", example = "example@naver.com")
        private String email;

        @Schema(description = "닉네임", example = "잔디")
        private String nickname;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "로그인 응답")
    public static class LoginDTO {

        @Schema(description = "유저 ID", example = "1")
        private Long userId;

        @Schema(description = "이메일", example = "example@naver.com")
        private String email;

        @Schema(description = "닉네임", example = "잔디")
        private String nickname;

        @Schema(description = "프로필 이미지 URL", example = "http://example.com/image.jpg")
        private String profileImgUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "유저 정보 조회 응답")
    public static class UserInfoDTO {

        @Schema(description = "유저 ID", example = "1")
        private Long userId;

        @Schema(description = "이메일", example = "example@naver.com")
        private String email;

        @Schema(description = "닉네임", example = "잔디")
        private String nickname;

        @Schema(description = "프로필 이미지 URL", example = "http://example.com/image.jpg")
        private String profileImgUrl;

        @Schema(description = "완료된 여행 수", example = "5")
        private Integer completedTravelCount;

        @Schema(description = "계획된 여행 수", example = "3")
        private Integer plannedTravelCount;
    }
}
