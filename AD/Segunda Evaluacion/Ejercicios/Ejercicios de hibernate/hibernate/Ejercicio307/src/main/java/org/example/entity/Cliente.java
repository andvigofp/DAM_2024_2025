package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;
    private String nombre;
    private String direccion;

    @OneToMany(mappedBy = "idTelefono", cascade = CascadeType.ALL)
    private List<Telefono> listaTelefonos;

    @OneToMany(mappedBy = "idFactura", cascade = CascadeType.ALL)
    private List<Factura> listaFacturas;

    public Cliente(String nombre, String direccion) {
        super();
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public void addFactura(Factura factura) {
        this.listaFacturas.add(factura);
    }


    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        for(Telefono tlTelefono : listaTelefonos)
            sBuilder.append(tlTelefono.getDescripcion() + ": " + tlTelefono.getNumero() + " ");

        return nombre + ", " + direccion + ", telefonos (" + sBuilder.toString() + ")";
    }
}
