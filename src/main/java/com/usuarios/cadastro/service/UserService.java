package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final IProfileService profileService;
    private final IUserPasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        log.info("Armazenar novo usuario %s".formatted(user));
        var profile = profileService.findByName(user.getProfile().getName());
        user.setProfile(profile);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Page<User> findAll(Integer pageNumber, Integer pageSize) {
        log.info("Listar todos os usuarios de forma paginada");
        var pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
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
    public void updateByName(User user, String name) {
        log.info("Atualizar usuario '%s'".formatted(user.getName()));
        userRepository.findByName(name)
                .map(foundUser -> {
                    if (user.getName() != null) foundUser.setName(user.getName());
                    if (user.getEmail() != null) foundUser.setEmail(user.getEmail());
                    return userRepository.save(foundUser);
                })
                .orElseThrow(
                        () -> new RuntimeException(
                                "Nenhum usuario com nome '%s' foi encontrado.".formatted(name)));
    }

    @Override
    public void deleteByName(String name) {
        log.info("Remover usuario nome=%s".formatted(name));
        userRepository.findByName(name).ifPresent(userRepository::delete);
    }

    @Override
    public Page<User> findByNameOrEmail(String nameOrEmail,
                                        Integer pageNumber,
                                        Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(
                nameOrEmail, nameOrEmail, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByName(username);
    }
}
