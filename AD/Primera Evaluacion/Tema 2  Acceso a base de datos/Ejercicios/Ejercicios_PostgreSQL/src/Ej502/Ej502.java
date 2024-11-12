package Ej502;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Usando Java como lenguaje de programación realiza las siguientes consultas en la siguiente base de datos PostgreSQL:
 *
 * Listar todos los equipos y sus directores
 * Obtener los pilotos y sus equipos actuales
 * Resultados de una carrera específica
 * Piloto más viejo
 * Número de victorias por equipo
 */

public class Ej502 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String dbURL = "jdbc:postgresql://localhost:5432/formula1";
        String user = "postgres";
        String clave = "abc123.";

        try (Connection conn = DriverManager.getConnection(dbURL, user, clave)){
            Statement statement = conn.createStatement();

            MEj502 metodos = new MEj502();

            metodos.Menu(teclado,  statement);

        }catch (SQLException e) {
            System.out.println("Error de conexión " + e.toString());
        }
    }
}
