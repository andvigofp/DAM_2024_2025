package Ej102;

import java.util.Scanner;

/**
 * Realiza un programa en Java que pida por teclado el nombre y la edad de una persona mayor de edad (se debe validar el valor introducido) y responda indicando:
 *
 * Si no está jubilado (edad de jubilación = 65 años), cuántos años le quedan para jubilarse.
 * Si ya está jubilada indicará cuántos años lleva jubilado.
 */
public class Ej102A {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            String nombre;
            int edad = 0;
            System.out.println("Introduzca el nombre de la persona");
            nombre = sc.nextLine();

                try {
                    System.out.println("Introduzca la edad");
                    edad = sc.nextInt();
                    if (edad < 18) {
                        System.out.println(nombre + " tiene que ser mayor de edad");
                    } else if(edad > 65) {
                        System.out.println(nombre + " lleva jubilada desde hace: " + Integer.toString(edad - 65) + " años");
                    }else if(edad == 65)
                        System.out.println(nombre + " se jubila este año");
                    else {
                        System.out.println(nombre + " se va a jubilar dentro de: " + Integer.toString(65 - edad) + " años");
                    }
                }catch (Exception e) {
                    System.out.println("Error en el valor introducido");
                }
            }
        }