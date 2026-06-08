package cl.rednorte.reasignacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reasignaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reasignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;
    private Long horaMedicaId;
    private Long listaEsperaId;

    private String motivo;
    private String estado;

    private LocalDateTime fechaReasignacion;
}
