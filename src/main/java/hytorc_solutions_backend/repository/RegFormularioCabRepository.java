package hytorc_solutions_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hytorc_solutions_backend.entity.RegFormularioCab;
import hytorc_solutions_backend.projection.FormularioDetalleProjection;
import hytorc_solutions_backend.projection.FormularioListadoProjection;

@Repository
public interface RegFormularioCabRepository extends JpaRepository<RegFormularioCab, Integer> {

    Optional<RegFormularioCab> findFirstByFormulario_IdFormularioOrderByIdRegCabDesc(Integer idFormulario);

    List<RegFormularioCab> findByFormulario_IdFormularioOrderByIdRegCabDesc(Integer idFormulario);

    List<RegFormularioCab> findByUsuario_IdUsuario(Integer idUsuario);

    @Query(value = """
                SELECT
                    cab.id_reg_cab AS idCab,
                    cab.fecha AS fecha,
                    cab.estado AS estado,
                    MAX(CASE WHEN det.id_campo = 10 THEN det.valor END) AS servicio,
                    MAX(CASE WHEN det.id_campo = 13 THEN det.valor END) AS motivo
                FROM reg_formulario_cab cab
                LEFT JOIN reg_formulario_det det
                    ON det.id_reg_cab = cab.id_reg_cab
                WHERE cab.id_usuario = :idUsuario
                GROUP BY cab.id_reg_cab, cab.fecha, cab.estado
                HAVING
                    MAX(CASE WHEN det.id_campo = 10 THEN det.valor END) IS NOT NULL
                    AND TRIM(MAX(CASE WHEN det.id_campo = 10 THEN det.valor END)) <> ''
                    AND MAX(CASE WHEN det.id_campo = 13 THEN det.valor END) IS NOT NULL
                    AND TRIM(MAX(CASE WHEN det.id_campo = 13 THEN det.valor END)) <> ''
            """, nativeQuery = true)
    List<FormularioListadoProjection> listarPorUsuario(Integer idUsuario);


@Query(value = """
    SELECT 
        cab.id_reg_cab AS idRegCab,

        MAX(CASE WHEN det.id_campo = 1 THEN det.valor END) AS nombreFormulario,
        MAX(CASE WHEN det.id_campo = 2 THEN det.valor END) AS codigoFormulario,
        MAX(CASE WHEN det.id_campo = 3 THEN det.valor END) AS versionFormulario,
        MAX(CASE WHEN det.id_campo = 4 THEN det.valor END) AS fechaLlenado,

        MAX(CASE WHEN det.id_campo = 5 THEN det.valor END) AS nombreCliente,
        MAX(CASE WHEN det.id_campo = 6 THEN det.valor END) AS empresaCliente,
        MAX(CASE WHEN det.id_campo = 7 THEN det.valor END) AS telefonoCliente,
        MAX(CASE WHEN det.id_campo = 8 THEN det.valor END) AS correoCliente,
        MAX(CASE WHEN det.id_campo = 9 THEN det.valor END) AS fechaVenta,
        MAX(CASE WHEN det.id_campo = 10 THEN det.valor END) AS productoServicio,

        MAX(CASE WHEN det.id_campo = 11 THEN det.valor END) AS fechaContacto,
        MAX(CASE WHEN det.id_campo = 12 THEN det.valor END) AS medioContacto,
        MAX(CASE WHEN det.id_campo = 13 THEN det.valor END) AS motivoContacto,
        MAX(CASE WHEN det.id_campo = 14 THEN det.valor END) AS atendidoPor,
        MAX(CASE WHEN det.id_campo = 15 THEN det.valor END) AS observacionesCliente,
        MAX(CASE WHEN det.id_campo = 16 THEN det.valor END) AS accionInmediata,

        MAX(CASE WHEN det.id_campo = 17 THEN det.valor END) AS fechaSeguimiento,
        MAX(CASE WHEN det.id_campo = 18 THEN det.valor END) AS aspectosVerificados,
        MAX(CASE WHEN det.id_campo = 19 THEN det.valor END) AS hallazgos,
        MAX(CASE WHEN det.id_campo = 20 THEN det.valor END) AS accionTomada,
        MAX(CASE WHEN det.id_campo = 21 THEN det.valor END) AS observacionesSeguimiento,
        MAX(CASE WHEN det.id_campo = 22 THEN det.valor END) AS fechaCierre,

        MAX(CASE WHEN det.id_campo = 23 THEN det.valor END) AS nivelSatisfaccion,
        MAX(CASE WHEN det.id_campo = 24 THEN det.valor END) AS observacionesSatisfaccion,
        MAX(CASE WHEN det.id_campo = 25 THEN det.valor END) AS recomendaciones,
        MAX(CASE WHEN det.id_campo = 26 THEN det.valor END) AS fechaCierreFinal,

        MAX(CASE WHEN det.id_campo = 27 THEN det.valor END) AS nombreResponsable,
        MAX(CASE WHEN det.id_campo = 28 THEN det.valor END) AS cargoResponsable,
        MAX(CASE WHEN det.id_campo = 29 THEN det.valor END) AS firmaResponsable,
        MAX(CASE WHEN det.id_campo = 30 THEN det.valor END) AS fechaFirma

    FROM reg_formulario_cab cab
    LEFT JOIN reg_formulario_det det 
        ON det.id_reg_cab = cab.id_reg_cab

    WHERE cab.id_reg_cab = :idRegCab
      AND cab.id_usuario = :idUsuario

    GROUP BY cab.id_reg_cab
""", nativeQuery = true)
FormularioDetalleProjection obtenerDetalle(Integer idRegCab, Integer idUsuario);



}