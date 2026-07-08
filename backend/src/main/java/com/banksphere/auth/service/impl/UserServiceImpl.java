package com.banksphere.auth.service.impl;

import com.banksphere.auth.dto.response.LoginResponse;
import com.banksphere.auth.entity.User;
import com.banksphere.auth.repository.UserRepository;
import com.banksphere.auth.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public LoginResponse getCurrentUser(@NonNull Authentication authentication) {
        String userName = ((UserDetails) Objects.requireNonNull(authentication.getPrincipal())).getUsername();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        String role = user.getRole().name();
        return new LoginResponse("", userName, role);
    }
}
