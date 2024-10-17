package com.example.event_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleTokenException(NotFoundException ex) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex.getMessage(), null);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex.getMessage(), null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse.ErrorDetail> errorDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse.ErrorDetail(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Validation failed", errorDetails);
    }

    // Helper method to create ResponseEntity with ErrorResponse
    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus status, String message, List<ErrorResponse.ErrorDetail> errors) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                LocalDateTime.now(),
                errors
        );

        return new ResponseEntity<>(errorResponse, status);
    }
}

