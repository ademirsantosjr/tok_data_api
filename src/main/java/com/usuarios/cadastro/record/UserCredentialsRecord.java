package com.usuarios.cadastro.record;

public record UserCredentialsRecord(String username, String password) {
    public UserCredentialsRecord(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
