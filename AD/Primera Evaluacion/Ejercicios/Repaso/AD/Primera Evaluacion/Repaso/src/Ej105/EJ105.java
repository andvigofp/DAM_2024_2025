package Ej105;


import java.util.Scanner;

public class EJ105 {
    public static void main(String[] args) {
        // Crear una instancia de Ejercicio_Piscina
       Piscina piscina = new Piscina();

        // Pedir los datos necesarios
        piscina.pedirDatos();

        // Realizar reservas
        boolean seguirReservando = true;
        Scanner teclado = new Scanner(System.in);

        while (seguirReservando) {
            piscina.reservar();

            // Preguntar si se desea realizar otra reserva
            System.out.println("¿Desea hacer otra reserva? (SI/NO)");
            String respuesta = teclado.nextLine();
            if (!respuesta.equalsIgnoreCase("SI")) {
                seguirReservando = false;
            }
        }

        teclado.close();
    }
}