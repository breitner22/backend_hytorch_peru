package hytorc_solutions_backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.service.UsuarioService;

@SpringBootApplication
public class HytorcSolutionsBackendApplication {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(HytorcSolutionsBackendApplication.class, args);
	}

	@Bean
public CommandLineRunner encriptarUsuariosExistentes() {
    return args -> {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        for (Usuario u : usuarios) {
            if (!u.getPassword().startsWith("$2a$")) {

                usuarioService.guardarUsuarioConHash(u);
                System.out.println("Usuario " + u.getUsuario() + " actualizado con hash.");
            }
        }
    };
}

	
}
