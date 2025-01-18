package Ej304;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Implementa un programa en Java donde mediante una clase llamada TransaccionEmpleado se permita insertar en la base de datos Empleados tres nuevos contables: Pedro, Lucía y Daniel que pertenecerán al departamento 1.
 *
 * ¿Qué pasa si hay un error al insertar alguno de los empleados?
 *
 * Utiliza transacciones para controlar que se inserten los 3 empleados a la vez y si ocurre un error, no se insertará ninguno.
 */
public class TransaccionEmpleado {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String usuario = "root";
        String clave = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empleados";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, usuario, clave);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO empleado(NSS, Nombre, Numdept) VALUES (?, ?, ?)");

            conn.setAutoCommit(false);

            ps.setInt(1, 1);
            ps.setString(2, "Pedro");
            ps.setInt(3, 1);
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "Lucía");
            ps.executeUpdate();

            ps.setInt(1, 3);
            ps.setString(2, "Daniel");
            ps.executeUpdate();

            conn.commit();


            System.out.println("Todos los datos han sido introducidos.");


        } catch (SQLException ex) {
            System.out.println("Error en la conexión: " + ex.toString());
        } finally {
            try {
                /**
                 * Si ocurre un error durante la inserción de alguno de los datos, la transacción no se lleva a cabo
                 * y con ROLLBACK deshacemos los cambios que se hayan realizado como parte de la transacción. De
                 * esta forma los datos de la BD no quedan en un estado inestable.
                 */
                conn.rollback();
                if (conn != null) conn.close();
                teclado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
