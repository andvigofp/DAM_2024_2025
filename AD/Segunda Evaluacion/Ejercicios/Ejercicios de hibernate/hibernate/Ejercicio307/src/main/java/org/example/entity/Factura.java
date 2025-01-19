package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private int idFactura;
    private String descripcion;
    private double precio;

    @Temporal(TemporalType.DATE) // Añadir esta línea para indicar que es de tipo DATE
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    public Factura(String descripcion, double precio, Date fecha) {
        super();
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
    }



    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.addFactura(this);
    }

    @Override
    public String toString() {
        return "ID: " + idFactura +"\nCLIENTE: " + cliente +
                "\nDESCRIPCION: " + descripcion + "\nPRECIO:" + precio;
    }
}
