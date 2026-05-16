package hytorc_solutions_backend.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hytorc_solutions_backend.entity.HistorialFormulario;
import hytorc_solutions_backend.repository.HistorialFormularioRepository;

@Service
public class HistorialFormularioService {

    @Autowired
    private HistorialFormularioRepository historialRepository;

    public void registrarCambio(Integer idFormulario, Integer idUsuario, String descripcion) {

    HistorialFormulario h = new HistorialFormulario();
    h.setIdRegCab(idFormulario); 
    h.setIdUsuario(idUsuario);
    h.setFechaMod(LocalDateTime.now());
    h.setCambios(descripcion);

    historialRepository.save(h);
}
}