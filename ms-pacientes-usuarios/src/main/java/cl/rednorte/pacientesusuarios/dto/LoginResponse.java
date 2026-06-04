package cl.rednorte.pacientesusuarios.dto;

import cl.rednorte.pacientesusuarios.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Usuario usuario;
}
