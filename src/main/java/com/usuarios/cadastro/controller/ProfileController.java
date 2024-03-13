package com.usuarios.cadastro.controller;

import com.usuarios.cadastro.entity.Profile;
import com.usuarios.cadastro.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final IProfileService profileService;

    @GetMapping
    public ResponseEntity<Collection<String>> findAll() {
        var profiles = profileService.findAll().stream()
                .map(Profile::getName)
                .toList();
        return ResponseEntity.ok().body(profiles);
    }
}
