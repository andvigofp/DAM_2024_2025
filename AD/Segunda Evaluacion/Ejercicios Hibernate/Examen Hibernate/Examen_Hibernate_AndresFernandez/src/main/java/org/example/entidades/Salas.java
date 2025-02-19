package org.example.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "salas")
public class Salas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int idSala;

    @NonNull
    private String nombre;

    @NonNull
    private int capacidad;

    @ManyToMany
    private List<Peliculas> listaPeliculas;

    public Salas(int idSala, @NonNull String nombre, @NonNull int capacidad) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "salas{" +
                "idSala=" + idSala +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
