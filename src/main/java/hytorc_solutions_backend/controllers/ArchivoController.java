package hytorc_solutions_backend.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.service.UsuarioService;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    private final UsuarioService usuarioService;

    private final String CARPETA_FIRMA = "D:/archivos/firma/";

    public ArchivoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 🔹 Subir solo firma
    @PostMapping("/subirFirma/{id}")
    public ResponseEntity<?> subirFirma(
            @PathVariable Integer id,
            @RequestParam("archivo") MultipartFile archivo) throws IOException {

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        Path rutaCarpeta = Paths.get(CARPETA_FIRMA);
        if (!Files.exists(rutaCarpeta)) {
            Files.createDirectories(rutaCarpeta);
        }

        String extension = archivo.getOriginalFilename()
                .substring(archivo.getOriginalFilename().lastIndexOf("."));
        String nombreArchivo = id + "_firma_" + System.currentTimeMillis() + extension;
        Path rutaArchivo = rutaCarpeta.resolve(nombreArchivo);
        archivo.transferTo(rutaArchivo.toFile());

        // Guardar solo el nombre del archivo
        usuario.setFirmaDigital(nombreArchivo);
        usuarioService.actualizarUsuarioArchivos(usuario);

        return ResponseEntity.ok(Map.of("nombreArchivo", nombreArchivo));
    }

    // 🔹 Ver firma
    @GetMapping("/verFirma")
    public ResponseEntity<Resource> verFirma(@RequestParam String nombreArchivo) throws IOException {
        Path ruta = Paths.get(CARPETA_FIRMA, nombreArchivo);
        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(ruta))
                .body(recurso);
    }

    

    // 🔹 Eliminar firma
    @DeleteMapping("/eliminarFirma/{id}")
    public ResponseEntity<?> eliminarFirma(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        String nombreArchivo = usuario.getFirmaDigital();
        if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
            Path rutaArchivo = Paths.get(CARPETA_FIRMA, nombreArchivo);
            try {
                Files.deleteIfExists(rutaArchivo); // elimina el archivo físico
            } catch (IOException e) {
                return ResponseEntity.status(500).body("No se pudo eliminar el archivo: " + e.getMessage());
            }

            // Actualizar el campo del usuario
            usuario.setFirmaDigital("");
            usuarioService.actualizarUsuarioArchivos(usuario);
        }

        return ResponseEntity.ok(Map.of("mensaje", "Firma eliminada correctamente"));
    }
}