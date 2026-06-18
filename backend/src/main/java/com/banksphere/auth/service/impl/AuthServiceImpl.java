package com.banksphere.auth.service.impl;

import com.banksphere.auth.dto.request.LoginRequest;
import com.banksphere.auth.dto.response.LoginResponse;
import com.banksphere.auth.entity.User;
import com.banksphere.auth.repository.UserRepository;
import com.banksphere.auth.security.JwtService;
import com.banksphere.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow();

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
}
