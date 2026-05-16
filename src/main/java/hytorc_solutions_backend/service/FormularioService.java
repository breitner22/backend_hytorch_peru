package hytorc_solutions_backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hytorc_solutions_backend.dto.FiltroFormularioDTO;
import hytorc_solutions_backend.dto.FormularioListadoDTO;
import hytorc_solutions_backend.entity.CampoFormulario;
import hytorc_solutions_backend.entity.Formulario;
import hytorc_solutions_backend.entity.RegFormularioCab;
import hytorc_solutions_backend.entity.RegFormularioDet;
import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.projection.FormularioDetalleProjection;
import hytorc_solutions_backend.repository.CampoFormularioRepository;
import hytorc_solutions_backend.repository.FormularioRepository;
import hytorc_solutions_backend.repository.RegFormularioCabRepository;
import hytorc_solutions_backend.repository.RegFormularioDetRepository;
import hytorc_solutions_backend.repository.UsuarioRepository;

@Service
public class FormularioService {

    @Autowired
    private RegFormularioCabRepository cabRepository;

    @Autowired
    private RegFormularioDetRepository detRepository;

    @Autowired
    private CampoFormularioRepository campoRepository;

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RegFormularioCab guardarFormulario(Integer idFormulario, Integer idUsuario, Map<Integer, String> valores) {

        Formulario formulario = formularioRepository.findById(idFormulario)
                .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RegFormularioCab cab = new RegFormularioCab();
        cab.setFormulario(formulario);
        cab.setUsuario(usuario);
        cab.setVersion(1);

        cab = cabRepository.save(cab);

        for (Map.Entry<Integer, String> entry : valores.entrySet()) {
            Integer idCampo = entry.getKey();
            String valor = entry.getValue();

            CampoFormulario campo = campoRepository.findById(idCampo)
                    .orElseThrow(() -> new RuntimeException("Campo no encontrado"));

            RegFormularioDet det = new RegFormularioDet();
            det.setValor(valor);
            det.setRegFormularioCab(cab);
            det.setCampo(campo);

            detRepository.save(det);
        }

        return cab;
    }

    public Map<Integer, String> obtenerDetallePorCabId(RegFormularioCab cab, List<Integer> campos) {
        Map<Integer, String> resultado = new HashMap<>();

        if (cab == null)
            return resultado; // si no hay registro CAB

        detRepository.findByRegFormularioCabIdRegCab(cab.getIdRegCab()).forEach(det -> {
            Integer idCampo = det.getCampo().getIdCampo();

            if (campos.contains(idCampo)) {
                resultado.put(idCampo, det.getValor());
            }
        });

        return resultado;
    }

   /*  public Map<Integer, String> obtenerUltimosCampos(Integer idFormulario, List<Integer> campos) {

        RegFormularioCab cab = cabRepository
                .findFirstByFormulario_IdFormularioOrderByIdRegCabDesc(idFormulario)
                .orElse(null);

        if (cab == null)
            return new HashMap<>();

        return obtenerDetallePorCabId(cab, campos);
    } */

public Map<Integer, String> obtenerUltimosCampos(Integer idFormulario, List<Integer> campos) {

    Map<Integer, String> resultado = new HashMap<>();

    List<RegFormularioCab> lista = cabRepository
            .findByFormulario_IdFormularioOrderByIdRegCabDesc(idFormulario);

    for (RegFormularioCab cab : lista) {

        Map<Integer, String> temp =
                obtenerDetallePorCabId(cab, campos);

        boolean completo = campos.stream()
                .allMatch(temp::containsKey);

        if (completo) {
            return temp;
        }
        resultado.putAll(temp);
    }

    return resultado;
}


    public RegFormularioCab registrarFormulario(Integer idFormulario, Integer idUsuario, Map<Integer, String> valores) {

        Formulario formulario = formularioRepository.findById(idFormulario)
                .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RegFormularioCab cab = new RegFormularioCab();
        cab.setFormulario(formulario);
        cab.setUsuario(usuario);
        cab.setVersion(1);
        cab.setEstado("P");

        cab = cabRepository.save(cab);

        for (Map.Entry<Integer, String> entry : valores.entrySet()) {

            CampoFormulario campo = campoRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Campo no encontrado"));

            RegFormularioDet det = new RegFormularioDet();
            det.setValor(entry.getValue());
            det.setRegFormularioCab(cab);
            det.setCampo(campo);

            detRepository.save(det);
        }

        return cab;
    }

    public List<FormularioListadoDTO> listarPorUsuario(Integer idUsuario) {

        List<RegFormularioCab> lista = cabRepository.findByUsuario_IdUsuario(idUsuario);

        return lista.stream().map(cab -> {

            String servicio = null;
            String motivo = null;

            List<RegFormularioDet> detalles = detRepository
                    .findByRegFormularioCabIdRegCab(cab.getIdRegCab());

            for (RegFormularioDet det : detalles) {

                Integer idCampo = det.getCampo().getIdCampo();

                if (idCampo == 10) {
                    servicio = det.getValor();
                }

                if (idCampo == 13) {
                    motivo = det.getValor();
                }
            }

            if (servicio == null || servicio.trim().isEmpty()
                    || motivo == null || motivo.trim().isEmpty()) {
                return null;
            }

            return new FormularioListadoDTO(
                    cab.getIdRegCab(),
                    /* cab.getFecha() != null ? cab.getFecha().toLocalDate().toString() : "", */
                    cab.getFecha() != null
                            ? cab.getFecha().toLocalDate()
                                    .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                            : "",
                    cab.getEstado(),
                    servicio,
                    motivo);

        })
                .filter(dto -> dto != null)
                .toList();
    }


    public List<FormularioListadoDTO> filtrar(Integer idUsuario, String estado, String fecha) {

    List<RegFormularioCab> lista = cabRepository.findByUsuario_IdUsuario(idUsuario);

    return lista.stream().filter(cab -> {

        boolean cumple = true;

        if (estado != null && !estado.isEmpty()) {
            cumple = cumple && estado.equalsIgnoreCase(cab.getEstado());
        }

      if (fecha != null && !fecha.isEmpty()) {

    try {

        java.time.LocalDate fechaFiltro = java.time.LocalDate.parse(
                fecha,
                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        java.time.LocalDate fechaCab = cab.getFecha() != null
                ? cab.getFecha().toLocalDate()
                : null;

        cumple = cumple && fechaFiltro.equals(fechaCab);

    } catch (Exception e) {
        System.out.println("Error formato fecha: " + fecha);
        return false;
    }
}

        return cumple;

    }).map(cab -> {

        String servicio = null;
        String motivo = null;

        List<RegFormularioDet> detalles =
                detRepository.findByRegFormularioCabIdRegCab(cab.getIdRegCab());

        for (RegFormularioDet det : detalles) {

            if (det.getCampo().getIdCampo() == 10)
                servicio = det.getValor();

            if (det.getCampo().getIdCampo() == 13)
                motivo = det.getValor();
        }

        if (servicio == null || servicio.trim().isEmpty()
                || motivo == null || motivo.trim().isEmpty()) {
            return null;
        }

        return new FormularioListadoDTO(
                cab.getIdRegCab(),
                cab.getFecha() != null
                        ? cab.getFecha().toLocalDate()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        : "",
                cab.getEstado(),
                servicio,
                motivo
        );

    }).filter(dto -> dto != null).toList();
}



public Map<String, Object> obtenerFormularioPlano(Integer idRegCab) {

    List<Object[]> lista = detRepository.obtenerCamposPorCab(idRegCab);

    Map<String, Object> resultado = new HashMap<>();
    resultado.put("idRegCab", idRegCab);

    for (Object[] fila : lista) {
        String nombreCampo = (String) fila[0];
        String valor = (String) fila[1];

        resultado.put(nombreCampo, valor);
    }

    return resultado;
}


public List<FormularioListadoDTO> buscar(FiltroFormularioDTO filtro) {

    List<RegFormularioCab> lista = cabRepository.findByUsuario_IdUsuario(filtro.getIdUsuario());

    return lista.stream().filter(cab -> {

        boolean cumple = true;

        // 🔵 FILTRO ESTADO
        if (filtro.getEstado() != null && !filtro.getEstado().isEmpty()) {
            cumple = cumple && filtro.getEstado().equalsIgnoreCase(cab.getEstado());
        }

        // 🔵 FILTRO FECHA
        if (filtro.getFecha() != null && !filtro.getFecha().isEmpty()) {

            java.time.LocalDate fechaFiltro = java.time.LocalDate.parse(
                    filtro.getFecha(),
                    java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")
            );

            java.time.LocalDate fechaCab = cab.getFecha() != null
                    ? cab.getFecha().toLocalDate()
                    : null;

            cumple = cumple && fechaFiltro.equals(fechaCab);
        }

        return cumple;

    }).map(cab -> {

        String servicio = null;
        String motivo = null;

        List<RegFormularioDet> detalles =
                detRepository.findByRegFormularioCabIdRegCab(cab.getIdRegCab());

        for (RegFormularioDet det : detalles) {

            if (det.getCampo().getIdCampo() == 10)
                servicio = det.getValor();

            if (det.getCampo().getIdCampo() == 13)
                motivo = det.getValor();
        }

        // 🔵 FILTRO SERVICIO (IMPORTANTE)
        if (filtro.getServicio() != null && !filtro.getServicio().isEmpty()) {
            if (servicio == null || !servicio.equalsIgnoreCase(filtro.getServicio())) {
                return null;
            }
        }

        if (servicio == null || motivo == null) {
            return null;
        }

        return new FormularioListadoDTO(
                cab.getIdRegCab(),
                cab.getFecha() != null
                        ? cab.getFecha().toLocalDate()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        : "",
                cab.getEstado(),
                servicio,
                motivo
        );

    }).filter(dto -> dto != null).toList();
}


public FormularioDetalleProjection obtenerDetalleCompleto(Integer idRegCab, Integer idUsuario) {
    return cabRepository.obtenerDetalle(idRegCab, idUsuario);
}


}