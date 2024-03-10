package com.usuarios.cadastro.exception.handler;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp,
                                String message,
                                String details) {
    public ExceptionResponse(LocalDateTime timestamp,
                             String message,
                             String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
