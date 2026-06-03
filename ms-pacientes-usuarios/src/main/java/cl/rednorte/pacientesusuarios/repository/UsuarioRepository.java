package cl.rednorte.pacientesusuarios.repository;

import cl.rednorte.pacientesusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
