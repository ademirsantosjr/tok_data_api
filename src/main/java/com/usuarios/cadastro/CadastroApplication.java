package com.usuarios.cadastro;

import com.usuarios.cadastro.entity.Perfil;
import com.usuarios.cadastro.entity.Usuario;
import com.usuarios.cadastro.repository.PerfilRepository;
import com.usuarios.cadastro.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioRepository usuarioRepository,
								  PerfilRepository perfilRepository) {
		return (args -> {

			var adm = Perfil.builder()
					.nome("Administrador")
					.build();
			var usuario = Perfil.builder()
					.nome("Usuario")
					.build();
			var supervisor = Perfil.builder()
					.nome("Supervisor")
					.build();

			var admPersistido = perfilRepository.save(adm);
			var usuarioPersistido = perfilRepository.save(usuario);
			var supervisorPersistido = perfilRepository.save(supervisor);

			var joao = Usuario
					.builder()
					.nome("João")
					.email("joao@toktok.com")
					.senha("123456")
					.perfil(admPersistido)
					.build();

			var maria = Usuario
					.builder()
					.nome("Maria")
					.email("maria@exemplo.com")
					.senha("654321")
					.perfil(usuarioPersistido)
					.build();

			var pedro = Usuario
					.builder()
					.nome("Pedro")
					.email("pedro@top.com")
					.senha("789456")
					.perfil(supervisorPersistido)
					.build();

			usuarioRepository.save(joao);
			usuarioRepository.save(maria);
			usuarioRepository.save(pedro);
		});
	}

}
