package com.banksphere.auth.dto.response;

public record LoginResponse(

        String accessToken,

        String username,

        String role

) {
}