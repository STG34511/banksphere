package com.banksphere.common.dto;

public record ApiResponse<T>(

        boolean success,

        String message,

        T data

) {
}
