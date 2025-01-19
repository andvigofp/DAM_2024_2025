package org.example.repository;

import org.example.entity.Producto;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductoRepositorio {
    Session session;

    public ProductoRepositorio() {
    }

    public ProductoRepositorio(Session session) {
        this.session = session;
    }

    public Producto getProducto(int idProducto) {
        try {
            Query query = this.session.createQuery("select p from Producto p where p.idProducto=:idProducto");
            query.setParameter("idProducto", idProducto);

            Producto producto = (Producto) query.getSingleResult();

            return producto;

        }catch (Exception e) {
            return new Producto(-1);
        }
    }
}
