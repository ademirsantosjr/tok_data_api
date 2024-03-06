package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.Usuario;
import com.usuarios.cadastro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        log.info("Armazenar novo usuario %s".formatted(usuario));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Collection<Usuario> findAll() {
        log.info("Listar todos os usuarios");
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        log.info("Buscar usuario ID=%s".formatted(id));
        return usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Nenhum usuario com ID=%s foi encontrado.".formatted(id)));
    }

    @Override
    public void updateById(Usuario usuario, Integer id) {
        log.info("Atualizar usuario %s ID=%s".formatted(usuario, id));
        usuarioRepository.findById(id)
                .map(usuarioEncontrado -> usuarioRepository.save(usuario))
                .orElseThrow(
                        () -> new RuntimeException(
                                "Nenhum usuario com ID=%s foi encontrado.".formatted(id)));
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Remover usuario ID=%s".formatted(id));
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException(
                    "Nenhum usuario com ID=%s foi encontrado.".formatted(id));
        }
    }
}
