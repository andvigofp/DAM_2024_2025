package Ej311;

import java.sql.*;

public class GestorBanco {

    private Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url ="jdbc:mysql://localhost:3306/banco";

    public GestorBanco() {
        openConnection();
    }

    private void openConnection() {
        try {
            this.conn = DriverManager.getConnection(url, usuario, clave);
        }catch(SQLException sqle) {
            System.out.println("Error al abrir la conexión");
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        }catch(SQLException sqle) {
            System.out.println("Error al cerrar la conexión");
        }
    }

    public int getId(String DNI) {
        int id = -1;
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT id FROM Cliente where dni = ?");

            ps.setString(1, DNI);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
                id = rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // Método para crear una cuenta bancaria
    public boolean crearCuenta(int id_cliente, float dinero) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO Cuenta_bancaria (dinero, id_cliente) VALUES (?, ?)");

            ps.setInt(2, id_cliente);
            ps.setFloat(1, dinero);

            return (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // Método para borrar una cuenta bancaria
    public boolean borrarCuenta(int id_cliente) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM Cuenta_bancaria WHERE id_cliente = ?");

            ps.setInt(1, id_cliente);

            return (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // Método para ingresar y reirrar dinero
    public boolean ingresarRetirarDinero(boolean ingresar, String dni, int cuenta, float dinero) throws SQLException {
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT dinero FROM Cuenta_bancaria as cb, Cliente as c where c.id = cb.id_cliente and c.dni = ? and cb.numero = ?");

            ps.setString(1, dni);
            ps.setInt(2, cuenta);

            ResultSet rs = ps.executeQuery();

            float saldo = 0;
            while(rs.next())
                saldo = rs.getFloat("dinero");

            if (ingresar)
                saldo += dinero;
            else {
                saldo -= dinero;
                if (saldo < 0)
                    return false;
            }

            ps = this.conn.prepareStatement("UPDATE Cuenta_bancaria as cb, Cliente as c SET cb.dinero = ? WHERE c.id = cb.id_cliente and c.dni = ? and cb.numero = ?");

            ps.setFloat(1, saldo);
            ps.setString(2, dni);
            ps.setInt(3, cuenta);

            return (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // Método para ingresar dinero en la cuenta bancaria
    public void ingreso(String dni, int cuenta, float dinero) throws SQLException {
        try {
            this.conn.setAutoCommit(false);

            ingresarRetirarDinero(true, dni, cuenta, dinero);

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn.rollback();
            closeConnection();
        }
    }

    // Método para retirar dinero en la cuenta bancaria
    public void retiro(String dni, int cuenta, float dinero) throws SQLException {
        try {
            this.conn.setAutoCommit(false);

            ingresarRetirarDinero(false, dni, cuenta, dinero);

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.conn.rollback();
            closeConnection();
        }
    }

    // Método para transferir dinero en una cuenta bancaria a otra cuenta
    public boolean transferencia(String DNI, int cuenta1, int cuenta2, float dinero) throws SQLException {
        try {
            this.conn.setAutoCommit(false);

            boolean esPosible = ingresarRetirarDinero(false, DNI, cuenta1, dinero);
            if (!esPosible) {
                this.conn.rollback();
                return false;
            }

            ingresarRetirarDinero(true, DNI, cuenta2, dinero);

            this.conn.commit();
            this.conn.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            this.conn.rollback();
            closeConnection();
            return false;
        }
    }

    // Método para mostrar los datos de una cuenta bancaria
    public void mostrarCuentasBancarias(String dni) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT cb.* FROM Cuenta_bancaria as cb, Cliente as c where c.id = cb.id_cliente and c.dni = ?");

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
                System.out.println("Cuenta bancaria: " + rs.getInt("numero") + " Saldo: " + rs.getString("dinero"));

            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }
}

