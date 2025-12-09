package com.example.triptalk.domain.user.service;

import com.example.triptalk.domain.user.dto.AuthRequest;
import com.example.triptalk.domain.user.dto.AuthResponse;

public interface AuthService {

    AuthResponse.SignUpDTO signUp(AuthRequest.SignUpDTO request);

    AuthResponse.TokenDTO login(AuthRequest.LoginDTO request);

    AuthResponse.TokenDTO reissue(AuthRequest.ReissueDTO request);

    void logout(String email);
}

