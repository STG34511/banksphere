package com.banksphere.common.dto;

import java.time.LocalDateTime;

public record ErrorResponse(

        String code,

        String message,

        LocalDateTime timestamp

) {
}