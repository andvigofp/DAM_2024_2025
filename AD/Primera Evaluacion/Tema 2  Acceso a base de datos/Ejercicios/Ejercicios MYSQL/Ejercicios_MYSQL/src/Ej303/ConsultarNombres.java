package Ej303;

import java.sql.*;
import java.util.Scanner;

/**
 * una clase llamada ConsultaNombres que devuelva los nombres de los empleados que empiezan por una letra determinada. Esta letra será introducida por el usuario.
  */

public class ConsultarNombres {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String usuario = "root";
        String clave = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empleados";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, usuario, clave);

            System.out.println("Introduzca la letra por la quería buscar.");

            String letra = "";
            while (letra.equals("") || letra.length() !=1)
                letra = teclado.next().toUpperCase();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from empleado where Nombre like '" + letra + "%'");

            while (rs.next()) {
                System.out.println("\tNSS: " + rs.getInt("NSS") +
                ", Nombre: " + rs.getString("Nombre") +
                ", Apellido 1: " + rs.getString("Apel1") +
                ", Apellido 2: " + rs.getString("Apel2") +
                ", Sexo: " + rs.getString("Sexo") +
                ", Dirección: " + rs.getString("Dirección") +
                ", Fecha Nacimient: " + rs.getString("Fechanac") +
                ", Salario: " + rs.getString("Salario") +
                ", Núm. Departamento: " + rs.getString("Numdept") +
                ", NSSsup: " + rs.getString("NSSsup"));
            }

        }catch (SQLException ex) {
            System.out.println("Error de conexión " + ex.toString());
        }finally {
            try {
                if (conn !=null) conn.close();
                teclado.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
