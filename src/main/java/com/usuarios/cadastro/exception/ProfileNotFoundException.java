package com.usuarios.cadastro.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String msg) {
        super(msg);
    }
}
