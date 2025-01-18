package Ej303;

import java.sql.*;
import java.util.Scanner;

/**
 * una clase llamada BorradoEmpleados que permita borrar un empleado con un número determinado. Este número será introducido por el usuario.
 */

public class BorrarEmpleados {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String usuario = "root";
        String clave = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empleados";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, usuario, clave);

            System.out.println("Introduzca el número de empleado a borrar.");

            int numero= -1;
            while (numero < 0)
                numero = teclado.nextInt();

                // Desactivamos los foreign_key
                Statement stm = conn.createStatement();
                stm.execute("SET FOREIGN_KEY_CHECKS=0");

                PreparedStatement ps = conn.prepareStatement("DELETE FROM empleado WHERE NSS = ?");
                ps.setInt(1, numero);
                int numTuplas = ps.executeUpdate();

                System.out.println("Sentencia: " + ps.toString());
                System.out.println("Tuplas afectadas: " + numTuplas);

                ps.close();

                // Volvemos a activar foreign_key
                stm = conn.createStatement();
                stm.execute("SET FOREIGN_KEY_CHECKS=1");
        }catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.toString());
        }finally {
            try {
                if (conn != null) conn.close();
                teclado.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
