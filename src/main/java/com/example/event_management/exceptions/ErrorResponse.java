package com.example.event_management.exceptions;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private List<ErrorDetail> errors;

    public ErrorResponse( int status, String message, LocalDateTime timestamp, List<ErrorDetail> errors) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    @Data
    public static class ErrorDetail {
        private String field;
        private String message;

        public ErrorDetail(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }
}

