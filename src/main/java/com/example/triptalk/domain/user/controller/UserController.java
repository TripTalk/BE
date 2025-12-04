package com.example.triptalk.domain.user.controller;

import com.example.triptalk.domain.user.dto.UserResponse;
import com.example.triptalk.domain.user.service.UserService;
import com.example.triptalk.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "유저 API", description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "마이페이지 유저 정보 조회 API", description = "현재 로그인한 사용자의 마이페이지 정보를 조회하는 API입니다.")
    public ApiResponse<UserResponse.UserInfoDTO> getUserInfo(
            @Parameter(description = "유저 ID", required = true, example = "1")
            @PathVariable Long userId
    ) {
        // 임시 유저로 설정
        UserResponse.UserInfoDTO response = userService.getUserInfo(1L);
        return ApiResponse.onSuccess(response);
    }
}

