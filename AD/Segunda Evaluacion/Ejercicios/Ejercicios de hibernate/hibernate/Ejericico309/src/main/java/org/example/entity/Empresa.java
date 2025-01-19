package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "EMPRESAS")
@NamedQueries({
        @NamedQuery(name="empleadosEmpresa", query="select empleados from Empresa where cif=:cif"),
        @NamedQuery(name="productosEmpresa", query="select productos from Empresa where cif=:cif"),
        @NamedQuery(name="empresas", query="from Empresa")
})
public class Empresa implements Serializable {

    @Id
    @Column(name = "CIF", unique = true, nullable = false)
    private String cif;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    @OneToMany(mappedBy = "empresa")
    private Set<Producto> productos;

    @OneToMany(mappedBy = "empresa")
    private Set<Empleado> empleados;



    public Empresa(String cif) {
        super();
        this.cif = cif;
    }

    public Empresa(String cif, String nombre, String direccion, String telefono) {
        super();
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        String empresa = "CIF: " + getCif() + "\n" + "NOMBRE: " + getNombre() + "\n"
                + "DIRECCIÓN: " + getDireccion() + "\n" + "TELÉFONO: " + getTelefono() + "\n";
        return empresa;
    }
}
