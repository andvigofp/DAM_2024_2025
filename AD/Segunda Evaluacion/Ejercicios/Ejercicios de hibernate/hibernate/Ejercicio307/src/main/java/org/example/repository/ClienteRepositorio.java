package org.example.repository;

import org.example.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClienteRepositorio {

    Session session;

    public ClienteRepositorio(Session session) {
        this.session = session;
    }

    public Cliente getClientePorId(int idCliente) {
        Transaction trx = this.session.beginTransaction();

        Query query = this.session.createQuery("select c from Cliente c where c.idCliente=:idCliente");
        query.setParameter("idCliente", idCliente);

        Cliente cliente = (Cliente) query.getSingleResult();

        trx.commit();

        return cliente;
    }
}
