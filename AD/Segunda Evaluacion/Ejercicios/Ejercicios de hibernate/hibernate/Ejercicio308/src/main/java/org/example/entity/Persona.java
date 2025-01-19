package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_persona", discriminatorType = DiscriminatorType.INTEGER)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private int idPersona;
    private String nombre;
    private int anhoNacimiento;
    private String DNI;

    @ManyToMany
    @JoinTable(name = "actores_peliculas",
        joinColumns = @JoinColumn(name = "idPersona"),
        inverseJoinColumns = @JoinColumn(name = "idPelicula"))
    List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

    public Persona(String nombre, String dNI, int anhoNacimiento) {
        super();
        this.nombre = nombre;
        this.DNI = dNI;
        this.anhoNacimiento = anhoNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", anhoNacimiento=" + anhoNacimiento
                + ", DNI=" + DNI + "]";
    }
}
