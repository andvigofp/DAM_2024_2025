package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "alquiler")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlquiler;

    @Temporal(TemporalType.DATE) // Añadir esta línea para indicar que es de tipo DATE
    private Date fecha;

    private boolean alquilado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    private Libro libro;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Alquiler(Date fecha, boolean alquilado) {
        super();
        this.fecha = fecha;
        this.alquilado = alquilado;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
        libro.addAlquiler(this);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.addAlaquiler(this);
    }
}
