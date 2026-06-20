package com.banksphere.auth.controller;

import com.banksphere.auth.dto.request.LoginRequest;
import com.banksphere.auth.dto.response.LoginResponse;
import com.banksphere.auth.service.AuthService;
import com.banksphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return new ResponseEntity<>(new ApiResponse<>(true, "Logged In successfully", response), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<LoginResponse>> getCurrentUser(
            Authentication authentication
    ) {
        LoginResponse response = authService.getCurrentUser(authentication);
        return new ResponseEntity<>(new ApiResponse<>(true, "Valid User", response), HttpStatus.OK);
    }

}
