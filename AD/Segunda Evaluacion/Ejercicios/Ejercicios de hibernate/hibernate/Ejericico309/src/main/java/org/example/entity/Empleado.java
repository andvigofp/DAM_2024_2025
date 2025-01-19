package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

@Entity
@Table(name = "EMPLEADOS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Empleado implements Serializable {

    @Id
    @Column(name = "DNI", unique = true, nullable = false)
    private String dni;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "PORCENTAJE_RETENCION")
    private float porcentajeRetencion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CIF_EMPRESA")
    private Empresa empresa;

    public Empleado(String dni, String nombre, String telefono, float porcentajeRetencion) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.porcentajeRetencion = porcentajeRetencion;
    }

    public void calcularNomina() {
    }
}
