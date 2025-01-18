package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSql {
    private static Connection conn;
    private final String usuario ="postgres";
    private final String clave = "abc123.";
    private final String url = "jdbc:postgresql://localhost:5432/academia";

    private DatabaseSql() {
        try {
            if (conn==null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, usuario, clave);
            }
        }catch (SQLException e) {
            System.out.println("Error. No se pudo establecer la conexión " + e.toString());
        }
    }

    public static Connection getInstance() {
        if (DatabaseSql.conn == null) {
            new DatabaseSql();
        }
        return DatabaseSql.conn;
    }

    public static void cerrarConexion() {
        try {
            if (conn !=null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada con éxito");
            }
        } catch (SQLException e) {
            System.out.println("Error. no se pudo cerrar la conexión");
        }
    }
}
