package com.usuarios.cadastro.repository;

import com.usuarios.cadastro.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
