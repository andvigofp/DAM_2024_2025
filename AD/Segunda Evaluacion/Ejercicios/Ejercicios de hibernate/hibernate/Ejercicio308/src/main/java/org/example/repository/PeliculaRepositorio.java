package org.example.repository;

import org.example.entity.Pelicula;
import org.example.entity.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PeliculaRepositorio implements Repositorio<Pelicula>{
    private Session session;

    public PeliculaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Pelicula t) {
        this.session.persist(t);
    }

    @Override
    public void eliminar(Pelicula t) {
        this.session.remove(t);
    }

    @Override
    public Pelicula buscarPorId(int id) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select p from Pelicula p where p.idPelicula=:idPelicula");
        query.setParameter("idPelicula", id);

        Pelicula pelicula = (Pelicula) query.getSingleResult();
        trx.commit();

        return pelicula;
    }

    @Override
    public List<Pelicula> buscarPorCadena(String cadena) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select p from Pelicula p where p.titulo = :cadena");
        query.setParameter("cadena", cadena);

        ArrayList<Pelicula> listaPelicula = (ArrayList<Pelicula>) query.getResultList();
        trx.commit();
        return listaPelicula;
    }

    @Override
    public void actaulizar(Pelicula t) {
        this.session.update(t);
    }

    public List<Pelicula> buscarTodo() {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select p from Pelicula p");

        ArrayList<Pelicula> listaPelicula = (ArrayList<Pelicula>) query.getResultList();
        trx.commit();
        return listaPelicula;
    }

    public List<Pelicula> peliculasPorPersona(Persona p) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select pel from Pelicula pel, Persona p, listaPeliculas lp "
                + "where pel.idPelicula=lp.idPelicula and lp.idPersona=:idPersona");

        query.setParameter("idPersona", p.getIdPersona());
        ArrayList<Pelicula> listaPelicula = (ArrayList<Pelicula>) query.getResultList();
        trx.commit();
        return listaPelicula;
    }
}
