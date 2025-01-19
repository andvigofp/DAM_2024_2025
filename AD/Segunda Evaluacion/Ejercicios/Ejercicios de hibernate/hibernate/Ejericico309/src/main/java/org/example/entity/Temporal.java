package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@PrimaryKeyJoinColumn(name = "DNI_EMPLEADO")
@Table(name = "EMPLEADOS_TEMPORALES")
public class Temporal extends Empleado implements Serializable {

    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Column(name = "FECHA_FIN")
    private Date fechaFin;

    @Column(name = "PAGO_DIA")
    private float pagoDia;

    @Column(name = "SUPLEMENTO")
    private float suplemento;

    @Column(name = "SULEDO")
    private float sueldo;

    @OneToMany(mappedBy = "dniEmpleado")
    private Set<Venta> ventas;

    public Temporal(String dni, String nombre, String telefono, float porcentajeRetencion, Date fechaInicio, Date fechaFin, float pagoDia) {
        super(dni, nombre, telefono, porcentajeRetencion);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pagoDia = pagoDia;
    }

    @Override
    public void calcularNomina() {
        sueldo = pagoDia * ((fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24)) *
                ( 1 - getPorcentajeRetencion()) + getSuplemento();
    }

    @Override
    public String toString() {
        String temporal = "DNI: " + super.getDni() + "\n"
                + "NOMBRE: " + super.getNombre() + "\n"
                + "TELÉFONO: " + super.getTelefono() + "\n"
                + "PORCENTAJE DE RENTENCIÓN: " + super.getPorcentajeRetencion() + "\n"
                + "FECHA DE INICIO: " + getFechaInicio() + "\n"
                + "FECHA DE FIN: " + getFechaFin() + "\n"
                + "PAGO DÍA: " + getPagoDia() + "\n"
                + "SUPLEMENTO: " + getSuplemento() + "\n"
                + "SUELDO: " + getSueldo() + "\n";
        return temporal;
    }
}
