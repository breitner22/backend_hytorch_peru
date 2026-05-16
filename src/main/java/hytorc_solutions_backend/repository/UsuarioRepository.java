package hytorc_solutions_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import hytorc_solutions_backend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

        Usuario findByUsuario(String usuario);

        List<Usuario> findByUsuarioContainingIgnoreCase(String usuario);

}
