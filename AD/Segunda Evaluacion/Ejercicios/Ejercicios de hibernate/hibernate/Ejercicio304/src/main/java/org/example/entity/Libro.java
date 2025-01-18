package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "Libros", uniqueConstraints = @UniqueConstraint(columnNames = "titulo", name = "tituloUniqueConstraint"))
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")

    private int id;
    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Precio")
    private double precio;

    @ManyToMany(mappedBy = "listaLibros")
    List<Autor> listaAutores = new ArrayList<Autor>();

    public Libro(String titulo, double precio) {
        super();
        this.titulo = titulo;
        this.precio = precio;
    }

    public void addAutor(Autor autor) {
        this.listaAutores.add(autor);
    }

    @Override
    public String toString() {
        return "{idLibro: " + id + ", Titulo: " + titulo + ", Precio: " + precio + "}";
    }

}
