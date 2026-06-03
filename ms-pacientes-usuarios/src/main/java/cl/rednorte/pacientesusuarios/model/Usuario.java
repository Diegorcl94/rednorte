package cl.rednorte.pacientesusuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rut;
    private String email;
    private String password;
    private Boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
