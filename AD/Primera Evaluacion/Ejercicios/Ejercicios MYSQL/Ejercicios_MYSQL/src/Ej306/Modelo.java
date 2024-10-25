package Ej306;


import java.sql.*;

public class Modelo {
    private final Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url = "jdbc:mysql://localhost:3306/empleados";

    public Modelo() throws SQLException {
        conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
    }

    public String obtenerDatosEmpleado(String numeroEmpleado) {
        String resultadoString="";

        try {
            Statement smt = conn.createStatement();

            ResultSet rs = smt.executeQuery("select * from empleado where NSS = " + numeroEmpleado);

            while (rs.next()) {
                resultadoString = "NSS: " + rs.getString("NSS") +
                                "\nNombre: " + rs.getString("Nombre") +
                                "\nApellido 1:" + rs.getString("Apel1") +
                                "\nApellido 2: " + rs.getString("Apel2") +
                                "\nSexo: " + rs.getString("Sexo") +
                                "\nDirección: " + rs.getString("Dirección") +
                                "\nFecha Nacimiento: " + rs.getString("Fechanac") +
                                "\nSalario: " + rs.getString("Salario") +
                                "\nNúmero Departamento: " + rs.getString("Numdept") +
                                "\nNSSsup: " + rs.getString("NSSsup");
            }
            conn.close();
        }catch (SQLException ex) {
            return "Error de conexión " + ex.toString();
        }
        return resultadoString;
    }
}
