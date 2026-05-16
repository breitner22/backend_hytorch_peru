package hytorc_solutions_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hytorc_solutions_backend.entity.RegFormularioCab;
import hytorc_solutions_backend.repository.RegFormularioCabRepository;

@Service
public class RegFormularioCabService {

    
    @Autowired
    private RegFormularioCabRepository repository;

    public RegFormularioCab obtenerUltimoPorFormulario(Integer idFormulario) {
        return repository
                .findFirstByFormulario_IdFormularioOrderByIdRegCabDesc(idFormulario)
                .orElse(null);
    }

    

}