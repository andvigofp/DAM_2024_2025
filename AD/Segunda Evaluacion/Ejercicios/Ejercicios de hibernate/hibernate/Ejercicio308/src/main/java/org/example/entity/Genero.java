package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "idGenero")
public class Genero {

    @Id
    @Column(name = "idGenero")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idGenero;
    private String nombre;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public void addPelicula(Pelicula pelicula) {
        this.listaPeliculas.add(pelicula);
    }

    @Override
    public String toString() {
        return "Genero [idGenero=" + idGenero + ", nombre=" + nombre + ", listaPeliculas=" + listaPeliculas + "]";
    }
}
