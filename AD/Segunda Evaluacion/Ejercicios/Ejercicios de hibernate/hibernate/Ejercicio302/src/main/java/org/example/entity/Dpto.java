package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "dptos")
public class Dpto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpto_id")
    private int id;
    private String nombre;
    private String localidad;

    @OneToMany(mappedBy = "dptoElments", cascade = CascadeType.ALL)
    private List<Emp> empts;

    public Dpto(String nombre, String localidad) {
        super();
        this.nombre =nombre;
        this.localidad = localidad;
        this.empts = new ArrayList<Emp>();
    }


    // Método para agregar empleados
    public void addEmps(Emp emp) {
        this.empts.add(emp);        // Agrega el empleado a la lista del departamento
        emp.setDptoElments(this);  // Establece este departamento como el departamento del empleado
    }

}

