package org.example.repository;

import org.example.entity.Telefono;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TelefonoRepositorio implements Repositorio<Telefono> {
    private Session session;

    public TelefonoRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void insertarUno(Telefono t) {
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
    public void borrar(Telefono t) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.remove(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Telefono> encontrarTodos() {
        return null;
    }

    @Override
    public Telefono encontrarUnoPorString(String nombre) {
        return null;
    }

    @Override
    public void actualizar(Telefono t) {
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
