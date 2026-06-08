package cl.rednorte.reasignacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "horas_medicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paciente_id")
    private Long pacienteId;

    @Column(name="especialidad_id")
    private Long especialidadId;

    @Column(name="centro_id")
    private Long centroId;

    @Column(name="fecha_hora")
    private LocalDateTime fechaHora;

    private String estado;
}
