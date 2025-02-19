package org.example.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "peliculas")
public class Peliculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " id_pelicula")
    private int peliculaId;

    @NonNull
    private String titulo;

    @NonNull
    @Column(name = "año_estreno ", columnDefinition = "year")
    private int anho_estreno;

    @NonNull
    private String genero;

    @ManyToOne
    private Peliculas peliculas;

    @ManyToOne
    private List<Actor> actors;



    public Peliculas(int peliculaId, @NonNull String titulo, @NonNull int anho_estreno, @NonNull String genero) {
        this.peliculaId = peliculaId;
        this.titulo = titulo;
        this.anho_estreno = anho_estreno;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Peliculas{" +
                "peliculaId=" + peliculaId +
                ", titulo='" + titulo + '\'' +
                ", anho_estreno=" + anho_estreno +
                ", genero='" + genero + '\'' +
                '}';
    }
}
