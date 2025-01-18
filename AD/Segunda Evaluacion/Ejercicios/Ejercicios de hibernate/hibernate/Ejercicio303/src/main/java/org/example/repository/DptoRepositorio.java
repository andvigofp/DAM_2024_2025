package org.example.repository;

import jakarta.persistence.NoResultException;
import org.example.entity.Dpto;
import org.example.entity.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DptoRepositorio implements Repositorio<Dpto>{
    private Session session;

    public DptoRepositorio(Session session) {
        super();
        this.session = session;
    }


    @Override
    public void guardar(Dpto t) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(t);  // Usar saveOrUpdate para evitar duplicados
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void visualizarDepartamento(int id) {
        Dpto depto = (Dpto) session.createQuery("SELECT d FROM Dpto d WHERE d.id = :id")
                .setParameter("id", id)
                .getSingleResult(); // Asegúrate de usar :id en lugar de concatenar directamente.

        System.out.println("Id: " + depto.getId() + "\nNombre: " + depto.getNombre() + "\nLocalidad: " + depto.getLocalidad());

        Query query = session.createQuery("SELECT e FROM Emp e WHERE e.dptoElments.id = :id");
        query.setParameter("id", id);

        List<Emp> empleados = query.getResultList();

        for (Emp empleado : empleados) {
            System.out.println(empleado.toString());
        }
    }

    public void eliminarDepartamento(int id) {
        Transaction trx = this.session.beginTransaction();
        try {
            Dpto depto = (Dpto) session.createQuery("SELECT d FROM Dpto d WHERE d.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();

            session.remove(depto);
            trx.commit();  // Confirma la transacción
        } catch (NoResultException e) {
            System.out.println("No se encontró el departamento con id: " + id);
            trx.rollback();  // Si hay un error, revertimos la transacción
        } catch (Exception e) {
            System.out.println("Error al eliminar departamento: " + e.getMessage());
            trx.rollback();  // Revertir en caso de error inesperado
        }
    }
}


