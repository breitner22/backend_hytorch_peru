package hytorc_solutions_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hytorc_solutions_backend.entity.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer> {
}
