package com.usuarios.cadastro.repository;

import com.usuarios.cadastro.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByName(String name);
}
