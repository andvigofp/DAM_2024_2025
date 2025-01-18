package org.example.repository;

import org.hibernate.Session;
import org.example.entity.Dpto;
import org.hibernate.Transaction;

public class DptoRepositorio implements Repositorio<Dpto> {

    private Session session;

    public DptoRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Dpto dpto) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(dpto);  // Usar saveOrUpdate para evitar duplicados
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}