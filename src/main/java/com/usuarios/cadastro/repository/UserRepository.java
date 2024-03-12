package com.usuarios.cadastro.repository;

import com.usuarios.cadastro.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    @Override
    Page<User> findAll(Pageable pageable);
}
