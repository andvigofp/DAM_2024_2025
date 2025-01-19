package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Telefonos")
public class Telefono implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "DniAutor", referencedColumnName = "DniAutor")
    private Autor autor;

    @Column(name = "NumeroTf")
    private String numeroTf;

    public Telefono(Autor autor, String numeroTf) {
        this.autor = autor;
        this.numeroTf = numeroTf;
    }
}
