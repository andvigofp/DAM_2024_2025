package org.example.repository;

import org.example.entity.Dpto;
import org.example.entity.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpRepositorio implements Repositorio<Emp>{

    private Session session;

    public EmpRepositorio(Session session) {
        super();
        this.session = session;
    }

    @Override
    public void guardar(Emp t) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();  // Iniciar transacción
            session.persist(t);               // Persistir la entidad
            trx.commit();                     // Confirmar transacción
        } catch (Exception e) {
            if (trx != null) trx.rollback();  // Revertir cambios en caso de error
            throw e;                          // Propagar la excepción
        }
    }

    public void añadirEmpleado(int idDepartamento, Emp empleado) {
        Transaction trx = this.session.beginTransaction();

        // Obtenemos el departamento
        Dpto dpto = (Dpto) session.createQuery("SELECT d FROM Dpto d WHERE d.id = :id")
                .setParameter("id", idDepartamento)
                .getSingleResult();

        // Asociamos el empleado al departamento
        dpto.addEmps(empleado);

        // Persistimos el empleado (si es nuevo)
        this.session.persist(empleado);

        trx.commit();
    }

    public void actualizarJefeDepartamento(int idDepart, int idEmpl) {
        Transaction trx = this.session.beginTransaction();

        // Recuperamos al empleado que pertenece al departamento y que corresponde al id del empleado
        Query query = session.createQuery("SELECT e FROM Emp e WHERE e.dptoElments.id = :id1 AND e.id = :id2");
        query.setParameter("id1", idDepart);
        query.setParameter("id2", idEmpl);

        // Obtenemos el empleado
        Emp emp = (Emp) query.getSingleResult();

        // Actualizamos el estado del empleado
        emp.setEsJefe(true);

        // Usamos merge en lugar de persist para actualizar el empleado existente
        this.session.merge(emp);

        trx.commit();
    }

}
