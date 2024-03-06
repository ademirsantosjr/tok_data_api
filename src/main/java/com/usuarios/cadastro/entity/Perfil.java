package com.usuarios.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "perfis")
public class Perfil implements Serializable {

    @Serial
    private static final long serialVersionUID = -7323585998259644836L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Usuario> usuarios;
}
