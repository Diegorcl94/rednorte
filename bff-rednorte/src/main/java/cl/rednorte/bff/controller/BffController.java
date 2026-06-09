package cl.rednorte.bff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bff")
public class BffController {

    private final RestTemplate restTemplate;

    private final String MS_USUARIOS = "http://localhost:8081";
    private final String MS_LISTA_ESPERA = "http://localhost:8082";
    private final String MS_REASIGNACION = "http://localhost:8083";

    public BffController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/health")
    public String health() {
        return "BFF RedNorte funcionando correctamente";
    }

    @GetMapping("/pacientes")
    public Object obtenerPacientes() {
        return restTemplate.getForObject(MS_USUARIOS + "/api/pacientes", Object.class);
    }

    @GetMapping("/usuarios")
    public Object obtenerUsuarios() {
        return restTemplate.getForObject(MS_USUARIOS + "/api/usuarios", Object.class);
    }

    @GetMapping("/listas-espera")
    public Object obtenerListasEspera() {
        return restTemplate.getForObject(MS_LISTA_ESPERA + "/api/listas-espera", Object.class);
    }

    @GetMapping("/horas-medicas")
    public Object obtenerHorasMedicas() {
        return restTemplate.getForObject(MS_REASIGNACION + "/api/horas-medicas", Object.class);
    }

    @GetMapping("/reasignaciones")
    public Object obtenerReasignaciones() {
        return restTemplate.getForObject(MS_REASIGNACION + "/api/reasignaciones", Object.class);
    }
}
