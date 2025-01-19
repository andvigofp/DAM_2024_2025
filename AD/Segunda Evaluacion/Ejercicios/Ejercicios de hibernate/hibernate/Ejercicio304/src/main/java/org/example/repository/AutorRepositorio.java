package org.example.repository;

import jakarta.persistence.NoResultException;
import org.example.HibernateUtil;
import org.example.entity.Autor;
import org.example.entity.Libro;
import org.example.entity.Telefono;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class AutorRepositorio implements Repositorio<Autor> {
    private Session session;
    private TelefonoRepositorio telefonoRepositorio;

    public AutorRepositorio(Session session) {
        this.session = session;
        this.telefonoRepositorio = new TelefonoRepositorio(session);
    }

    @Override
    public void insertarUno(Autor t) {
        Transaction trx = session.beginTransaction();
        try {
            session.persist(t);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void borrar(Autor t) {
        try (Session session = HibernateUtil.get().openSession()) {
            Transaction trx = session.beginTransaction();
            try {
                // Inicializar la colección `listaLibros` dentro de la sesión actual
                t = session.merge(t); // Asegurar que el objeto esté gestionado por la sesión actual
                Hibernate.initialize(t.getListaLibros());

                // Eliminar las relaciones en la tabla intermedia `Libros_Autores`
                Query deleteLibrosAutores = session.createNativeQuery("DELETE FROM Libros_Autores WHERE DniAutor = :dni");
                deleteLibrosAutores.setParameter("dni", t.getDniAutor());
                deleteLibrosAutores.executeUpdate();

                // Eliminar las relaciones en la tabla `Telefonos` utilizando `INNER JOIN` con `DniAutor`
                Query deleteTelefono = session.createNativeQuery(
                        "DELETE t " +
                                "FROM Telefonos t " +
                                "INNER JOIN Autores a ON t.DniAutor = a.DniAutor " + // Usar el nombre correcto de la columna en la tabla `Telefonos`
                                "WHERE a.DniAutor = :dni"
                );
                deleteTelefono.setParameter("dni", t.getDniAutor());
                deleteTelefono.executeUpdate();


                // Limpiar la lista de libros del autor para evitar referencias circulares
                t.getListaLibros().clear();
                session.update(t);

                // Finalmente eliminar el autor
                session.remove(t);
                trx.commit();
                System.out.println("Autor borrado correctamente.");
            } catch (Exception e) {
                if (trx != null) trx.rollback();
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Autor> encontrarTodos() {
        Query<Autor> query = session.createQuery("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.listaLibros", Autor.class);
        return query.getResultList();
    }

    @Override
    public Autor encontrarUnoPorString(String dni) {
        Query<Autor> query = session.createQuery("SELECT a FROM Autor a LEFT JOIN FETCH a.listaLibros WHERE a.dniAutor=:dni", Autor.class);
        query.setParameter("dni", dni);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró un autor con el DNI: " + dni);
            return null;
        }
    }

    @Override
    public void actualizar(Autor t) {
        Transaction trx = session.beginTransaction();
        try {
            session.update(t);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            e.printStackTrace();
        }
    }
}
