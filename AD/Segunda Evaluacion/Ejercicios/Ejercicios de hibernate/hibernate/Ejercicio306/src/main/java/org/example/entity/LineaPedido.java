package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "lineaPedido")
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLineaPedido")
    private int idLineaPedido;
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    public LineaPedido(int cantidad, Producto producto, Pedido pedido) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "LineaPedido [idLineaPedido=" + idLineaPedido + ", cantidad=" + cantidad + ", producto=" + producto.getNombre()
                + ", pedido=" + pedido.getIdPedido() + "]";
    }
}
