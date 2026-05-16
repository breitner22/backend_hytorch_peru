package hytorc_solutions_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hytorc_solutions_backend.entity.RegFormularioDet;

@Repository
public interface RegFormularioDetRepository extends JpaRepository<RegFormularioDet, Integer> {

    List<RegFormularioDet> findByRegFormularioCabIdRegCab(Integer idRegCab);

    @Query("""
        SELECT c.nombreCampo, d.valor
        FROM RegFormularioDet d
        JOIN d.campo c
        WHERE d.regFormularioCab.idRegCab = :idRegCab
        AND c.idCampo > 3
        ORDER BY c.idCampo
    """)
    List<Object[]> obtenerCamposPorCab(@Param("idRegCab") Integer idRegCab);


}