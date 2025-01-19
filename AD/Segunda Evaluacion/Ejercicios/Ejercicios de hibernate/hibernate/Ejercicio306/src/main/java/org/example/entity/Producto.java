package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private int idProducto;

    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<LineaPedido> listaPedidos;

    public Producto(int idProducto) {
        super();
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", precio=" + precio + ", imagen=" + imagen + ", listaPedidos=" + listaPedidos + "]";
    }
}
