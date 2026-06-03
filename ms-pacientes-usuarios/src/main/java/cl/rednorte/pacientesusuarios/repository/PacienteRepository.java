package cl.rednorte.pacientesusuarios.repository;

import cl.rednorte.pacientesusuarios.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
