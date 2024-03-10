package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.record.RefreshTokenRecord;
import com.usuarios.cadastro.record.UserCredentialsRecord;
import com.usuarios.cadastro.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserCredentialsRecord userCredentialsRecord) {

        var token = authService.signIn(userCredentialsRecord);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials!");
        }
        return token;
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRecord refreshTokenRecord) {
        var token = authService.refreshToken(refreshTokenRecord);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials!");
        }
        return token;
    }
}
