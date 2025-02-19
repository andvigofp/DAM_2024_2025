package org.example.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "premios")
public class Premios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio")
    private int idProyecciones;

    @NonNull
    @Column(name = "nombre_premio")
    private String nombrePremio;

    @NonNull
    @JoinColumn(name = "anhoPremio", referencedColumnName = "año")
    private int anhoPremio;

    @OneToOne
    private Peliculas peliculas;

    public Premios(int idProyecciones, @NonNull String nombrePremio, @NonNull int anhoPremio) {
        this.idProyecciones = idProyecciones;
        this.nombrePremio = nombrePremio;
        this.anhoPremio = anhoPremio;
    }

    @Override
    public String toString() {
        return "Premios{" +
                "idProyecciones=" + idProyecciones +
                ", nombrePremio='" + nombrePremio + '\'' +
                ", anhoPremio=" + anhoPremio +
                '}';
    }
}
