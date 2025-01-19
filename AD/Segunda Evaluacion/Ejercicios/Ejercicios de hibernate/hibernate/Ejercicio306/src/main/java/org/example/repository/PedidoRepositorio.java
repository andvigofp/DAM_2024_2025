package org.example.repository;

import org.example.entity.Cliente;
import org.example.entity.LineaPedido;
import org.example.entity.Pedido;
import org.example.entity.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PedidoRepositorio {
    Session session;
    Scanner scanner;
    ProductoRepositorio productoRepositorio;

    public PedidoRepositorio() {
    }

    public PedidoRepositorio(Session session, Scanner teclado) {
        this.session = session;
        this.scanner = teclado;
        this.productoRepositorio = new ProductoRepositorio(session);
    }

    public void eliminarPedido(int idPedido) {
        Transaction trx = this.session.beginTransaction();

        try {
            Query query = this.session.createQuery("select p from Pedido p where p.idPedido=:idPedido");
            query.setParameter("idPedido", idPedido);
            Pedido pedido = (Pedido) query.getSingleResult();

            this.session.remove(pedido);
        }catch (Exception e) {
            System.out.println("No existe el pedido con ese ID");
        }
        trx.commit();
    }

    public void addPedido(Cliente cliente) {
        ArrayList<LineaPedido> lineaPedidos = new ArrayList<LineaPedido>();

        Timestamp fecha = new Timestamp(new Date().getTime());
        Pedido pedido = new Pedido(fecha, cliente);

        String opcion = "";

        do {
            int idProducto = pintarPedirInt("Introduce el id del producto");
            Producto producto = productoRepositorio.getProducto(idProducto);

            if (producto.getIdProducto() != -1) {
                LineaPedido lPedido = new LineaPedido(idProducto, producto, pedido);
                int cantidad = pintarPedirInt("Introduzca la cantidad de productos");
                lPedido.setCantidad(cantidad);
                lineaPedidos.add(lPedido);
            }
            opcion= pintarPedirString("Quieres seguir introduciendo productos al pedido? (y/n)");
        }while (!opcion.toLowerCase().equals("n"));
    }

    public int pintarPedirInt(String mensaje) {
        System.out.println(mensaje);
        return this.scanner.nextInt();
    }

    public String pintarPedirString(String mensaje) {
        System.out.println(mensaje);
        return this.scanner.next();
    }
}
