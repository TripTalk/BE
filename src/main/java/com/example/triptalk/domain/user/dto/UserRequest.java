package com.example.triptalk.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "로컬 회원가입 정보")
    public static class LocalSignUpDTO {

        @Schema(description = "이메일", example = "example@naver.com")
        @Email(message = "올바른 이메일 형식이어야합니다")
        @NotBlank(message = "이메일은 필수입니다")
        private String email;

        @Schema(description = "비밀번호", example = "12345678Abc@")
        @NotBlank(message = "비밀번호는 필수입니다")
        private String password;

        @Schema(description = "닉네임", example = "톡톡이")
        @NotBlank(message = "닉네임은 필수입니다")
        private String nickname;

        @Schema(description = "사진 URL", example = "http://example.com/image.jpg")
        private String profileImgUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Schema(description = "로컬 로그인 정보")
    public static class LocalLoginDTO {

        @Schema(description = "이메일", example = "example@naver.com")
        @Email(message = "올바른 이메일 형식이어야합니다")
        @NotBlank(message = "이메일은 필수입니다")
        private String email;

        @Schema(description = "비밀번호", example = "12345678Abc@")
        @NotBlank(message = "비밀번호는 필수입니다")
        private String password;
    }

}
