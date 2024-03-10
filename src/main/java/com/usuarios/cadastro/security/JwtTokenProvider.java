package com.usuarios.cadastro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.usuarios.cadastro.exception.InvalidJwtAuthenticationException;
import com.usuarios.cadastro.record.TokenRecord;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expired-length}")
    private String expiredLengthMillis;

    private final UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenRecord createAccessToken(String username, List<String> roles) {
        Instant now = Instant.now();
        Instant validity = now.plusMillis(Long.parseLong(expiredLengthMillis)); //plus an hour
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);

        return new TokenRecord(
                username,
                true,
                now,
                validity,
                accessToken,
                refreshToken
        );
    }

    public TokenRecord refreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) {
            refreshToken = refreshToken.substring("Bearer ".length());
        }
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return createAccessToken(username, roles);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities()
        );
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization"); //Bearer {token}
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
        }
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            return jwtVerifier.verify(token);
        } catch (TokenExpiredException tee) {
            throw new InvalidJwtAuthenticationException(tee.getMessage());
        }
    }

    private String getAccessToken(String username, List<String> roles, Instant now, Instant validity) {
        String issuerUrl =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .build()
                        .toUriString();

        return JWT
                .create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm);
    }

    private String getRefreshToken(String username, List<String> roles, Instant now) {
        Instant validity = now.plusMillis(Long.parseLong(expiredLengthMillis) * 3); //3h
        return JWT
                .create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .sign(algorithm);
    }

}
