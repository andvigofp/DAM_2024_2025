package org.example.repository;

import jakarta.persistence.NoResultException;
import org.example.HibernateUtil;
import org.example.entity.Libro;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LibroRepositorio implements Repositorio<Libro> {
    private Session session;

    public LibroRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void insertarUno(Libro t) {
        try (Session session = HibernateUtil.get().openSession()) {
            Transaction trx = session.beginTransaction();
            try {
                session.persist(t);
                trx.commit();
            } catch (Exception e) {
                if (trx != null) trx.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void borrar(Libro t) {
        try (Session session = HibernateUtil.get().openSession()) {
            Transaction trx = session.beginTransaction();
            try {
                // Inicializar la colección `listaAutores` dentro de la sesión actual
                t = session.merge(t); // Asegurar que el objeto esté gestionado por la sesión actual
                Hibernate.initialize(t.getListaAutores());

                // Eliminar las relaciones en la tabla intermedia `Libros_Autores` utilizando `INNER JOIN`
                Query deleteLibrosAutores = session.createNativeQuery(
                        "DELETE la " +
                                "FROM Libros_Autores la " +
                                "INNER JOIN Libros l ON la.idLibro = l.idLibro " +
                                "WHERE l.idLibro = :id"
                );
                deleteLibrosAutores.setParameter("id", t.getId());
                deleteLibrosAutores.executeUpdate();

                // Limpiar la lista de autores del libro para evitar referencias circulares
                t.getListaAutores().clear();
                session.update(t);

                // Finalmente eliminar el libro
                session.remove(t);
                trx.commit();
                System.out.println("Libro borrado correctamente.");
            } catch (Exception e) {
                if (trx != null) trx.rollback();
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Libro> encontrarTodos() {
        try (Session session = HibernateUtil.get().openSession()) {
            Query query = session.createQuery("SELECT l FROM Libro l");
            return query.getResultList();
        }
    }

    @Override
    public Libro encontrarUnoPorString(String nombre) {
        try (Session session = HibernateUtil.get().openSession()) {
            Query query = session.createQuery("SELECT l FROM Libro l WHERE l.titulo=:nombre");
            query.setParameter("nombre", nombre);
            try {
                return (Libro) query.getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No se encontró un libro con el título: " + nombre);
                return null;
            }
        }
    }


    @Override
    public void actualizar(Libro t) {
        try (Session session = HibernateUtil.get().openSession()) {
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
}
