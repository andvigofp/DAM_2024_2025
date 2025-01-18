package Ej122;


import java.io.Serializable;

public class Persona  implements Serializable {
    private long id;
    private String dni;
    private int edad;
    private float salario;

    public Persona(long id, String dni, int edad, float salario) {
        this.id = id;
        this.dni = dni;
        this.edad = edad;
        this.salario = salario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                '}';
    }
}