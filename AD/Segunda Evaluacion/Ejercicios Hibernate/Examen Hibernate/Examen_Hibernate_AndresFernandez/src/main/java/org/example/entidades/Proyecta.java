package org.example.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor

@Embeddable
public class Proyecta {
    @Column(name = "peliculas_id")
    private int peliculaId;

    @Column(name = "sala_id")
    private int idSala;

    private LocalDate fecha;

    private LocalTime horario;


    public Proyecta(int peliculaId, int idSala, LocalDate fecha, LocalTime horario) {
        this.peliculaId = peliculaId;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horario = horario;
    }


    @Override
    public String toString() {
        return "proyeccionesPK{" +
                "peliculaId=" + peliculaId +
                ", idSala=" + idSala +
                ", fecha=" + fecha +
                ", horario=" + horario +
                '}';
    }
}
