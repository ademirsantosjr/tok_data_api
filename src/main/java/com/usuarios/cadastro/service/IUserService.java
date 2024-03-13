package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.User;
import org.springframework.data.domain.Page;

public interface IUserService {
    User save(User user);
    Page<User> findAll(Integer pageNumber, Integer pageSize);
    User findById(Integer id);
    User findByName(String name);
    void updateById(User user, Integer id);
    void deleteByName(String name);
    Page<User> findByNameOrEmail(String name, Integer pageNumber, Integer pageSize);
}
