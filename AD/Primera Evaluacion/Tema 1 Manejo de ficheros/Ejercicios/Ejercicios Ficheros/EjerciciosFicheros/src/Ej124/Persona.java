package Ej124;

public class Persona {
    private String id; // Agregar atributo id
    private String nombre;
    private String dni; // Agregar atributo dni
    private int edad;
    private double salario; // Agregar atributo salario
    private boolean borrado;

    public Persona(String id, String nombre, String dni, int edad, double salario, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.salario = salario;
        this.borrado = borrado;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getEdad() {
        return edad;
    }

    public double getSalario() {
        return salario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                ", borrado=" + borrado +
                '}';
    }
}