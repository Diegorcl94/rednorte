package cl.rednorte.reasignacion.service;

import cl.rednorte.reasignacion.entity.HoraMedica;
import cl.rednorte.reasignacion.entity.ListaEspera;
import cl.rednorte.reasignacion.entity.Reasignacion;
import cl.rednorte.reasignacion.repository.HoraMedicaRepository;
import cl.rednorte.reasignacion.repository.ListaEsperaRepository;
import cl.rednorte.reasignacion.repository.ReasignacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReasignacionService {

    private final HoraMedicaRepository horaRepository;
    private final ListaEsperaRepository listaRepository;
    private final ReasignacionRepository reasignacionRepository;

    public ReasignacionService(
            HoraMedicaRepository horaRepository,
            ListaEsperaRepository listaRepository,
            ReasignacionRepository reasignacionRepository) {
        this.horaRepository = horaRepository;
        this.listaRepository = listaRepository;
        this.reasignacionRepository = reasignacionRepository;
    }

    public Reasignacion reasignarAutomaticamente(Long horaId) {
        HoraMedica hora = horaRepository.findById(horaId).orElse(null);

        if (hora == null) {
            return null;
        }

        ListaEspera pacienteEnEspera = listaRepository
                .findFirstByEstadoOrderByFechaIngresoAsc("EN_ESPERA")
                .orElse(null);

        if (pacienteEnEspera == null) {
            hora.setPacienteId(null);
            hora.setEstado("DISPONIBLE");
            horaRepository.save(hora);
            return null;
        }

        hora.setPacienteId(pacienteEnEspera.getPacienteId());
        hora.setEstado("REASIGNADA");
        horaRepository.save(hora);

        pacienteEnEspera.setEstado("REASIGNADO");
        listaRepository.save(pacienteEnEspera);

        Reasignacion reasignacion = new Reasignacion();
        reasignacion.setPacienteId(pacienteEnEspera.getPacienteId());
        reasignacion.setHoraMedicaId(hora.getId());
        reasignacion.setListaEsperaId(pacienteEnEspera.getId());
        reasignacion.setMotivo("Hora cancelada reasignada automáticamente al primer paciente en espera");
        reasignacion.setEstado("COMPLETADA");
        reasignacion.setFechaReasignacion(LocalDateTime.now());

        return reasignacionRepository.save(reasignacion);
    }
}
