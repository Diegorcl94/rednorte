package cl.rednorte.pacientesusuarios.controller;

import cl.rednorte.pacientesusuarios.dto.LoginRequest;
import cl.rednorte.pacientesusuarios.model.Usuario;
import cl.rednorte.pacientesusuarios.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {
        return usuarioRepository
                .findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElse(null);
    }
}
