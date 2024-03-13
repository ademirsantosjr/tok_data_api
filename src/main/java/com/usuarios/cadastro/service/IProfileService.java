package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.Profile;

import java.util.Collection;

public interface IProfileService {
    Profile findByName(String name);
    Collection<Profile> findAll();
}
