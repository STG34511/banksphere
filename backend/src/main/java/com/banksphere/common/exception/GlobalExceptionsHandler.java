package com.banksphere.common.exception;

import com.banksphere.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ApplicationOnboardingException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(
            ApplicationOnboardingException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                        new ErrorResponse(
                                "APPLICATION_ERROR",
                                ex.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ErrorResponse("APPLICATION_ERROR", ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
