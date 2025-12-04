package com.example.triptalk.domain.user.service;

import com.example.triptalk.domain.user.dto.UserResponse;

public interface UserService {

    // 마이페이지 조회
    UserResponse.UserInfoDTO getUserInfo(Long userId);

}

