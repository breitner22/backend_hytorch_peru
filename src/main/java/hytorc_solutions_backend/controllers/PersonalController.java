package hytorc_solutions_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hytorc_solutions_backend.dto.PersonalDto;
import hytorc_solutions_backend.service.PersonalService;

@RestController
@CrossOrigin(origins = "http://localhost:8085") 
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/api/empleados/listar")
    public List<PersonalDto> listarEmpleados() {
        return personalService.listarPersonalConArea();
    }
}
