package org.example.repository;

import org.example.entity.Alquiler;
import org.example.entity.Cliente;
import org.example.entity.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class AlquilerRepositorio {

    private Session session;

    public AlquilerRepositorio(Session session) {
        this.session = session;
    }

    public boolean esAlquilado(int idLibro) {
        // Cambiar la consulta para acceder correctamente al atributo libro.idLibro
        Query<Alquiler> query = this.session.createQuery("select a from Alquiler a where a.libro.idLibro = :idLibro", Alquiler.class);
        query.setParameter("idLibro", idLibro);

        try {
            List<Alquiler> listaAlquileres = query.getResultList();
            for (Alquiler alquiler : listaAlquileres) {
                if (alquiler.isAlquilado())
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void alquilar(int idLibro, int idCliente) {
        Transaction trx = this.session.beginTransaction();

        try {
            // Cambiar la consulta para acceder correctamente a la entidad Libro
            Query<Libro> comprobarLibro = this.session.createQuery("select l from Libro l where l.idLibro = :idLibro", Libro.class);
            comprobarLibro.setParameter("idLibro", idLibro);
            Libro libro = comprobarLibro.getSingleResult();

            if (!esAlquilado(idLibro)) {
                try {
                    // Cambiar la consulta para acceder correctamente a la entidad Cliente
                    Query<Cliente> comprobarCliente = this.session.createQuery("select c from Cliente c where c.idCliente = :idCliente", Cliente.class);
                    comprobarCliente.setParameter("idCliente", idCliente);
                    Cliente cliente = comprobarCliente.getSingleResult();

                    Alquiler alquiler = new Alquiler(new Date(), true);
                    alquiler.setCliente(cliente);
                    alquiler.setLibro(libro);
                    alquiler.setAlquilado(true);
                    this.session.persist(alquiler);
                    System.out.println("Libro alquilado");
                } catch (Exception e) {
                    System.out.println("No existe el cliente");
                }
            } else {
                System.out.println("Libro ya alquilado");
            }
            trx.commit();
        } catch (Exception e) {
            System.out.println("Libro no existe");
        }
    }

    public void devolver(int idLibro) {
        if (esAlquilado(idLibro)) {
            Transaction trx = this.session.beginTransaction();

            // Cambiar la consulta para acceder correctamente al atributo libro.idLibro
            Query<Alquiler> query = this.session.createQuery("select a from Alquiler a where a.libro.idLibro = :idLibro and a.alquilado = true", Alquiler.class);
            query.setParameter("idLibro", idLibro);
            Alquiler alquiler = query.getSingleResult();

            alquiler.setAlquilado(false);

            this.session.update(alquiler);

            trx.commit();
        } else {
            System.out.println("No se puede devolver un libro que no fue alquilado");
        }
    }
}
