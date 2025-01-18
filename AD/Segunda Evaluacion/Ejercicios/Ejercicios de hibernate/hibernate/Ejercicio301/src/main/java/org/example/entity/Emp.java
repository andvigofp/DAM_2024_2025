package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emps")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private int id;
    private String nombre;
    private String puesto;
    private double sueldo;
    private int edad;
    private String DNI;
    private boolean esJefe;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dpto_id")
    private Dpto dptoElments;

    // Constructor adicional para crear un empleado con su departamento
    public Emp(String nombre, String puesto, double sueldo, int edad, String DNI, boolean esJefe) {
        super();
        this.nombre = nombre;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.edad = edad;
        this.DNI = DNI;
        this.esJefe = esJefe;
    }
}
