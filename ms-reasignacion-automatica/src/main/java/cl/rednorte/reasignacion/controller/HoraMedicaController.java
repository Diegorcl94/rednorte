package cl.rednorte.reasignacion.controller;

import cl.rednorte.reasignacion.entity.HoraMedica;
import cl.rednorte.reasignacion.repository.HoraMedicaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horas-medicas")
public class HoraMedicaController {

    private final HoraMedicaRepository repository;

    public HoraMedicaController(HoraMedicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<HoraMedica> listar() {
        return repository.findAll();
    }

    @PostMapping
    public HoraMedica crear(@RequestBody HoraMedica horaMedica) {
        if (horaMedica.getEstado() == null || horaMedica.getEstado().isBlank()) {
            horaMedica.setEstado("DISPONIBLE");
        }
        return repository.save(horaMedica);
    }

    @GetMapping("/{id}")
    public HoraMedica buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/estado/{estado}")
    public List<HoraMedica> buscarPorEstado(@PathVariable String estado) {
        return repository.findByEstado(estado);
    }

    @PutMapping("/{id}/confirmar-llegada")
    public HoraMedica confirmarLlegada(@PathVariable Long id) {
        return repository.findById(id).map(hora -> {
            hora.setEstado("CONFIRMADA");
            return repository.save(hora);
        }).orElse(null);
    }

    @PutMapping("/{id}/cancelar")
    public HoraMedica cancelarHora(@PathVariable Long id) {
        return repository.findById(id).map(hora -> {
            hora.setPacienteId(null);
            hora.setEstado("DISPONIBLE");
            return repository.save(hora);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
