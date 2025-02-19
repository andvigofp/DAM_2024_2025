/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andrés Fernández Pereira
 */

//Módelo de datos de un traballador
public class Traballador {
    private String dni;
    private String nome;
    private String apelido1;
    private String apelido2;
    private String provincia;
    private String profesion;

    public Traballador(String dni, String nome, String apelido1, String apelido2, String provincia, String profesion) {
        this.dni = dni;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.provincia = provincia;
        this.profesion = profesion;
    }

    public String getDni() {
        return dni;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getProfesion() {
        return profesion;
    }
    
    
    @Override
    public String toString() {
        return "Traballador{" + "dni=" + dni + ", nome=" + nome + ", apelido1=" + apelido1 + ", apelido2=" + apelido2 + ", provincia=" + provincia + ", profesion=" + profesion + '}';
    }    
}
