package org.example.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "actores")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actor")
    private int id;

    @NonNull
    private String nombre;

    @NonNull
    @Column(name = "fecha_nacimiento")
    private LocalDate fecha;

    @NonNull
    private String nacionalidad;

    @ManyToMany(mappedBy = "listaActores")
    List<Peliculas> listaPeliculas;



    @Override
    public String toString() {
        return "Actores{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
