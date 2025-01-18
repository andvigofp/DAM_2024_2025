package Ej120;

import java.io.Serializable;

public class Piscina implements Serializable {
    private static final long serialVersionUID = 1L; // Asegura la compatibilidad en la serialización

    private long id;       //Identificador numérico
    private String dni;     //DNI: 8 números + 1 carácter [A-Z]
    private int edad;       //Edad
    private float salario;  //Salario

    // Constructor con todos los atributos
    public Piscina(long id, String dni, int edad, float salario) {
        this.id = id;
        this.dni = validarDNI(dni) ? dni : null;  // Valida el formato del DNI
        this.edad = edad;
        this.salario = salario;
    }

    // Getters y Setters
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
        if (validarDNI(dni)) {
            this.dni = dni;
        } else {
            System.out.println("DNI inválido. Debe tener el formato 8 dígitos seguido de una letra.");
        }
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

    // Método para validar el formato del DNI
    private boolean validarDNI(String dni) {
        return dni.matches("\\d{8}[A-Z]");  // 8 dígitos seguidos de una letra mayúscula
    }

    // Método toString para representar los datos de la piscina
    @Override
    public String toString() {
        return "Piscina{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                '}';
    }
}