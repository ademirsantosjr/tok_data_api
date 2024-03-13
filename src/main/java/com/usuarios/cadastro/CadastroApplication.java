package com.usuarios.cadastro;

import com.usuarios.cadastro.repository.ProfileRepository;
import com.usuarios.cadastro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository,
								  ProfileRepository profileRepository) {
		return (args -> {
			userRepository.findAll()
					.forEach(user -> {
						user.setPassword(encodePass(user.getName()));
						userRepository.save(user);
					});
		});
	}

	private static String encodePass(String rawPassword) {
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
