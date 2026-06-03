package cl.rednorte.pacientesusuarios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rut;
    private Integer edad;
    private String sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String comuna;

    @Column(name = "enfermedad_cronica")
    private String enfermedadCronica;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
