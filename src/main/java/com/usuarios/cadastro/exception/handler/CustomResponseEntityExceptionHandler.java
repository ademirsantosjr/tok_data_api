package com.usuarios.cadastro.exception.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.usuarios.cadastro.exception.InvalidJwtAuthenticationException;
import com.usuarios.cadastro.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleForbiddenExceptions(Exception ex,
                                                                             WebRequest request) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        request.getDescription(false)
                );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationExceptions(Exception ex,
                                                                                  WebRequest request) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        request.getDescription(false)
                );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleProfileNotFoundException(Exception ex,
                                                                                  WebRequest request) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        request.getDescription(false)
                );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
