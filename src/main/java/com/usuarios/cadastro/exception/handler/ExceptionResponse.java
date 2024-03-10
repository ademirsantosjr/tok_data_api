package com.usuarios.cadastro.exception.handler;

import java.util.Date;

public record ExceptionResponse(Date timestamp,
                                String message,
                                String details) {
    public ExceptionResponse(Date timestamp,
                             String message,
                             String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
