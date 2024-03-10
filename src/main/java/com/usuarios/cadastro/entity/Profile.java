package com.usuarios.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profiles")
public class Profile implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = -7323585998259644836L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Collection<User> users;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
