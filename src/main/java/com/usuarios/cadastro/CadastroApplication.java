package com.usuarios.cadastro;

import com.usuarios.cadastro.entity.Profile;
import com.usuarios.cadastro.entity.User;
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

			var adm = Profile.builder()
					.name("Administrador")
					.build();
			var usuario = Profile.builder()
					.name("Usuario")
					.build();
			var supervisor = Profile.builder()
					.name("Supervisor")
					.build();

			var admPersistido = profileRepository.save(adm);
			var usuarioPersistido = profileRepository.save(usuario);
			var supervisorPersistido = profileRepository.save(supervisor);

			var admin = User
					.builder()
					.name("admin")
					.email("admin@admin.com")
					.password("{pbkdf2}9c43a06541973e760a0bc88125305d7928c763be8946d9ae49f66ceb05dbcac8bcbee4208565fa89")
					.profile(admPersistido)
					.build();

			var joao = User
					.builder()
					.name("João")
					.email("joao@toktok.com")
					.password("123456")
					.profile(usuarioPersistido)
					.build();

			var maria = User
					.builder()
					.name("Maria")
					.email("maria@exemplo.com")
					.password("654321")
					.profile(usuarioPersistido)
					.build();

			var pedro = User
					.builder()
					.name("Pedro")
					.email("pedro@top.com")
					.password("789456")
					.profile(supervisorPersistido)
					.build();

			userRepository.save(admin);
			userRepository.save(joao);
			userRepository.save(maria);
			userRepository.save(pedro);

			// Security

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
			var result = delegatingPasswordEncoder.encode("admin123");
			System.out.println("My hash " + result);
		});
	}

}
