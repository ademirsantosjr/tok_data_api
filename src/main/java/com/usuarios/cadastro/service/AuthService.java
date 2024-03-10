package com.usuarios.cadastro.service;

import com.usuarios.cadastro.record.RefreshTokenRecord;
import com.usuarios.cadastro.record.TokenRecord;
import com.usuarios.cadastro.record.UserCredentialsRecord;
import com.usuarios.cadastro.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public ResponseEntity<TokenRecord> signIn(UserCredentialsRecord userCredentialsRecord) {
        try {
            var username = userCredentialsRecord.username();
            var password = userCredentialsRecord.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            var user = userService.findByName(username);
            var roles = List.of(user.getProfile().getName());
            var tokenResponse = jwtTokenProvider.createAccessToken(username, roles);
            return ResponseEntity.ok().body(tokenResponse);
        } catch (Exception e) {
            log.error("Login Error: %s".formatted(e.getMessage()));
            throw new BadCredentialsException("Bad Credentials!");
        }
    }

    public ResponseEntity<TokenRecord> refreshToken(RefreshTokenRecord refreshTokenRecord) {
        var user = userService.findByName(refreshTokenRecord.username());
        var tokenResponse = jwtTokenProvider.refreshToken(refreshTokenRecord.refreshToken());
        return ResponseEntity.ok().body(tokenResponse);
    }
}
