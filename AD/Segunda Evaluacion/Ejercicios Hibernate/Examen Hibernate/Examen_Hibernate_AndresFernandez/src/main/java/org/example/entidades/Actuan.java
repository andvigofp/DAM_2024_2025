package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Actuan  {
    @Id
    @Column(name = "id_pelicula")
    private int actorId;

    @Id
    @Column(name = "pelicula_id")
    private int peliculaId;



    @Override
    public String toString() {
        return "Actuan{" +
                "actorId=" + actorId +
                ", peliculaId=" + peliculaId +
                '}';
    }
}
