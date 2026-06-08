package cl.rednorte.listaespera.repository;

import cl.rednorte.listaespera.model.ListaEspera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {
    List<ListaEspera> findByEstado(String estado);
    List<ListaEspera> findByPacienteId(Long pacienteId);
}
