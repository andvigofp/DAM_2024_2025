package org.example.repository;

import org.example.entity.Cliente;
import org.example.entity.Factura;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FacturaRepositorio {
    Session session;
    Scanner scanner;

    public FacturaRepositorio(Session session, Scanner teclado) {
        this.session = session;
        this.scanner = teclado;
    }

    public void mostrarTodasFacturas() {
        Query query = this.session.createQuery("select f from Factura f");
        ArrayList<Factura> listaFacturas = (ArrayList<Factura>) query.getResultList();

        for (Factura f: listaFacturas)
            System.out.println(f.toString());
    }

    public void addFactura(Cliente cliente) {
        String descripcion = pedirString("Introduce la descripción de la factuta");
        Double precio = pedirDouble("Introduce el precio de la factura");

        Date fecha = new Date();
        Factura factura = new Factura(descripcion, precio, fecha);
        factura.setCliente(cliente);
        this.session.persist(factura);
    }

    public void modificarFactura() {
        int idFactura = pedirInt("Introduzca el ID de la factura a modificar");
        String descripcion = pedirString("Introduzca la nueva descripción. Déjelo en blanco si quiere conservar la anterior");
        double precio = pedirDouble("Introduzca la nueva descripción. -1 para mantener la anterior");

        Transaction trx = this.session.beginTransaction();
        Factura factura = getFacturaId(idFactura);

        if (!descripcion.isEmpty())
            factura.setDescripcion(descripcion);

        if (precio != -1)
            factura.setPrecio(precio);

        this.session.persist(factura);

        trx.commit();
    }

    private Factura getFacturaId(int idFactura) {
        Query query = this.session.createQuery("select f from Factura f where f.idFactura=:idFactura");
        query.setParameter("idFactura", idFactura);

        Factura factura = (Factura) query.getSingleResult();

        return factura;
    }

    public void borrarFactura() {
        int idFactura = pedirInt("Introduce el ID de la factura");

        Transaction trx = this.session.beginTransaction();

        Query query = this.session.createQuery("select f from Factura f where f.idFactura=:idFactura");
        query.setParameter("idFactura", idFactura);

        Factura factura = (Factura) query.getSingleResult();

        this.session.remove(factura);

        trx.commit();
    }

    private int pedirInt(String string) {
        System.out.println(string);
        return this.scanner.nextInt();
    }

    private Double pedirDouble(String string) {
        System.out.println(string);
        return this.scanner.nextDouble();
    }

    private String pedirString(String string) {
        System.out.println(string);
        return this.scanner.next();
    }
}
