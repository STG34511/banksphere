package com.banksphere.auth.service;

import com.banksphere.auth.dto.request.LoginRequest;
import com.banksphere.auth.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

public interface AuthService {
    LoginResponse login(@Valid LoginRequest loginRequest);

    LoginResponse getCurrentUser(Authentication authentication);
}
