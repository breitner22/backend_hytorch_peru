package hytorc_solutions_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import hytorc_solutions_backend.entity.Usuario;
import hytorc_solutions_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    public boolean validarPassword(Usuario usuario, String passwordPlano) {
        return passwordEncoder.matches(passwordPlano, usuario.getPassword());
    }

    public Usuario guardarUsuarioConHash(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarPorUsuarioLike(String usuario) {
        return usuarioRepository.findByUsuarioContainingIgnoreCase(usuario);
    }

    public Usuario actualizarUsuario(Integer idUsuario, Usuario usuarioActualizado) {
        return usuarioRepository.findById(idUsuario)
                .map(usuario -> {

                    if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                        usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                    }
                    if (usuarioActualizado.getEstadoUsuario() != null) {
                        usuario.setEstadoUsuario(usuarioActualizado.getEstadoUsuario());
                    }
                    if (usuarioActualizado.getTipoUsu() != null) {
                        usuario.setTipoUsu(usuarioActualizado.getTipoUsu());
                    }
                    return usuarioRepository.save(usuario);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
    }

    public void eliminarUsuario(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario actualizarUsuarioArchivos(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElse(null);
    }

    

}
