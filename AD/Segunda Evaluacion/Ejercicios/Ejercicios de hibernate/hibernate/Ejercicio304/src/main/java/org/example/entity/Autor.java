package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor

@Entity
@Table(name = "Autores")
public class Autor {

    @Id
    @Column(name = "DniAutor")
    private String dniAutor;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Nacionalidad")
    private String nacionalidad;

    @ManyToMany
    @JoinTable(name="Libros_Autores",
            joinColumns = @JoinColumn(name="DniAutor"),
            inverseJoinColumns = @JoinColumn(name="idLibro"))
    List<Libro> listaLibros = new ArrayList<Libro>();

    @OneToOne(mappedBy = "autor")
    @JoinColumn(name = "dni_autor")
    private Telefono telefono;

    public Autor(String dniAutor, String nombre, String nacionalidad) {
        this.dniAutor = dniAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Método para agregar un libro a la lista de libros del autor
    public void addListaLibros(Libro libro) {
        this.listaLibros.add(libro);
        libro.addAutor(this); // Agrega el autor a la lista de autores del libro
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
        telefono.setAutor(this);
    }

    @Override
    public String toString() {
        return "{dniAutor: " + dniAutor + ", nombre: " + nombre + ", nacionalidad: " + nacionalidad + "}";
    }

}
