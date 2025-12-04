package com.example.triptalk.domain.user.service;

import com.example.triptalk.domain.user.converter.UserConverter;
import com.example.triptalk.domain.user.dto.UserResponse;
import com.example.triptalk.domain.user.entity.User;
import com.example.triptalk.domain.user.repository.UserRepository;
import com.example.triptalk.global.apiPayload.code.status.ErrorStatus;
import com.example.triptalk.global.apiPayload.exception.GeneralException;
import com.example.triptalk.global.apiPayload.exception.handler.ErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserResponse.UserInfoDTO getUserInfo(Long userId) {

        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 응답 DTO 변환 및 반환
        return userConverter.toUserInfoDTO(user);
    }
}

