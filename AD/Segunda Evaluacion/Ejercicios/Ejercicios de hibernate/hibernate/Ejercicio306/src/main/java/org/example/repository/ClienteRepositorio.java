package org.example.repository;

import org.example.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClienteRepositorio {
    Session session;


    public ClienteRepositorio() {
    }

    public ClienteRepositorio(Session session) {
        this.session = session;
    }

    public Cliente obtenerClientePorDNI(String dni) {
        Transaction trx = this.session.beginTransaction();
        Cliente cliente;

        try {
            Query query = this.session.createQuery("select c from Cliente c where c.dni=:dni");
            query.setParameter("dni", dni);
            cliente = (Cliente) query.getSingleResult();
        }catch (Exception e) {
            cliente = new Cliente(-1);
        }

        trx.commit();

        return cliente;
    }
}
