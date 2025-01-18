package org.example.repository;

import org.example.entity.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpRepositorio implements Repositorio<Emp> {

    private Session session;

    public EmpRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Emp t) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();  // Iniciar transacción
            session.persist(t);               // Persistir la entidad
            trx.commit();                     // Confirmar transacción
        } catch (Exception e) {
            if (trx != null) trx.rollback();  // Revertir cambios en caso de error
            throw e;                          // Propagar la excepción
        }
    }
}
