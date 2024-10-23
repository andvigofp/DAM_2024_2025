package Ej303;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MEj303 {
    private static final String URL = "jdbc:mysql://localhost:3306/empleados";
    private static final String USER = "root";
    private static final String PASSWORD = "abc123.";


    public void borrarEmpleado(int numEmpleado) {
        // Conexión a la base de datos
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Crear la consulta SQL para borrar
            String sql = "DELETE FROM empleado WHERE numero = ?;";

            // Preparar la sentencia
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, numEmpleado);

                // Ejecutar el borrado
                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Empleado con número " + numEmpleado + " ha sido eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún empleado con ese número.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta en la base de datos.");
            e.printStackTrace();
        }
    }
}