package Ej104;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Piscina {
    private double longitudPsicina;
    private double anchuraPiscina;
    private double longitudParcela;
    private double anchuraParcela;

    //Constructor
    public Piscina() {
    }

    //Método para pedir datos
    public void pedirDatos() {
        Scanner teclado = new Scanner(System.in);

        //Pedir longitud de la piscina
        longitudPsicina = socilitarValor(teclado, "Longitud de la piscina");

        //Pedir anchura de la piscina
        anchuraPiscina = socilitarValor(teclado, "Anchura de la piscina");

        //Pedir longitud de la parcela
        longitudParcela = socilitarValor(teclado, "Longitud de la parcela");

        //Pedir anchura de la parcela
        anchuraParcela = socilitarValor(teclado, "Anchura de la parcela");
    }

    //Mátodo para calcular el aforo
    public int calcularAforo() {
        //Calcular superficies
        double superficiePiscina = longitudPsicina * anchuraPiscina;
        double supercieParcela = longitudParcela * anchuraParcela;

        //Calcular aforo (2 metros cuadrados por persona)
        int aforoPiscina = (int) (superficiePiscina /2);
        int aforoParcela = (int) (supercieParcela /2);

        //Devolver el menor valor entre ambos aforos
        return Math.min(aforoPiscina, aforoParcela);
    }

    public double socilitarValor(Scanner teclado, String descripcion) {
        double valor=-1;
        boolean valorValido=false;

        while (!valorValido) {
            try {
                System.out.println("Introduzca " + descripcion + ":");
                valor = teclado.nextDouble();
                valorValido=true;
            }catch (InputMismatchException e) {
                System.out.println("Ha ocurrido una excepción. solo se permiten números.");
                teclado.next();
            }
        }
        return valor;
    }

}
