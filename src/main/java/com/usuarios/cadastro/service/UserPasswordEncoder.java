package com.usuarios.cadastro.service;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserPasswordEncoder implements IUserPasswordEncoder {

    @Override
    public String encode(String rawPassword) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder =
                new Pbkdf2PasswordEncoder(
                        "",
                        8,
                        185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
                );
        encoders.put("pbkdf2", pbkdf2PasswordEncoder);
        DelegatingPasswordEncoder delegatingPasswordEncoder =
                new DelegatingPasswordEncoder(
                        "pbkdf2",
                        encoders
                );
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        return delegatingPasswordEncoder.encode(rawPassword);
    }
}
