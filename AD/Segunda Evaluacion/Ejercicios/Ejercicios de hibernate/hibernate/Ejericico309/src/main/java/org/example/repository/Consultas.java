package org.example.repository;

import org.example.entity.Empleado;
import org.example.entity.Empresa;
import org.example.entity.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class Consultas {
    Session session;

    public Consultas(Session session) {
        this.session = session;
    }

    public void consulta(Scanner teclado) {
        byte op = 0;
        do {
            Cadenas.menuConsultas();
            op = ControlData.leerByte(teclado);
            switch (op) {
                case 1:
                    empresasEmpleados(teclado);
                    break;
                case 2:
                    productosEmpresa(teclado);
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

    public void empresasEmpleados(Scanner teclado) {

        Transaction trx = this.session.beginTransaction();
        List<Empresa> totalEmp = session.createNamedQuery("empresas").list();

        for (Empresa empresa : totalEmp) {
            System.out.println("\tEMPRESA "+empresa);
            System.out.println("\tLSITA DE EMPLEADOS:");
            for (Empleado empleado : empresa.getEmpleados()) {
                System.out.println(empleado);
            }
            System.out.println("-------------------------");
        }

        trx.commit();
    }

    public void productosEmpresa(Scanner teclado) {

        System.out.println("CIF DE LA EMPRESA:");
        String cif = ControlData.leerString(teclado);

        Transaction trx = this.session.beginTransaction();

        System.out.println("\nLISTA DE PRODUCTOS DE LA EMPRESA:\n");
        List<Producto> productos = session.createNamedQuery("productosEmpresa").setParameter("cif", cif).list();
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        trx.commit();
    }


}

