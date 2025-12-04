package com.example.triptalk.domain.user.converter;

import com.example.triptalk.domain.user.dto.UserResponse;
import com.example.triptalk.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse.UserInfoDTO toUserInfoDTO(User user) {
        return UserResponse.UserInfoDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickName())
                .profileImgUrl(user.getProfileImgUrl())
                .completedTravelCount(user.getCompletedTravelCount())
                .plannedTravelCount(user.getPlannedTravelCount())
                .build();
    }
}

