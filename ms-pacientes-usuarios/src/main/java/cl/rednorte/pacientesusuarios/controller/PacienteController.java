package cl.rednorte.pacientesusuarios.controller;

import cl.rednorte.pacientesusuarios.model.Paciente;
import cl.rednorte.pacientesusuarios.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Long id, @RequestBody Paciente pacienteActualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombre(pacienteActualizado.getNombre());
            paciente.setRut(pacienteActualizado.getRut());
            paciente.setEdad(pacienteActualizado.getEdad());
            paciente.setSexo(pacienteActualizado.getSexo());
            paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            paciente.setComuna(pacienteActualizado.getComuna());
            paciente.setEnfermedadCronica(pacienteActualizado.getEnfermedadCronica());
            return pacienteRepository.save(paciente);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}
