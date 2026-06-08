package cl.rednorte.reasignacion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "listas_espera")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaEspera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paciente_id")
    private Long pacienteId;

    @Column(name="especialidad_id")
    private Long especialidadId;

    @Column(name="centro_id")
    private Long centroId;

    @Column(name="motivo_atencion")
    private String motivoAtencion;

    private String prioridad;
    private String estado;

    @Column(name="fecha_ingreso")
    private LocalDateTime fechaIngreso;
}
