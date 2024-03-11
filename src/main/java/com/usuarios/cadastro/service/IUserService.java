package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.User;

import java.util.Collection;

public interface IUserService {
    User save(User user);
    Collection<User> findAll();
    User findById(Integer id);
    User findByName(String name);
    void updateById(User user, Integer id);
    void deleteById(Integer id);
}
