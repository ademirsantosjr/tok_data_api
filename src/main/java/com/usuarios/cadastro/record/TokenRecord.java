package com.usuarios.cadastro.record;

import java.time.Instant;
import java.util.Date;

public record TokenRecord(String username,
                          Boolean authenticated,
                          Instant created,
                          Instant expiration,
                          String accessToken,
                          String refreshToken) {
    public TokenRecord(String username,
                       Boolean authenticated,
                       Instant created,
                       Instant expiration,
                       String accessToken,
                       String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
