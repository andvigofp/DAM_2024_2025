package org.example.repository;

import org.example.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClienteRepositorio {

    private Session session;

    public ClienteRepositorio(Session session) {
        this.session = session;
    }

    public Cliente obtenerCiente(int idCliente) {
        Transaction trx = this.session.beginTransaction();

        // Cambiar "cliente" a "Cliente" para que coincida con la anotación @Entity
        Query query = this.session.createQuery("select c from Cliente c where c.idCliente=:id");
        query.setParameter("id", idCliente);

        Cliente cliente = (Cliente) query.getSingleResult();

        trx.commit();

        return cliente;
    }

    public void anhadirCliente(Cliente cliente) {
        Transaction trx = this.session.beginTransaction();

        this.session.persist(cliente);

        trx.commit();
    }

    public void modicarCliente(Cliente cliente) {
        Transaction trx = this.session.beginTransaction();

        this.session.update(cliente);

        trx.commit();
    }

    public boolean borrarCliente(Cliente cliente) {
        try {
            Transaction trx = this.session.beginTransaction();

            this.session.remove(cliente);

            trx.commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
