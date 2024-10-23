package Ej302;

import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MEj302 {
    // Método para la conexion de la base de datos
    public void conection() {
        String usuario= "root";
        String clave= "abc123.";
        String url= "jdbc:mysql://localhost:3306/empleados";

        Connection conn;

        try {
            conn = DriverManager.getConnection(url, usuario, clave);
            amosarInformacionBD(conn);
            amosarProyectos(conn);
            insertarProyecto(conn, 11, "Base de datos", "Lugo", 3);
            amosarProyectos(conn);
            eliminarProyecto(conn, 11);
            amosarProyectos(conn);
            conn.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    // Método para amosarInformacion BD (Tipo de Gestor , Conector, url y usuario)
    private void amosarInformacionBD(Connection conn) {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String xestor = metaData.getDatabaseProductName();
            String conector = metaData.getDriverName();
            String url = metaData.getURL();
            String usuario = metaData.getUserName();

            System.out.println("Informacion de la base de datos");
            System.out.println("-----------------------------------");
            System.out.println("Xestor: " + xestor);
            System.out.println("Conector: " + conector);
            System.out.println("URL: " + url);
            System.out.println("Usuario: " + usuario);
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para mostar todo lo contiene de la columna Proyecto, de la BD empleddos
    private void amosarProyectos(Connection conn) {
        Statement stmt;
        ResultSet rs;
        String sql = "SELECT * from proyecto";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("Proxectos");
            while (rs.next()) {
                System.out.println("\tNúmero: " + rs.getInt("Numproy") +
                        ", Nome: " + rs.getString("NombreProy") +
                        ", Lugar: " + rs.getString("Lugarproy") +
                        ", Departamento " + rs.getInt("departamento_Numdep"));
            }
                stmt.close();
                rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Método para insertar valores de la columna proyecto en la BD empleados
    private void insertarProyecto(Connection conn, int nun, String nome, String lugar, int dep) {
        PreparedStatement ps;
        Statement st;
        String sql = "INSERT INTO proyecto VALUES (?,?,?,?);";

        try {
            ps = conn.prepareStatement(sql);
            st = conn.createStatement();
            ps.setInt(1, nun);
            ps.setString(2, nome);
            ps.setString(3, lugar);
            ps.setInt(4, dep);

            int numTuplas = ps.executeUpdate();
            System.out.println("Setenza:  " + ps.toString());
            System.out.println("Tuplas afectadas: " + numTuplas);

            st.close();
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para elimninar un valor en la columna Proyecto de la BD empleados
    private void eliminarProyecto(Connection conn, int num) {
        PreparedStatement ps;
        Statement st;
        String sql = "DELETE FROM proyecto WHERE Numproy = ?;";

        try {
            ps = conn.prepareStatement(sql);
            st = conn.createStatement();
            ps.setInt(1,num);
            int numTuplas = ps.executeUpdate();
            System.out.println("Sentenzas " + ps.toString());
            System.out.println("Tupas afectadas: " + numTuplas);

            st.close();
            ps.close();
        }catch (SQLException ex) {
            Logger.getLogger(MEj302.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
