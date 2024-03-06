package com.usuarios.cadastro.repository;

import com.usuarios.cadastro.entity.Perfil;
import com.usuarios.cadastro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
