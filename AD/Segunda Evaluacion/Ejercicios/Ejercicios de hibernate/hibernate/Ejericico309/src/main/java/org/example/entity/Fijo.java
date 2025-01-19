package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

@Entity
@PrimaryKeyJoinColumn(name = "DNI_EMPLEADO")
@Table(name = "EMPLEADOS_FIJOS")
public class Fijo extends Empleado implements Serializable {

    @Column(name = "SALARIO_BASE")
    private int salarioBase;

    @Column(name = "TRIENIOS")
    private int trienios;

    @Column(name = "SUELDO")
    private float sueldo;

    public Fijo(String dni, String nombre, String telefono, float porcentajeRetencion, int salarioBase, int trienios) {
        super(dni, nombre, telefono, porcentajeRetencion);
        this.salarioBase = salarioBase;
        this.trienios = trienios;
    }

    @Override
    public void calcularNomina() {
        sueldo = (salarioBase + trienios) * (1 - getPorcentajeRetencion());
    }

    @Override
    public String toString() {
        String fijo = "DNI: " + super.getDni() + "\n"
                + "NOMBRE: " + super.getNombre() + "\n" + "TELÉFONO: " + super.getTelefono() + "\n"
                + "PORCENTAJE DE RENTENCIÓN: " + super.getPorcentajeRetencion() + "\n"
                + "SALARIO BASE: " + getSalarioBase() + "\n" + "TRIENIOS: " + getTrienios() + "\n"
                + "SULEDO: " + getSueldo() + "\n";
        return fijo;
    }
}
