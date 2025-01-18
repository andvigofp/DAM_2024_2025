package Aplicacion;

import Conexion.DatabaseSql;

import java.sql.*;
import java.util.Scanner;

public class MEJExamen {
    private static Connection connPostgres = DatabaseSql.getInstance();

    private static Scanner teclado = new Scanner(System.in);

    public void Menu() {
        final String menu = "1. Crear esquema academiaTeis\n" +
                "2. Crear tipo instituto\n" +
                "3. Insertar nueva inscripcion\n" +
                "4. Actualizar el email de un estudiante\n" +
                "5. Eliminar la información de un curso\n" +
                "6. Consulta 1\n" +
                "7. Consulta 2\n" +
                "8. Consulta 3\n" +
                "9. Consulta 4\n" +
                "10. Consulta 5\n" +
                "11. Salir";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    crearEsquema();
                    break;
                case 2:
                    crearTipoInstituto();
                    break;
                case 3:

                    int cursoID = pedirInt("Dime la id del curso a insertar: ");
                    teclado.nextLine();
                    int estudianteID = pedirInt("Dime el id del estudiante a insertar: ");
                    teclado.nextLine();
                    String fecha = pedirString("Dime una fecha insertar (2023-03-05)");

                    insertarNuevaInscripcion(cursoID, estudianteID, fecha);
                    break;
                case 4:
                    int estudiantesID = pedirInt("Dime la id del estudiante: ");
                    teclado.nextLine();
                    String emailEstudiante = pedirString("Dime el email a actauliazar: ");

                    actualizarEmailEstudiante(estudiantesID, emailEstudiante);
                    break;
                case 5:
                    try {
                        System.out.println("Dime la id del curso a eliminar");
                        int cusoID = teclado.nextInt();
                        eliminarInformacionCurso(cusoID);
                    } catch (SQLException e) {
                        System.out.println("Error al eliminar de la base de datos el curso" + e.toString());
                    }
                    break;
                case 6:
                    listarNombreEmailEstudiante();
                    break;
                case 7:
                    obtenerPromedioEstudiantes();
                    break;
                case 8:
                    obtenerNombreDescripciconCusrsos();
                    break;
                case 9:
                    obtenerNombreDescricpionCursoMayorVeintiOcho();
                    break;
                case 10:
                    obtenerNombreCantidadCursos();
                    break;
                case 11:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Error: solo puedes elegir entre las opciones entre 1-11");
            }
        } while (opcion != 11);
    }

    //Función de lectura de un String:
    public static String pedirString(String mensaje){
        System.out.println(mensaje);
        return teclado.nextLine();
    }

    //Función de lectura de un entero:
    public static int pedirInt(String mensaje){
        while(true){
            try{
                System.out.println(mensaje);
                return teclado.nextInt();
            }catch (Exception e){}
        }
    }

    //Método para crear esquema academiaTeis
    static void crearEsquema() {
        String sql = "CREATE SCHEMA academiaTeis";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)) {
            stmt.executeUpdate();

            System.out.println("Se a creado el esquema academiaTeis correctamente");
        } catch (SQLException e) {
            System.out.println("Error no existe la base de datos o no esta conectado " + e.toString());
        }
    }

    //Método para crear el tipo instituto dentro del esquema academiaTeis
    static void crearTipoInstituto() {
        String sql = "CREATE TYPE academiaTeis.Instituto AS ("
                + "nombre VARCHAR(100),"
                + "numero_Profesores INT,"
                + "presupuesto DOUBLE PRECISION"
                + ");";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)) {
            stmt.executeUpdate();

            System.out.println("Se ha creado el tipo de instituto correctamente");
        } catch (SQLException e) {
            System.out.println("Error no existe la base de datos o no está conectado: " + e.toString());
        }
    }

    //Método para insertar una nuevba inscricpción en la base de datos
    static void insertarNuevaInscripcion(int cursoID, int estudianteID, String fecha) {
        String sql = "INSERT INTO Inscripciones (curso_id, estudiante_id, fecha_inscripcion) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            stmt.setInt(1, cursoID);
            stmt.setInt(2, estudianteID);

            // Convertir java.util.Date a java.sql.Date
            Date fechaSQL = Date.valueOf(fecha);
            stmt.setDate(3, fechaSQL);


            int rowInsert = stmt.executeUpdate();

            if (rowInsert > 0) {
                System.out.println("La inscripción se a insertado correctamente en la base de datos ");
            }else {
                System.out.println("No se a podido insertar en la base de datos: ");
            }
        } catch (SQLException e) {
            System.out.println("Error: no existe la base de datos academia. " + e.toString());
        }

    }

    //Método para actualizar el email de un estudiante en concreto
    static void actualizarEmailEstudiante(int estudianteID, String nuevoemail) {
        String sql = "UPDATE Estudiantes SET info_estudiante.email = ? WHERE estudiante_id = ?";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            stmt.setString(1, nuevoemail);
            stmt.setInt(2, estudianteID);

            int rowUpdate = stmt.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Se a actualizado el email del estudiante correctamente");
            }else {
                System.out.println("No se a podido actualizar el email del estudiante");
            }
        } catch (SQLException e) {
            System.out.println("Error. No existe la base de datos o error de conexión. " + e.toString());
        }

    }

    //Método para elminar información de un curso
    static void eliminarInformacionCurso(int cursoID) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE curso_id = ?";
        String sqlDeleteMaterialCurso = "DELETE FROM MaterialCurso WHERE curso_id = ?";
        String sqlDeleteInscricpion = "DELETE FROM Inscripciones WHERE curso_id = ?";
        String sqlCursos = "DELETE FROM Cursos WHERE curso_id = ?";

        connPostgres.setAutoCommit(false);
        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            stmt.setInt(1, cursoID);

            PreparedStatement stmtDeleteMatrialCurso = connPostgres.prepareStatement(sqlDeleteMaterialCurso);
            stmtDeleteMatrialCurso.setInt(1, cursoID);
            stmtDeleteMatrialCurso.executeUpdate();

            PreparedStatement stmDeleInscripcion = connPostgres.prepareStatement(sqlDeleteInscricpion);
            stmDeleInscripcion.setInt(1, cursoID);
            stmDeleInscripcion.executeUpdate();

            PreparedStatement stmtDeleteCurso = connPostgres.prepareStatement(sqlCursos);
            stmtDeleteCurso.setInt(1, cursoID);
            int rowDelete = stmtDeleteCurso.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Se a eliminado todos los datos del curso");
            }else {
                System.out.println("Error al eliminar los datos de la base de datos");
            }
            connPostgres.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connPostgres != null) connPostgres.rollback();
            }catch (SQLException e)  {
                System.out.println("Error no existe la base de datos Curso " + e.getMessage());
        }
    }
}

//Método para listar todos los estudiantes por su email y su nombre (6 estudinates)
static void listarNombreEmailEstudiante() {
    String sql = "SELECT (info_estudiante).nombre, (info_estudiante).email FROM Estudiantes";

    try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.println("Nombre: " + resultSet.getString("nombre") + " "+ "Email: " + resultSet.getString("email"));

        }
    } catch (SQLException e) {
        System.out.println("Error. No existe en la base de datos. " + e.getMessage());
    }
}

//Obetner el promedio del estudiante 30.33
static void obtenerPromedioEstudiantes() {
        String sql = "SELECT AVG(CAST((info_estudiante).edad AS INTEGER)) AS promedio_edad FROM Estudiantes";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                double promedioEdad = resultSet.getDouble("promedio_edad");
                System.out.println("Edad Promedio de los estudiantes: " + promedioEdad);
            }
        } catch (SQLException e) {
            System.out.println("Error: obtener los datos del " + e.getMessage());
        }
    }

 //Método para obtener el nombre, descripcion y curso mayor a 180 (3 resultados)
static void  obtenerNombreDescripciconCusrsos() {
        String sql = "SELECT c.nombre_curso, c.descripcion, p.nombre AS profesor_nombre "
                + "FROM Cursos c "
                + "JOIN Profesores p ON c.profesor_id = p.profesor_id "
                + "WHERE c.precio > 180";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nombreCurso = resultSet.getString("nombre_curso");
                String descripcion = resultSet.getString("profesor_nombre");
                String profesorNombre = resultSet.getString("profesor_nombre");

                System.out.println("Nombre_ del Curso: " + nombreCurso);
                System.out.println("Descripción: " + descripcion);
                System.out.println("Profesor: " + profesorNombre);
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los cursos: " + e.getMessage());
        }
}

//Método para obtener el nombre, descripcion y curso mayores 28 años (4 resultados)
static void obtenerNombreDescricpionCursoMayorVeintiOcho() {
    String sql = "SELECT c.nombre_curso, c.descripcion " +
            "FROM Inscripciones i " +
            "JOIN Estudiantes e ON e.estudiante_id = i.estudiante_id " +
            "JOIN Cursos c ON c.curso_id = i.curso_id " +
            "WHERE EXTRACT(YEAR FROM CURRENT_DATE) - CAST((e.info_estudiante).edad AS INTEGER) > 28 " +
            "GROUP BY c.nombre_curso, c.descripcion " +
            "HAVING COUNT(e.estudiante_id) > 0";

    try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            String nombreCurso = resultSet.getString("nombre_curso");
            String descripcion = resultSet.getString("descripcion");

            System.out.println("Nombre del Curso: " + nombreCurso);
            System.out.println("Descripción: " + descripcion);
            System.out.println("----------------------------");

        }
    } catch (SQLException e) {
        System.out.println("Error al obtener los cursos: " + e.getMessage());
    }
}

static void obtenerNombreCantidadCursos() {
        String sql = "SELECT (e.info_estudiante).nombre, COUNT(c.curso_id) AS total_cursos " +
                "FROM Estudiantes e " +
                "JOIN Inscripciones i ON e.estudiante_id = i.estudiante_id " +
                "JOIN Cursos c ON c.curso_id = i.curso_id " +
                "GROUP BY (e.info_estudiante).nombre " +
                "HAVING COUNT(c.curso_id) > 0";

        try (PreparedStatement stmt = connPostgres.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nombreEstudiante = resultSet.getString("nombre");
                int totalCursos = resultSet.getInt("total_cursos");
                System.out.println("Estudiante: " + nombreEstudiante + ", Total Cursos: " + totalCursos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos o ejecutar la consulta.");
        }
        }
}

