package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {

    @Id
    @Column(name="CODIGO", unique=true, nullable=false)
    private String codigo;

    @Column(name="STOCK_ALMACEN")
    private int stockActualAlmacen;

    @Column(name="STOCK_MINIMO")
    private final int STOCK_MINIMO = 30;

    @Column(name="PRECIO_UNITARIO")
    private float precioUnitario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CIF_EMPRESA")
    private Empresa empresa;

    @OneToMany(mappedBy="codigoArticulo")
    private Set<Venta> ventas;

    public Producto(String codigo, int stockAlmacen, float precioUnitario) {
        this.codigo = codigo;
        this.stockActualAlmacen = stockAlmacen;
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        String producto = "CÓDIGO: " + codigo + "\n"
                + "STOCK ALMACÉN: " + stockActualAlmacen + "\n"
                + "STOCK MÍNIMO: " + STOCK_MINIMO + "\n"
                + "PRECIO UNITARIO: " + precioUnitario + "\n";

        return producto;
    }
}
