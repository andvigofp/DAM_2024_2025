package org.example.repositorio;

import org.example.entidades.Actor;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActorRepositorio {
    private Session session;

    public ActorRepositorio(Session session) {
        this.session = session;
    }

    public void crearActor(Actor actor) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(actor);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
