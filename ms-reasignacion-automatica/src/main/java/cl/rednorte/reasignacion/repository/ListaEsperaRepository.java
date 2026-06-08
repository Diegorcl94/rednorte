package cl.rednorte.reasignacion.repository;

import cl.rednorte.reasignacion.entity.ListaEspera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {

    Optional<ListaEspera> findFirstByEstadoOrderByFechaIngresoAsc(String estado);
}
