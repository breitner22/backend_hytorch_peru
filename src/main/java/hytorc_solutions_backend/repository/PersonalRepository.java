package hytorc_solutions_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hytorc_solutions_backend.entity.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    // findAll() ya está disponible automáticamente
}