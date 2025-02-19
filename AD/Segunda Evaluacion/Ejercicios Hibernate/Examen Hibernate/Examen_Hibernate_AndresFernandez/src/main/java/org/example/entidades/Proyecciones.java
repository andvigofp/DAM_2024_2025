package org.example.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "proyecciones")
public class Proyecciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "pelicula_id")
    private int idPeliculas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_sala")
    private int idSala;

    @NonNull
    @Id
    private LocalDate fecha;
    @NonNull
    @Id
    private LocalTime horario;

}
