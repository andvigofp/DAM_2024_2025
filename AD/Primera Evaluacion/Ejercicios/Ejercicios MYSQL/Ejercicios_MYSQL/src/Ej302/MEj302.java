package Ej302;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MEj302 {
    // Método para la conexión BD de empleados
    public void conection() {
        String usuario = "root";
        String clave = "abc123.";
        String url= "jdbc:mysql://localhost:3306/empleados";

        Connection conn;

        try {
            conn = DriverManager.getConnection(url, usuario, clave);
            amosarInformacionBD(conn);
            amosarProyectos(conn);
            insertarProyectos(conn,11, "Base de datos", "Lugo", 3);
            amosarProyectos(conn);
            eliminarProyecto(conn, 11);
            amosarProyectos(conn);
            conn.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para amosarInformacion BD (Tip de Gestor, Conector, URL y usuario)
    private void amosarInformacionBD(Connection conn) {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String xestor = metaData.getDatabaseProductName();
            String conector = metaData.getDriverName();
            String url = metaData.getURL();
            String usuario = metaData.getUserName();

            System.out.println("Información de la base de datos");
            System.out.println("-----------------------------------");
            System.out.println("Xestor: " + xestor);
            System.out.println("Conector: " + conector);
            System.out.println("URL: " + url);
            System.out.println("Usuario: " + usuario);
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para mostrar todo lo que contiene la columna de Proyecto, de BD empelados
    private void amosarProyectos(Connection conn) {
        Statement stmt;
        ResultSet rs;
        String sql = "select * from proyecto";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("Proxectos");
            while (rs.next()) {
                System.out.println("\tNúmero: " + rs.getInt("Numproy") +
                        ", Nome: " + rs.getString("Nombreproy") +
                        ", Lugar: " + rs.getString("Lugarproy") +
                        ", Departamento: " + rs.getString("departamento_Numdep"));
            }
            stmt.close();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para insertar valores en la columna proyecto de la BD empleados
    private void insertarProyectos(Connection coon, int num, String nome, String lugar, int dep) {
        PreparedStatement ps;
        Statement st;
        String sql = "INSERT INTO proyecto VALUES (?, ?, ?, ?);";

        try {
           ps = coon.prepareStatement(sql);
           st = coon.createStatement();
           ps.setInt(1, num);
           ps.setString(2, nome);
           ps.setString(3, lugar);
           ps.setInt(4, dep);

           int numTuplas = ps.executeUpdate();
           System.out.println("Setenza: " + ps.toString());
           System.out.println("Tuplas afectadas: " + numTuplas);

           st.close();
           ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para eliminar un valor de una columna Proyecto de la BD empleados
    private void eliminarProyecto(Connection conn, int num) {
        PreparedStatement ps;
        Statement st;
        String sql = "DELETE FROM proyecto WHERE Numproy = ?;";

        try {
            ps = conn.prepareStatement(sql);
            st = conn.createStatement();
            ps.setInt(1, num);
            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenzas: " + toString());
            System.out.println("Tuplas afectas: " + numTuplas);

            st.close();
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
