package com.usuarios.cadastro.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usuarios.cadastro.record.validgroup.CreateUserRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecord(Integer id,
                         @NotBlank
                         String name,
                         @NotBlank
                         String email,
                         @NotBlank(groups = CreateUserRecord.class)
                         @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                         String password,
                         @NotNull(groups = CreateUserRecord.class)
                         String profile) {
}
