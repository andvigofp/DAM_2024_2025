package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private int idLibro;
    private String codigo;
    private String titulo;
    private String autores;

    @Column(name = "año")
    private int anho;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Alquiler> alquiler;

    public Libro(String codigo, String titulo, String autores, int anho) {
        super();
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.anho = anho;
    }

    public void addAlquiler(Alquiler alquiler) {
        this.alquiler.add(alquiler);
    }
}
