package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IUsuarioService {
    Usuario save(Usuario usuario);
    Collection<Usuario> findAll();
    Usuario findById(Integer id);
    void updateById(Usuario usuario, Integer id);
    void deleteById(Integer id);
}
