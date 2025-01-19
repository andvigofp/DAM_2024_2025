package org.example.repository;

import org.example.entity.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonaRepositorio implements Repositorio<Persona>{
    private Session session;

    public PersonaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Persona t) {
        Transaction trx = session.beginTransaction();
        this.session.persist(t);

        trx.commit();
    }

    @Override
    public void eliminar(Persona t) {
        this.session.remove(t);
    }

    @Override
    public Persona buscarPorId(int id) {
        Transaction trx = this.session.beginTransaction();
        Query query = null;
        Persona persona = null;
        try {
            query = this.session.createQuery("select p from Actriz p where p.idPersona=:idPersona");
            query.setParameter("idPersona", id);
            persona = (Persona) query.getSingleResult();
        }catch (Exception e) {
            try {
                query = this.session.createQuery("select p from Actor p where p.idPersona=:idPersona");
                query.setParameter("idPersona", id);
                persona = (Persona) query.getSingleResult();
            }catch (Exception e1) {
                System.out.println("Error recuperando valores actor");
            }

        }
        trx.commit();

        return persona;
    }

    @Override
    public List<Persona> buscarPorCadena(String cadena) {
        Transaction trx = this.session.beginTransaction();
        ArrayList<Persona> listaPersona = null;
        Query query = null;
        try {
            query = this.session.createQuery("select p from Persona p where p.anhoNacimiento > :cadena");
            query.setParameter("cadena", cadena);
            listaPersona = (ArrayList<Persona>) query.getResultList();
        }catch (Exception e) {
            try {
                query = this.session.createQuery("select p from Actor p where p.anhoNacimiento > :cadena");
                query.setParameter("cadena", cadena);
                listaPersona = (ArrayList<Persona>) query.getResultList();
            }catch (Exception e1) {
                System.out.println("Error recuperando valores actor");
            }

        }

        trx.commit();
        return listaPersona;
    }

    public List<Persona> buscarPorAnho(int cadena) {
        Transaction trx = this.session.beginTransaction();
        ArrayList<Persona> listaPersona = null;
        Query query = null;
        try {
            query = this.session.createQuery("select p from Actriz p where p.anhoNacimiento > :cadena");
            query.setParameter("cadena", cadena);
            listaPersona = (ArrayList<Persona>) query.getResultList();
        }catch (Exception e) {
            try {
                query = this.session.createQuery("select p from Actor p where p.anhoNacimiento > :cadena");
                query.setParameter("cadena", cadena);
                listaPersona = (ArrayList<Persona>) query.getResultList();
            }catch (Exception e1) {
                System.out.println("Error recuperando valores actor");
            }

        }

        trx.commit();
        return listaPersona;
    }

    @Override
    public void actaulizar(Persona t) {
        this.session.update(t);
    }

    public List<Persona> buscarPorMejorActriz(int mejorActriz) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select p from Actriz p where p.numeroMejorActriz >= :mejorActriz");
        query.setParameter("mejorActriz", mejorActriz);

        ArrayList<Persona> listaPersona = (ArrayList<Persona>) query.getResultList();
        trx.commit();
        return listaPersona;
    }

    public List<Persona> buscarPorMejorActor(int mejorActor) {
        Transaction trx = this.session.beginTransaction();
        Query query = this.session.createQuery("select p from Actor p where p.numeroOscars >= :numeroOscars");
        query.setParameter("numeroOscars", mejorActor);

        ArrayList<Persona> listaPersona = (ArrayList<Persona>) query.getResultList();
        trx.commit();
        return listaPersona;
    }
}
