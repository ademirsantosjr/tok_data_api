package com.usuarios.cadastro;

import com.usuarios.cadastro.entity.Profile;
import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.repository.ProfileRepository;
import com.usuarios.cadastro.repository.UserRepository;
import jakarta.annotation.PostConstruct;
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

//	@Bean
//	public CommandLineRunner demo(UserRepository userRepository,
//								  ProfileRepository profileRepository) {
//		return (args -> {
//
//			var adminProfie = Profile.builder()
//					.name("ADMIN")
//					.build();
//			var userProfile = Profile.builder()
//					.name("USER")
//					.build();
//
//			var persistedAdminProfile = profileRepository.save(adminProfie);
//			var persistedUserProfile = profileRepository.save(userProfile);
//
//			var admin = User
//					.builder()
//					.name("admin")
//					.email("admin@admin.com")
//					.password(encodePass("4657"))
//					.profile(persistedAdminProfile)
//					.build();
//
//			var joao = User
//					.builder()
//					.name("João")
//					.email("joao@toktok.com")
//					.password("123456")
//					.profile(persistedUserProfile)
//					.build();
//
//			var maria = User
//					.builder()
//					.name("Maria")
//					.email("maria@exemplo.com")
//					.password("654321")
//					.profile(persistedUserProfile)
//					.build();
//
//			var pedro = User
//					.builder()
//					.name("Pedro")
//					.email("pedro@top.com")
//					.password("789456")
//					.profile(persistedUserProfile)
//					.build();
//
//			userRepository.save(admin);
//			userRepository.save(joao);
//			userRepository.save(maria);
//			userRepository.save(pedro);
//
//		});
//	}

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
