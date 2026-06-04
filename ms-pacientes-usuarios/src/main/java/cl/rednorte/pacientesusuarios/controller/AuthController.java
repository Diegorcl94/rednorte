package cl.rednorte.pacientesusuarios.controller;

import cl.rednorte.pacientesusuarios.dto.LoginRequest;
import cl.rednorte.pacientesusuarios.dto.LoginResponse;
import cl.rednorte.pacientesusuarios.model.Usuario;
import cl.rednorte.pacientesusuarios.repository.UsuarioRepository;
import cl.rednorte.pacientesusuarios.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository
                .findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElse(null);

        if (usuario == null) {
            return null;
        }

        String token = jwtUtil.generarToken(usuario);
        return new LoginResponse(token, usuario);
    }
}
