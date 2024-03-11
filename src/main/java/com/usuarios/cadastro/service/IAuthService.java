package com.usuarios.cadastro.service;

import com.usuarios.cadastro.record.TokenRecord;
import com.usuarios.cadastro.record.UserCredentialsRecord;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<TokenRecord> signIn(UserCredentialsRecord userCredentialsRecord);
}
