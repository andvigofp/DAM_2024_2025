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

    @Column(name = "DNI", columnDefinition = "char")
    private String dni;

    private String nombre;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Alquiler> listaAlquileres;

    public Cliente(String dni, String nombre, String email) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    public void addAlaquiler(Alquiler alquiler) {
        this.listaAlquileres.add(alquiler);
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + "]";
    }
}
