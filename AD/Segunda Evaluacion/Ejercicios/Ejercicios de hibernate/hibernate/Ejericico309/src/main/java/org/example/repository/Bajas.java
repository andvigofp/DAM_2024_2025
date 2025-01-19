package org.example.repository;

import org.example.entity.Fijo;
import org.example.entity.Temporal;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Bajas {
    Session session;

    public Bajas(Session session) {
        this.session = session;
    }

    public void baja(Scanner teclado) {
        byte op = 0;
        do {
            System.out.println("---------------------------------------------\n"
                    + "\t\tBAJAS\n\n");
            Cadenas.menuEmpleados();
            op = ControlData.leerByte(teclado);
            switch (op) {
                case 1:
                    fijo(teclado);
                    break;
                case 2:
                    temporal(teclado);
                    break;
                case 0:
                    Cadenas.vueltaAnteriorMenu();
                    break;
                default:
                    Cadenas.mesajeDefaultMenu();
                    break;
            }
        } while (op != 0);

    }

    private void fijo(Scanner teclado) {
        System.out.println("BAJA EMPLEADO FIJO");
        System.out.println("DNI:");
        String dni = ControlData.leerDni(teclado);


        Transaction trx = this.session.beginTransaction();

        Fijo emplFijo = (Fijo) session.get(Fijo.class, dni);
        trx.commit();

        if (emplFijo == null) {
            System.out.println("\nEL EMPLEADO FIJO " + dni + " NO EXISTE Y POR LO TANTO NO PUEDE SER BORRADO");
        } else {
            System.out.println("\n" + emplFijo);
            System.out.println("\nEL AUTOR, SUS DATOS Y SUS LIBROS SERÁN BORRADOS (SI ESTOS LIBROS TAMBIÉN TIENEN OTROS AUTORES ESTOS SERÁN BORRADOS CON TODOS SUS LIBROS)\n");
            this.session.delete(emplFijo);
        }

    }

    private void temporal(Scanner teclado) {
        System.out.println("BAJA EMPLEADO TEMPORAL");
        System.out.println("DNI:");
        String dni = ControlData.leerDni(teclado);


        Transaction trx = this.session.beginTransaction();

        Temporal emplTmp = (Temporal) session.get(Temporal.class, dni);
        trx.commit();

        if (emplTmp == null) {
            System.out.println("\nEL EMPLEADO FIJO " + dni + " NO EXISTE Y POR LO TANTO NO PUEDE SER BORRADO");
        } else {
            System.out.println("\n" + emplTmp);
            System.out.println("\nEL AUTOR, SUS DATOS Y SUS LIBROS SERÁN BORRADOS (SI ESTOS LIBROS TAMBIÉN TIENEN OTROS AUTORES ESTOS SERÁN BORRADOS CON TODOS SUS LIBROS)\n");
            this.session.delete(emplTmp);
        }
    }

}

