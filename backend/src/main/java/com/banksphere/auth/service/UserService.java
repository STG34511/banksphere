package com.banksphere.auth.service;

import com.banksphere.auth.dto.response.LoginResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

    LoginResponse getCurrentUser(Authentication authentication);
}

