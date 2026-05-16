package hytorc_solutions_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hytorc_solutions_backend.service.RegFormularioCabService;
import hytorc_solutions_backend.dto.FiltroFormularioDTO;
import hytorc_solutions_backend.dto.FormularioListadoDTO;
import hytorc_solutions_backend.entity.RegFormularioCab;
import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.service.FormularioService;
import hytorc_solutions_backend.service.HistorialFormularioService;
import hytorc_solutions_backend.service.UsuarioService;

@RestController
@RequestMapping("/api/formularios")
@CrossOrigin(origins = "http://localhost:8085")
public class FormularioController {

    /*
     * private final HistorialFormularioService historialService;
     * private final UsuarioService usuarioService;
     * private final FormularioService formularioService;
     * 
     * public FormularioController(HistorialFormularioService historialService,
     * UsuarioService usuarioService,
     * FormularioService formularioService) {
     * this.historialService = historialService;
     * this.usuarioService = usuarioService;
     * this.formularioService = formularioService;
     * }
     */

    private final HistorialFormularioService historialService;
    private final UsuarioService usuarioService;
    private final FormularioService formularioService;
    private final RegFormularioCabService regFormularioCabService;

    public FormularioController(
            HistorialFormularioService historialService,
            UsuarioService usuarioService,
            FormularioService formularioService,
            RegFormularioCabService regFormularioCabService) {

        this.historialService = historialService;
        this.usuarioService = usuarioService;
        this.formularioService = formularioService;
        this.regFormularioCabService = regFormularioCabService;
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarFormulario(@RequestBody Map<String, Object> data) {

        Integer idUsuario = Integer.parseInt(data.get("idUsuario").toString());
        Integer idFormulario = Integer.parseInt(data.get("idFormulario").toString());

        Map<String, Object> formulario = (Map<String, Object>) data.get("formulario");

        Map<Integer, String> valores = new java.util.HashMap<>();
        for (Map.Entry<String, Object> entry : formulario.entrySet()) {
            try {
                Integer idCampo = Integer.parseInt(entry.getKey());
                valores.put(idCampo, entry.getValue().toString());
            } catch (NumberFormatException e) {
                throw new RuntimeException("La clave del formulario debe ser un ID de campo numérico");
            }
        }

        Usuario usuario = usuarioService.findById(idUsuario);

        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        if (!"A".equalsIgnoreCase(usuario.getTipoUsu()) && !"S".equalsIgnoreCase(usuario.getTipoUsu())) {
            return ResponseEntity.status(403).body("No tiene permisos para editar este formulario");
        }

        RegFormularioCab cab = formularioService.guardarFormulario(idFormulario, idUsuario, valores);

        if ("A".equalsIgnoreCase(usuario.getTipoUsu())) {
            String descripcion = "Cambios realizados: " + valores.toString();
            historialService.registrarCambio(idFormulario, idUsuario, descripcion);
        }

        /* return ResponseEntity.ok("Formulario guardado correctamente"); */

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Formulario guardado correctamente");
        response.put("datos", Map.of(
                "formulario", valores));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ultimos-campos")
    public ResponseEntity<?> obtenerUltimosCampos(@RequestBody Map<String, Object> data) {

        try {
            Integer idFormulario = Integer.parseInt(data.get("idFormulario").toString());

            // Lista de campos (1,2,3...)
            List<Integer> campos = ((List<Object>) data.get("campos"))
                    .stream()
                    .map(c -> Integer.parseInt(c.toString()))
                    .toList();

            Map<Integer, String> resultado = formularioService
                    .obtenerUltimosCampos(idFormulario, campos);

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al obtener los campos: " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFormulario(@RequestBody Map<String, Object> data) {

        try {
            Integer idUsuario = Integer.parseInt(data.get("idUsuario").toString());
            Integer idFormulario = Integer.parseInt(data.get("idFormulario").toString());

            Map<String, Object> formulario = (Map<String, Object>) data.get("formulario");

            // 🔹 Convertir a Map<Integer, String>
            Map<Integer, String> valores = new HashMap<>();

            for (Map.Entry<String, Object> entry : formulario.entrySet()) {
                try {
                    Integer idCampo = Integer.parseInt(entry.getKey());
                    valores.put(idCampo, entry.getValue() != null ? entry.getValue().toString() : "");
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest()
                            .body("ID de campo inválido: " + entry.getKey());
                }
            }

            // 🔹 Validar usuario
            Usuario usuario = usuarioService.findById(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
            }

            // 🔹 Guardar formulario
            RegFormularioCab cab = formularioService.registrarFormulario(
                    idFormulario, idUsuario, valores);

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Formulario registrado correctamente",
                    "idRegistro", cab.getIdRegCab()));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al registrar: " + e.getMessage());
        }
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<FormularioListadoDTO>> listarPorUsuario(@PathVariable Integer idUsuario) {

        List<FormularioListadoDTO> lista = formularioService.listarPorUsuario(idUsuario);

        return ResponseEntity.ok(lista);
    }


@PostMapping("/filtrar")
public ResponseEntity<List<FormularioListadoDTO>> filtrar(@RequestBody Map<String, String> filtros) {

    Integer idUsuario = Integer.parseInt(filtros.get("idUsuario"));
    String estado = filtros.get("estado");
    String fecha = filtros.get("fecha");

    List<FormularioListadoDTO> lista = formularioService.filtrar(idUsuario, estado, fecha);

    return ResponseEntity.ok(lista);
}


@GetMapping("/formulario/plano/{id}")
public ResponseEntity<?> obtenerPlano(@PathVariable Integer id) {
    return ResponseEntity.ok(formularioService.obtenerFormularioPlano(id));
}


@PostMapping("/buscar")
public ResponseEntity<List<FormularioListadoDTO>> buscar(@RequestBody FiltroFormularioDTO filtro) {

    List<FormularioListadoDTO> lista = formularioService.buscar(filtro);

    return ResponseEntity.ok(lista);
}


@GetMapping("/detalle")
public ResponseEntity<?> obtenerDetalle(
        @RequestParam Integer idRegCab,
        @RequestParam Integer idUsuario) {

    try {

        var detalle = formularioService
                .obtenerDetalleCompleto(idRegCab, idUsuario);

        return ResponseEntity.ok(detalle);

    } catch (Exception e) {

        return ResponseEntity.status(404)
                .body("No se encontró el detalle del formulario");
    }
}

}