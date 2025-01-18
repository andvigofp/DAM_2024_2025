package Ej307;

import java.sql.*;
import java.util.ArrayList;

/**
 * También debes añadirle los métodos que consideres necesarios.
 *
 * Por otra parte, deberás crear una clase llamada ManageStudents, que permitirá realizar diferentes operaciones sobre la base de datos del instituto. La clase tendrá, como mínimo, los siguientes atributos y métodos:
 *
 * Connection connection.
 * void openConnection().
 * void closeConnection().
 * boolean addStudent(Student student): añade un nuevo estudiante a la base de datos.
 * Student getStudent(String id): obtiene la información de un estudiante determinado.
 * boolean deleteStudent(String id): borra la información de un estudiante determinado.
 * boolean modifyStudent(Student student): modifica los datos de un estudiante determinado, si ya existe en la base de datos.
 * ArrayList<Student> getStudentsList(): devuelve una lista con todos los estudiantes almacenados en la BD.
 */
public class ManageStudents {
    private Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private String url = "jdbc:mysql://localhost:3306/school";


    public ManageStudents() {
        openConnection();
    }

    // Método para barir la conexion a la BD school
    private void openConnection() {
        try {
            this.conn = DriverManager.getConnection(url, usuario, clave);
        }catch (SQLException ex) {
            System.out.println("Error al abrir la conexíon " + ex.toString());
        }
    }

    // Método para cerrar la conexión a la BD school
    public void closeConection() {
        try {
            this.conn.close();
        }catch (SQLException ex) {
            System.out.println("Error al cerrar la oonexión " + ex.toString());
        }
    }

    // Método para añadir un estudiante a la BD school
    public boolean addStudent(Student student) {
        try {
            PreparedStatement checkStmt = this.conn.prepareStatement("SELECT id FROM student WHERE id = ?");
            checkStmt.setString(1, student.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Si el ID ya existe, devuelve false y muestra un mensaje
                System.out.println("Error: El ID " + student.getId() + " ya existe. No se puede añadir el estudiante.");
                return false;
            }
            PreparedStatement ps = this.conn.prepareStatement(" INSERT INTO student VALUES (?, ?, ?, ?)");

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAge());

            return (ps.executeUpdate() > 0);

        }catch (SQLException ex) {
            System.out.println("Error al insertar el estudiante a la base de datos " + ex.toString());
            ex.printStackTrace();
            closeConection();
            return false;
        }
    }

    // Método para obtener información de un estudiante detreminado BD school
    public Student getStudent(String id) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM student WHERE id = ?");

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            Student st = null;

            while (rs.next()) {
                st = new Student(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getInt("age"));
            }
            return st;

        }catch (SQLException ex) {
            System.out.println("Error al intentar obtener informacion de ese estudiante " + ex.toString());
            ex.printStackTrace();
            closeConection();
            return new Student();
        }

    }

    // Método para borrar al estudiante de la BD school
    public boolean deleteSudent(String id) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM student WHERE id = ?");

            ps.setString(1, id);

            return (ps.executeUpdate() > 0);

        }catch (SQLException ex) {
            System.out.println("Error al borrar el estudiante de la base de datos " + ex.toString());
            ex.printStackTrace();
            closeConection();
            return false;
        }
    }

    // Método para modificar el estudiante de la BD school
    public boolean modifyStudent(Student student) {
        try {
            PreparedStatement ps = this.conn.prepareStatement("UPDATE student set id = ?, name = ?,"
                    + "surname = ?, age = ? where id = ?");

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAge());
            ps.setString(5, student.getId());

            return (ps.executeUpdate() > 0);

        }catch (SQLException ex) {
            System.out.println("Error al intentar modificar el estudiante de la base de datos " + ex.toString());
            ex.printStackTrace();
            closeConection();
            return false;
        }
    }

    // Método para listar todos los estudiantes de la BD school
    public ArrayList<Student> getSudentsList() {
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();

            ArrayList<Student> listaEstudiantes = new ArrayList<>();

            while (rs.next()) {
                listaEstudiantes.add(new Student(rs.getString("id"), rs.getString("name"),
                        rs.getString("surname"), rs.getInt("age")));
            }
            return listaEstudiantes;

        }catch (SQLException ex) {
            System.out.println("Error al listar los estudiantes de la base de datos " + ex.toString());
            ex.printStackTrace();
            closeConection();
            return new  ArrayList<Student>();
        }
    }
}

