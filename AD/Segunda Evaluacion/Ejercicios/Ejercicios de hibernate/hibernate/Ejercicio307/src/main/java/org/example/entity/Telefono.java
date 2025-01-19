package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "telefono")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTelefono")
    private int idTelefono;
    private String descripcion;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    public Telefono(String descripcion, String numero) {
        this.descripcion = descripcion;
        this.numero = numero;
    }
}
