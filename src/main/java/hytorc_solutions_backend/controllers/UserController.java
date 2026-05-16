package hytorc_solutions_backend.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hytorc_solutions_backend.dto.UsuarioDto;
import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listarUsu")
    public List<Usuario> listarUsuarios(@RequestParam(required = false) String usuario) {
        if (usuario != null && !usuario.isEmpty()) {
            return usuarioService.buscarPorUsuarioLike(usuario);
        }
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String usuario,
            @RequestParam String password) {

        Usuario user = usuarioService.findByUsuario(usuario);

        if (user == null) {
            return ResponseEntity.status(401).body(0);
        }

        boolean passOk = usuarioService.validarPassword(user, password);

        if (!passOk || user.getEstadoUsuario() != 1) {
            return ResponseEntity.status(401).body(0);
        }

       UsuarioDto dto = new UsuarioDto(
    user.getIdUsuario(),
    user.getUsuario(),
    user.getEmpleado().getNomEmpleado() + " " + user.getEmpleado().getApellEmpleado(),
    user.getEmpleado().getDniEmpleado(),
    user.getEmpleado().getTelefEmpleado(),
    user.getEmpleado().getDireccion(),
    user.getEmpleado().getEmailEmpleado(),
    user.getEmpleado().getFechNacimiento(),
    user.getTipoUsu(),
    user.getEmpleado().getIdArea(),
    user.getEmpleado().getArea().getNombreArea(),
    user.getFirmaDigital() // 🔹 enviar la firma al front
);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {

        try {

            Usuario nuevoUsuario = usuarioService.guardarUsuarioConHash(usuario);

            return ResponseEntity.ok(nuevoUsuario);

        } catch (Exception e) {

            return ResponseEntity.status(500).body("Error al guardar usuario");

        }

    }

    @PostMapping("/modificar/{id}")
    public ResponseEntity<?> modificarUsuario(
            @PathVariable Integer id,
            @RequestBody Map<String, String> payload) {

        try {
            Usuario usuarioActualizado = new Usuario();

            // Asignar contraseña si viene
            if (payload.containsKey("password") && !payload.get("password").isEmpty()) {
                usuarioActualizado.setPassword(payload.get("password"));
            }

            // Asignar tipo de usuario si viene
            if (payload.containsKey("tipoUsu")) {
                usuarioActualizado.setTipoUsu(payload.get("tipoUsu"));
            }

            // Asignar estado de usuario si viene
            if (payload.containsKey("estadoUsuario")) {
                usuarioActualizado.setEstadoUsuario(Integer.parseInt(payload.get("estadoUsuario")));
            }

            // Cualquier otro campo que quieras permitir actualizar
            // Ej: email, nombre, etc. (si decides exponerlo en el CRUD)
            // ...

            // Actualizar usando el servicio
            Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);

            return ResponseEntity.ok(Map.of("status", "OK", "mensaje", "Usuario actualizado correctamente"));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("status", "ERROR", "mensaje", "Error al modificar usuario: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar usuario: " + e.getMessage());
        }
    }

}
