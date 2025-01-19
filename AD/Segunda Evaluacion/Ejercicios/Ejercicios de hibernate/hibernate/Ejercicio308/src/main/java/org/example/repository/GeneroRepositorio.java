package org.example.repository;

import org.example.entity.Genero;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GeneroRepositorio implements Repositorio<Genero>{
    private Session session;

    public GeneroRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Genero t) {
        this.session.persist(t);
    }

    @Override
    public void eliminar(Genero t) {
        this.session.remove(t);
    }

    @Override
    public Genero buscarPorId(int id) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select g from Genero g where g.idGenero=:idGenero");
        query.setParameter("idGenero", id);

        Genero genero = (Genero) query.getSingleResult();
        trx.commit();

        return genero;
    }

    @Override
    public List<Genero> buscarPorCadena(String cadena) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select g from Genero g where g.nombre=:cadena");
        query.setParameter("cadena", cadena);

        ArrayList<Genero> listaGenero = (ArrayList<Genero>) query.getResultList();
        trx.commit();
        return listaGenero;
    }

    @Override
    public void actaulizar(Genero t) {
        this.session.update(t);
    }

    public List<Genero> buscarTodo() {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select g from Genero g");

        ArrayList<Genero> listaGenero = (ArrayList<Genero>) query.getResultList();
        trx.commit();
        return listaGenero;
    }
}
