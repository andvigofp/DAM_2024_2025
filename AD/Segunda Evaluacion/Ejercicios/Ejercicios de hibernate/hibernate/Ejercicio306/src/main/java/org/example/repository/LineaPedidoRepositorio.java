package org.example.repository;

import org.example.entity.Cliente;
import org.example.entity.LineaPedido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class LineaPedidoRepositorio {
    Session session;

    public LineaPedidoRepositorio() {
    }

    public LineaPedidoRepositorio(Session session) {
        this.session = session;
    }

    public void mostrarTodosPedidos() {
        Transaction trx = this.session.beginTransaction();

        Query query = this.session.createQuery("select lp from LineaPedido lp");
        ArrayList<LineaPedido> listaPedidos = (ArrayList<LineaPedido>) query.getResultList();

        for (LineaPedido lPedido: listaPedidos)
            System.out.println(lPedido.toString());

        trx.commit();
    }

    public void mostrarPedidosCliente(Cliente cliente) {
        Transaction trx = this.session.beginTransaction();

        Query query = this.session.createQuery("select lp from LineaPedido lp where lp.pedido.cliente.idCliente=:idCliente");

        query.setParameter("idCliente", cliente.getIdCliente());

        ArrayList<LineaPedido> listaPedidos = (ArrayList<LineaPedido>) query.getResultList();

        for (LineaPedido lPedido: listaPedidos)
            System.out.println(lPedido.toString());

        trx.commit();
    }
}
