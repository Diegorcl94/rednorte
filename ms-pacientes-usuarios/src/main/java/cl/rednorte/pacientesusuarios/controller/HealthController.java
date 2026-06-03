package cl.rednorte.pacientesusuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Microservicio pacientes-usuarios funcionando correctamente";
    }
}