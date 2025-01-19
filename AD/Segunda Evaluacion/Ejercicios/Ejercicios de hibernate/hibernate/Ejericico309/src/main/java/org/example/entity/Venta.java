package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor

@Entity
@Table(name = "VENTAS")
public class Venta implements Serializable {


    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "CODIGO_ARTICULO")
    private Producto codigoArticulo;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DNI_EMPLEADO")
    private Empleado dniEmpleado;

    @Id
    @Column(name = "FECHA_VENTA")
    private Date fechaVenta;

    @Id
    @Column(name = "HORA")
    private Time hora;

    @Column(name = "NUMERO_UNIDADES")
    private int numeroUnidades;

    @Column(name = "IMPORTE")
    private float importe;

    public Venta(Producto codigoArticulo, Empleado dniEmpleado, Date fechaVenta, Time hora, int numeroUnidades, float importe) {
        this.codigoArticulo = codigoArticulo;
        this.dniEmpleado = dniEmpleado;
        this.fechaVenta = fechaVenta;
        this.hora = hora;
        this.numeroUnidades = numeroUnidades;
        this.importe = importe;
    }
}
