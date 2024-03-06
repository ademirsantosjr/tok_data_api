package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.entity.Usuario;
import com.usuarios.cadastro.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        var novoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok().body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<Collection<Usuario>> findAll() {
        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatedById(@RequestBody Usuario usuario,
                                            @PathVariable Integer id) {
        usuarioService.updateById(usuario, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
