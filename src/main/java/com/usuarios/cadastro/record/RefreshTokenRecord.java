package com.usuarios.cadastro.record;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRecord(@NotBlank String username,
                                 @NotBlank String refreshToken) {
}
