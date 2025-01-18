import Aplicacion.MEJExamen;
import Conexion.DatabaseSql;

import java.sql.Connection;

public class Lanzador {
    public static void main(String[] args) {
        Connection connPostgres = DatabaseSql.getInstance();
        System.out.println("Conexión conectado con éxito " + connPostgres);

        MEJExamen metodos = new MEJExamen();
        metodos.Menu();

        DatabaseSql.cerrarConexion();
    }
}