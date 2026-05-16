package hytorc_solutions_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hytorc_solutions_backend.entity.Area;


public interface AreaRepository extends JpaRepository<Area, Integer> {
}