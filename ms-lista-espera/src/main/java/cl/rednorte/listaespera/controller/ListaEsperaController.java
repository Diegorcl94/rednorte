package cl.rednorte.listaespera.controller;

import cl.rednorte.listaespera.model.ListaEspera;
import cl.rednorte.listaespera.repository.ListaEsperaRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/listas-espera")
public class ListaEsperaController {

    private final ListaEsperaRepository repository;

    public ListaEsperaController(ListaEsperaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ListaEspera> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ListaEspera crear(@RequestBody ListaEspera listaEspera) {
        listaEspera.setFechaIngreso(LocalDateTime.now());

        if (listaEspera.getEstado() == null || listaEspera.getEstado().isBlank()) {
            listaEspera.setEstado("EN_ESPERA");
        }

        return repository.save(listaEspera);
    }

    @GetMapping("/{id}")
    public ListaEspera buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/estado/{estado}")
    public List<ListaEspera> buscarPorEstado(@PathVariable String estado) {
        return repository.findByEstado(estado);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<ListaEspera> buscarPorPaciente(@PathVariable Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    @PutMapping("/{id}")
    public ListaEspera actualizar(@PathVariable Long id, @RequestBody ListaEspera datos) {
        return repository.findById(id).map(lista -> {
            lista.setPacienteId(datos.getPacienteId());
            lista.setEspecialidadId(datos.getEspecialidadId());
            lista.setCentroId(datos.getCentroId());
            lista.setMotivoAtencion(datos.getMotivoAtencion());
            lista.setPrioridad(datos.getPrioridad());
            lista.setEstado(datos.getEstado());
            return repository.save(lista);
        }).orElse(null);
    }

    @PutMapping("/{id}/estado")
    public ListaEspera cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return repository.findById(id).map(lista -> {
            lista.setEstado(estado);
            return repository.save(lista);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
