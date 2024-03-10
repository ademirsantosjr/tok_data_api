package com.usuarios.cadastro.record;

import jakarta.validation.constraints.NotBlank;

public record UserCredentialsRecord(@NotBlank String username,
                                    @NotBlank String password) {
    public UserCredentialsRecord(@NotBlank String username,
                                 @NotBlank String password) {
        this.username = username;
        this.password = password;
    }
}
