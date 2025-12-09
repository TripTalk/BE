package com.example.triptalk.domain.user.converter;

import com.example.triptalk.domain.user.dto.AuthRequest;
import com.example.triptalk.domain.user.dto.AuthResponse;
import com.example.triptalk.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public User toUser(AuthRequest.SignUpDTO request, String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickName(request.getNickName())
                .completedTravelCount(0)
                .plannedTravelCount(0)
                .build();
    }

    public AuthResponse.SignUpDTO toSignUpResponse(User user) {
        return AuthResponse.SignUpDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .build();
    }

    public AuthResponse.TokenDTO toTokenResponse(String accessToken, String refreshToken) {
        return AuthResponse.TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

