package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        log.info("Armazenar novo usuario %s".formatted(user));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Collection<User> findAll() {
        log.info("Listar todos os usuarios");
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        log.info("Buscar usuario ID=%s".formatted(id));
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Nenhum usuario com ID=%s foi encontrado.".formatted(id)));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User with name=%s not found!".formatted(name)));
    }

    @Override
    public void updateById(User user, Integer id) {
        log.info("Atualizar usuario %s ID=%s".formatted(user, id));
        userRepository.findById(id)
                .map(usuarioEncontrado -> userRepository.save(user))
                .orElseThrow(
                        () -> new RuntimeException(
                                "Nenhum usuario com ID=%s foi encontrado.".formatted(id)));
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Remover usuario ID=%s".formatted(id));
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException(
                    "Nenhum usuario com ID=%s foi encontrado.".formatted(id));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByName(username);
    }
}
