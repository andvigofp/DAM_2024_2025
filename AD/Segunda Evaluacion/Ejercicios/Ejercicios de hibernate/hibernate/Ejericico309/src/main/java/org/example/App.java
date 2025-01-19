package org.example;

import org.example.repository.*;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {

    static Scanner teclado;

    public static void main(String[] args) {
        System.out.println("Ejercicio 309");

        Scanner teclado = new Scanner(System.in);

        Session session = HibernateUtil.get().openSession();

        byte opc = 0;

        do {
            Cadenas.menuPrincipal();
            opc= ControlData.leerByte(teclado);

            switch (opc) {
                case 1:
                    new Altas(session).alta(teclado);
                    break;
                case 2:
                    new Bajas(session).baja(teclado);
                    break;
                case 3:
                    new Modificaciones(session).modificacion(teclado);
                    break;
                case 4:
                    new Consultas(session).consulta(teclado);
                    break;
                case 0:
                    Cadenas.fin();
                    break;
                default:
                    Cadenas.mesajeDefaultMenu();
                    break;
            }
        }while (opc != 0);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    public static int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }

    public static Double pedirDouble(String string) {
        System.out.println(string);
        return teclado.nextDouble();
    }

    public static String pedirString(String string) {
        System.out.println(string);
        return teclado.next();
    }
}
