package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.Profile;

public interface IProfileService {
    Profile findByName(String name);
}
