package Ej505;

import Ej506.MEj506;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej505 {
    private static Connection conn;

    public static void main(String[] args) {
        String dbURL = "jdbc:postgresql://localhost/hospital";
        String user = "postgres";
        String clave = "abc123.";

        try {
            conn = DriverManager.getConnection(dbURL, user, clave);

            Scanner teclado = new Scanner(System.in);

            MEj505 metodos = new MEj505();
            metodos.Menu(teclado, conn);

        }catch (SQLException e) {
            System.out.println("Error de conexión " + e.toString());
        }
    }
}

