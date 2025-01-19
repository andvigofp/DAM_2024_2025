package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp; // Usar Timestamp en lugar de Date
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private int idPedido;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // Ajustar la columna
    private Timestamp fecha;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<LineaPedido> listaPedidos;

    public Pedido(Timestamp fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", cliente=" + cliente + ", listaPedidos="
                + listaPedidos + "]";
    }
}
