package cl.rednorte.reasignacion.controller;

import cl.rednorte.reasignacion.entity.Reasignacion;
import cl.rednorte.reasignacion.repository.ReasignacionRepository;
import cl.rednorte.reasignacion.service.ReasignacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reasignaciones")
public class ReasignacionController {

    private final ReasignacionRepository repository;
    private final ReasignacionService service;

    public ReasignacionController(ReasignacionRepository repository, ReasignacionService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Reasignacion> listar() {
        return repository.findAll();
    }

    @PostMapping("/automatica/{horaId}")
    public Reasignacion reasignarAutomaticamente(@PathVariable Long horaId) {
        return service.reasignarAutomaticamente(horaId);
    }
}
