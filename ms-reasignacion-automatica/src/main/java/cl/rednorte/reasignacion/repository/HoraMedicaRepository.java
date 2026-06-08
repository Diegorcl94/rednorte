package cl.rednorte.reasignacion.repository;

import cl.rednorte.reasignacion.entity.HoraMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoraMedicaRepository extends JpaRepository<HoraMedica, Long> {
    List<HoraMedica> findByEstado(String estado);
}
