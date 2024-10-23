package Ej123;

public class Persona {
    private String dni;
    private String nombre;
    private int edad;
    private double salario;
    private boolean borrado; // Este atributo es opcional.

    public Persona() {
    }

    public Persona(String dni, String nombre, int edad, double salario, boolean borrado) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
        this.borrado = borrado;
    }

    // Getters y Setters



    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                ", borrado=" + borrado +
                '}';
    }
}