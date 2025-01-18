package Ej309;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Operaciones {
    private Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url = "jdbc:mysql://localhost:3306/library";

    public Operaciones() {
        openConnection();
    }

    private void openConnection() {
        try {
            this.conn = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException sqle) {
            System.out.println("Error al abrir la conexión");
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método si existe el cliente por idCliente
    public boolean existeCliente(String idCliente) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM client where idClient = ?");

            ps.setString(1, idCliente);

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next())
                count++;

            return (count > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }
    // Método si esxiste el libro por codigo del libro
    public boolean existeLibro(String code) {
        PreparedStatement ps;
        try {
            // Cambiar el campo a 'code' para que coincida con la búsqueda por código de libro
            ps = this.conn.prepareStatement("SELECT * FROM book WHERE code = ?");

            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();
            // Verificar si existe alguna fila con el código de libro proporcionado
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // Método para prestar un libro
    public boolean prestado(String code) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement(
                    "SELECT l.borrowed FROM book as b, loan as l WHERE b.code = ? and b.idBook = l.idBook and l.borrowed = true");

            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();
            boolean borrowed = false;
            while (rs.next())
                borrowed = borrowed || rs.getBoolean("borrowed");

            return borrowed;

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // Método para prestar un libro
    public void prestarLibro(String code, String idCliente) {
        if (!existeCliente(idCliente))
            System.out.println("No existe el cliente");
        if (prestado(code))
            System.out.println("El libro ya está siendo prestado");

        try {
            PreparedStatement psIdLibro = this.conn.prepareStatement("SELECT idBook FROM book" + " WHERE code = ?");

            psIdLibro.setString(1, code);

            ResultSet rsIdLibro = psIdLibro.executeQuery();
            int idLibro = -1;
            while (rsIdLibro.next())
                idLibro = rsIdLibro.getInt("idBook");
            if (idLibro == -1) {
                System.out.println("Error en el código del libro");
                closeConnection();
                return;
            }

            PreparedStatement ps = this.conn
                    .prepareStatement("INSERT INTO loan (idBook, idClient, date, borrowed)" + " VALUES (?, ?, ?, ?)");

            ps.setInt(1, idLibro);
            ps.setInt(2, Integer.parseInt(idCliente));
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, true);

            if (ps.executeUpdate() <= 0)
                System.out.println("Se ha producido un error al insertar un nuevo préstamo");

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    // Métododo para devolver un libro
    public void devolverLibro(String code) {
        // Verificar que el libro existe en la base de datos
        if (!existeLibro(code)) {
            System.out.println("El libro no existe en la base de datos");
            return;
        }

        // Verificar si el libro está prestado
        if (!prestado(code)) {
            System.out.println("El libro no está siendo prestado");
            return;
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(
                    "UPDATE loan AS l, book AS b SET l.borrowed = false " +
                            "WHERE b.idBook = l.idBook AND b.code = ? AND l.borrowed = true"
            );

            ps.setString(1, code);

            if (ps.executeUpdate() <= 0) {
                System.out.println("Se ha producido un error al devolver un préstamo");
            } else {
                System.out.println("El libro ha sido devuelto con éxito");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    // Métedodo para listar los libros que pueden ser prestados
    public ArrayList<Libro> listarLibrosPrestado() {
        PreparedStatement ps;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        try {
            ps = this.conn.prepareStatement("SELECT b.idBook, b.code, b.title, b.authors, b.year FROM"
                    + " book as b, loan as l where b.idBook = l.idBook and l.borrowed = true");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaLibros.add(new Libro(rs.getInt("idBook"), rs.getString("code"), rs.getString("title"),
                        rs.getString("authors"), rs.getInt("year")));
            }

            return listaLibros;

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return listaLibros;
        }
    }

    public ArrayList<Libro> listaLibrosPuedePrestar() {
        PreparedStatement ps;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        try {
            ps = this.conn.prepareStatement("SELECT b.idBook, b.code, b.title, b.authors, b.year FROM"
                    + " book as b, loan as l where b.idBook = l.idBook and l.borrowed = false");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaLibros.add(new Libro(rs.getInt("idBook"), rs.getString("code"), rs.getString("title"),
                        rs.getString("authors"), rs.getInt("year")));
            }

            return listaLibros;

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return listaLibros;
        }
    }
}

