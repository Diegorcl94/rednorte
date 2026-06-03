package cl.rednorte.pacientesusuarios.controller;

import cl.rednorte.pacientesusuarios.model.Rol;
import cl.rednorte.pacientesusuarios.repository.RolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rol buscarPorId(@PathVariable Long id) {
        return rolRepository.findById(id).orElse(null);
    }
}
