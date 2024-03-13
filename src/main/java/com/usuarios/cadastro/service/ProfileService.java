package com.usuarios.cadastro.service;

import com.usuarios.cadastro.entity.Profile;
import com.usuarios.cadastro.exception.ProfileNotFoundException;
import com.usuarios.cadastro.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile findByName(String name) {
        return profileRepository.findByName(name)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "No profile with name=%s was found".formatted(name)));
    }

    @Override
    public Collection<Profile> findAll() {
        return profileRepository.findAll();
    }
}
