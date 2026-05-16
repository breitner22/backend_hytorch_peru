package hytorc_solutions_backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hytorc_solutions_backend.dto.PersonalDto;
import hytorc_solutions_backend.entity.Personal;
import hytorc_solutions_backend.repository.AreaRepository;
import hytorc_solutions_backend.repository.PersonalRepository;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private AreaRepository areaRepository;

    public List<PersonalDto> listarPersonalConArea() {
        List<Personal> listaPersonal = personalRepository.findAll();

        return listaPersonal.stream().map(personal -> {
            PersonalDto dto = new PersonalDto();
            dto.setIdEmpleado(personal.getIdEmpleado());
            dto.setNomEmpleado(personal.getNomEmpleado());
            dto.setApellEmpleado(personal.getApellEmpleado());
            dto.setIdArea(personal.getIdArea());

          
            areaRepository.findById(personal.getIdArea()).ifPresent(area -> {
                dto.setNombreArea(area.getNombreArea());
                dto.setDescripcionArea(area.getDescripcionArea());
            });

            return dto;
        }).toList();
    }
}