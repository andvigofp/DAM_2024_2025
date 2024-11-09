package Ej504;

import java.net.ConnectException;
import java.sql.*;
import java.util.Scanner;

public class MEj504 {
    // Método menú de opciones
    public void Menu(Scanner teclado, Connection conn) throws SQLException {
        final String menu = "1. INSERTAR ESTUDIANTE."
                + "\n2. ELIMINAR ESTUDIANTE."
                + "\n3. MODIFICAR ESTUDIANTE."
                + "\n4. INSERTAR PROFESOR."
                + "\n5. ELIMINAR PROFESOR."
                + "\n6. MODIFICAR PROFESOR."
                + "\n7. INSERTAR CURSO."
                + "\n8. ELIMINAR CURSO."
                + "\n9. MODIFICAR CURSO."
                + "\n10. INSCRIBIR ESTUDIANTE EN CURSO."
                + "\n11. DESINSCRIBIR PROFESOR EN CURSO."
                + "\n12. INSCRIBIR PROFESOR EN CURSO."
                + "\n13. DESINSCRIBIR PORFESOR DE CURSOS."
                + "\n14. LISTAR ESTUDIANTE POR ID."
                + "\n15. LISTAR TODOS LOS ESTUDIANTES."
                + "\n16. LISTAR CURSO POR ID."
                + "\n17. LISTAR TODOS LOS CURSOS."
                + "\n18. LISTAR PROFESORES POR ID."
                + "\n19. LISTAR TODOS LOS PROFESORES."
                + "\n20. LISTAR TODOS LOS ESTUDIANTES/MATRICULAS."
                + "\n21. LISTAR CURSOS DE UN ESTUDIANTE."
                + "\n22. NÚMERO DE ESTUDIANTES POR CARRERA"
                + "\n23. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    //Insertar Estudiante
                    insertarEstudiante(teclado, conn);
                    break;
                case 2:
                    //Eliminar Estudiante
                    eliminarEstudiante(teclado, conn);
                    break;
                case 3:
                    //Modificar Estudiante
                    modficarEstudiante(teclado, conn);
                    break;
                case 4:
                    //Insertar Profesor
                    insertarProfesor(teclado, conn);
                    break;
                case 5:
                    //Eliminar Profesor
                    eliminarProfesor(teclado, conn);
                    break;
                case 6:
                    //Modificar Profesor
                    modificarProfesor(teclado, conn);
                    break;
                case 7:
                    //Insertar Cursos
                    insertarCurso(teclado, conn);
                    break;
                case 8:
                    //Eliminar Cursos
                    eliminarCurso(teclado, conn);
                    break;
                case 9:
                    //Modificar curso
                    modificarCurso(teclado, conn);
                    break;
                case 10:
                    //Inscribir un estudiante en curso
                    inscribirEstudianteCurso(teclado, conn);
                    break;
                case 11:
                    //Desinscribir estudiante de curso
                    desinscribirEstudianCurso(teclado, conn);
                    break;
                case 12:
                    //Inscribir Profesor en Curso
                    inscribirProfesorCurso(teclado,conn);
                    break;
                case 13:
                    //Describir Profesor eb Curso
                    desinscribirProfesorCurso(teclado,conn);
                    break;
                case 14:
                    //Listar Estudiante por ID
                    listarEstudianteID(teclado, conn);
                    break;
                case 15:
                    //Listar todos los estudiantes
                    listarTodosEstudiantes(conn);
                    break;
                case 16:
                    //Listar Curso por ID
                    int cursoId = pedirInt(teclado, "ID del Curso: ");
                    listarCursoID(cursoId, conn);
                    break;
                case 17:
                    //Listar todos los cursos
                    listarTodosCursos(conn);
                    break;
                case 18:
                    //Listar Profesores por ID
                    int profesorID = pedirInt(teclado, "ID del profesor: ");
                    listarProfesorID(profesorID, conn);
                    break;
                case 19:
                    //Listar todos los profesores
                    listarTodosProfesores(conn);
                    break;
                case 20:
                    //Listar todos los estudiantes y sus matrículas
                    listarEstudianteCursos(conn);
                    break;
                case 21:
                    //Listar cursos de un Estudiante
                    listarCursosEstudiante(teclado, conn);
                    break;
                case 22:
                    //Número de estudiantes por carrera
                    numeroEstudiantesCurso(conn);
                    break;
                case 23:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-23");
            }
        } while (opcion !=23);
    }


    //Método para pedir por teclado para atributos de tipo int
    static int pedirInt(Scanner teclado, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextInt();
            }catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    //Método para pedir por teclado para atributos de tipo String
    static String pedirString(Scanner teclado, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.next();
            }catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }


    //Método para insertar los datos de un estudiante BD universidad
    static void insertarEstudiante(Scanner teclado, Connection conn) {
        String nombre = pedirString(teclado, "Nombre del estudiante: ");
        int edad = pedirInt(teclado, "Edad del estudiante: ");
        String matricula = pedirString(teclado, "Matrícula del estudiante: ");
        String carrera = pedirString(teclado, "Carrera del estudiante: ");

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info) VALUES (ROW(?, ?), ROW(?, ?))");
            statement.setString(1, nombre);
            statement.setInt(2, edad);
            statement.setString(3, matricula);
            statement.setString(4, carrera);

            int rowInsertd = statement.executeUpdate();

            if (rowInsertd > 0) {
                System.out.println("Se ha insertado correctamente el estudiante");
            }else {
                System.out.println("No se pudo insertar el estudiante");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar los datos de estudiante." + e.toString());
        }
    }

    //Método para elimar el estudiante por su ID en su BD universidad
    static void eliminarEstudiante(Scanner teclado, Connection conn) {
        int estudianteId = pedirInt(teclado, "ID del estudiante a eliminar: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Estudiantes WHERE estudiante_id = ?");
            statement.setInt(1, estudianteId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El estudiante se eliminado correctamente.");
            }else {
                System.out.println("No se a podido eliminar el estudiante revisa el ID");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar el estudiante de la BD universidad
    static void modficarEstudiante(Scanner teclado, Connection conn) {
        int estudianteIdModificar = pedirInt(teclado, "ID del estudiante a modificar: ");
        String nuevoNombre = pedirString(teclado, "Nuevo nombre: ");
        int nuevaEdad = pedirInt(teclado, "Nueva edad: ") ;
        String nuevaMatricula = pedirString(teclado, "Nueva matrícula: ") ;
        String nuevaCarrera =pedirString(teclado,"Nueva carrera: ") ;

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.Estudiantes SET datos_personales = ROW(?, ?), estudiante_info = ROW(?, ?) WHERE estudiante_id = ?");
            statement.setString(1, nuevoNombre);
            statement.setInt(2, nuevaEdad);
            statement.setString(3, nuevaMatricula);
            statement.setString(4, nuevaCarrera);
            statement.setInt(5, estudianteIdModificar);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("El estudiante se a actualizado correctamente");
            }else {
                System.out.println("No se a podido modificar revisa el ID del estudiante");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para insertar los datos de un profesor BD universidad
    static void insertarProfesor(Scanner teclado, Connection coon) {
        System.out.println( "Nombre Profesor: ");
        String nombreP = teclado.nextLine();

        int edadP = pedirInt(teclado, "Edad del profesor: ");
        String cedula = pedirString(teclado, "Cédula del profesor: ");
        String departamento = pedirString(teclado, "Departamento del profesor: ");
        int cursoID = pedirInt(teclado, "Curso del profesor: ");

        if (!listarCursoID(cursoID,coon)) {
            System.out.println("No existe ese ID del curos");
            return;
        }

        try {
           PreparedStatement statement = coon.prepareStatement("INSERT INTO objetos.Profesores (datos_personales, profesor_info, curso_id) "
                   + "VALUES (ROW(?, ?), ROW(?, ?), ?)");

           statement.setString(1, nombreP);
           statement.setInt(2, edadP);
           statement.setString(3, cedula);
           statement.setString(4, departamento);
           statement.setInt(5, cursoID);

           int rowInserted = statement.executeUpdate();

           if (rowInserted > 0) {
               System.out.println("Los datos de los profesores se ha insertado correctamente.");
           }else {
               System.out.println("No se podido insertar los datos de los profesores, revisa los atributos");
           }

       } catch (SQLException e) {
           System.out.println("Error. " + e.toString());
       }
    }

    //Método para eliminar un profesor de la BD universidad
    static void eliminarProfesor(Scanner teclado, Connection conn) {
        int profosorId = pedirInt(teclado, "ID del profesor a eliminar: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Profesores WHERE profesor_id = ?");
            statement.setInt(1, profosorId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El profesor se a borrado correctamente.");
            }else {
                System.out.println("No se a podido eliminar el profesor, revisa la ID.");
            }
        }catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar los datos del profesor de la BD universidad
    static void modificarProfesor(Scanner teclado, Connection conn) {

        int profesorId = pedirInt(teclado, "ID del profesor : ");

        // Verificar si el profesor existe
        if (!listarProfesorID(profesorId, conn)) {
            System.out.println("No existe ese ID del profesor");
            return;
        }

        // Obtener nuevos datos del profesor
        String nombreP = pedirString(teclado, "Nombre del profesor: ");
        int edadP = pedirInt(teclado, "Edad del profesor: ");
        String cedula = pedirString(teclado, "Cédula del profesor: ");
        String departamento = pedirString(teclado, "Departamento del profesor: ");
        int cursoID = pedirInt(teclado, "Curso del profesor: ");

        // Verificar si el curso existe
        if (!listarCursoID(cursoID,conn)){
            System.out.println("No existe ese ID con ese curso");
            return;
        }

        try {
            // Preparar la actualización del profesor
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE objetos.Profesores SET datos_personales = ROW(?, ?), profesor_info = ROW(?, ?), curso_id = ? WHERE profesor_id = ?"
            );

            // Asignar valores a la consulta
            statement.setString(1, nombreP);
            statement.setInt(2, edadP);
            statement.setString(3, cedula);
            statement.setString(4, departamento);
            statement.setInt(5, cursoID);
            statement.setInt(6, profesorId);

            // Ejecutar la actualización
            int rowUpdate = statement.executeUpdate();

            // Confirmar resultado
            if (rowUpdate > 0) {
                System.out.println("Se ha actualizado los datos del profesor correctamente.");
            } else {
                System.out.println("No se ha podido actualizar los datos del profesor.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.toString());
        }
    }

    //Método para isertar un curso en la BD universidad
    static void insertarCurso(Scanner teclado, Connection conn) {
        String nombreCurso = pedirString(teclado, "Nombre del curso: ");
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.Cursos (nombre) VALUES (?)");
            statement.setString(1, nombreCurso);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("El curso se a insertado correctamente");
            }else {
                System.out.println("No se a podido insertar el curso fíjate en los atributos");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar el curso de la BD universidad
    static void eliminarCurso(Scanner teclado, Connection conn) {
        int idCurso = pedirInt(teclado, "ID del curso: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Cursos WHERE curso_id = ?");
            statement.setInt(1, idCurso);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Se ha eliminado el curso correctamente");
            }else {
                System.out.println("No se a podido eliminar el curso, comprueba la ID");
            }

        } catch (SQLException e) {
            System.out.println("Error." + e.toString());
        }
    }

    //Método para modificar los datos de un curso
    static void modificarCurso(Scanner teclado, Connection conn) {
       int idCurso = pedirInt(teclado, "ID del curso: ");
       String nombreCurso = pedirString(teclado, "Nombre del curso: ");

       try {
           PreparedStatement statement = conn.prepareStatement("UPDATE objetos.Cursos SET nombre = ? WHERE curso_id = ?");
           statement.setString(1, nombreCurso);
           statement.setInt(2, idCurso);

           int rowUpdate = statement.executeUpdate();

           if (rowUpdate > 0) {
               System.out.println("El curso se actualizado correctamente");
           }else {
               System.out.println("No se a podido actualizar fijate en la ID");
           }
       } catch (SQLException e) {
           System.out.println("Error. " + e.toString());
       }
    }

    //Método para inscribir a un estudiante en curso
    static void inscribirEstudianteCurso(Scanner teclado, Connection conn) {
        int estudianteId = pedirInt(teclado, "ID del estudiante: ");
        int cursoId = pedirInt(teclado, "ID del curso: ");

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.Inscripciones (estudiante_id, curso_id) VALUES (?, ?)");
            statement.setInt(1, estudianteId);
            statement.setInt(2, cursoId);

            int rowInsertd = statement.executeUpdate();

            if (rowInsertd > 0) {
                System.out.println("El estudiante se a añadido a ese curso correctamente");
            }else {
                System.out.println("No se a podido añadir al estudiante a ese curso, revisa los atributos");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para desinscribir a un estudiante de ese curso
    static void desinscribirEstudianCurso(Scanner teclado, Connection conn) {
        int estudianteId = pedirInt(teclado, "ID del estudiante: ");
        int cursoId = pedirInt(teclado, "ID del curso: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Inscripciones WHERE estudiante_id = ? AND curso_id = ?");
            statement.setInt(1, estudianteId);
            statement.setInt(2, cursoId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El estudiante se a desinscrito de ese curso correctamente");
            }else {
                System.out.println("No se a podido desinscribir ese estudiante de ese curso");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para inscribir profesor en ese curso
    static void inscribirProfesorCurso(Scanner teclado, Connection conn) {
        int profesorId = pedirInt(teclado, "ID del profesor: ");
        int cursoId = pedirInt(teclado, "ID del curso: ");

        // Verificar que el profesor y el curso existan antes de proceder
        if (!listarProfesorID(profesorId, conn)) {
            System.out.println("El ID del profesor no existe.");
            return;
        }
        if (!listarCursoID(cursoId, conn)) {
            System.out.println("El ID del curso no existe.");
            return;
        }

        try {
            // Insertar la inscripción en la tabla 'Inscripciones'
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO objetos.Inscripciones (profesor_id, curso_id) VALUES (?, ?)"
            );
            statement.setInt(1, profesorId);
            statement.setInt(2, cursoId);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("El profesor se ha añadido al curso correctamente.");
            } else {
                System.out.println("No se ha podido añadir al profesor al curso. Revisa los atributos.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para desinscribir a un profesor de ese curso
    static void desinscribirProfesorCurso(Scanner teclado, Connection conn) {
        int profesorId = pedirInt(teclado, "ID del profesor: ");
        int cursoId = pedirInt(teclado, "ID del curso: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Inscripciones WHERE profesor_id = ? AND curso_id = ?");
            statement.setInt(1, profesorId);
            statement.setInt(2, cursoId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("El profesor se ha desinscrito de ese curso correctamente.");
            } else {
                System.out.println("No se ha podido desinscribir a ese profesor de ese curso.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desinscribir al profesor del curso: " + e.toString());
        }
    }



    //Listar un estudiante por ID
    static void listarEstudianteID(Scanner teclado, Connection conn) {
        int estudianteId = pedirInt(teclado, "ID del estudiante: ");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Estudiantes WHERE estudiante_id = ?");
            statement.setInt(1, estudianteId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int estudianteIdRecuperado = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante estudiante = new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + estudianteIdRecuperado);
                System.out.println(persona);
                System.out.println(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());;
        }
    }

    //Método para listar todos los estudiantes BD universidad
    static void listarTodosEstudiantes(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Estudiantes");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int estudianteID = resultSet.getInt("estudiante_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Estudiante estudiante = new Estudiante(resultSet.getString("estudiante_info"));

                System.out.println("ID: " + estudianteID);
                System.out.println(persona);
                System.out.println(estudiante);

            }
        } catch (SQLException e) {
            System.out.println("Error, comprueba que este bien los atributos." + e.toString());
        }
    }

    //Método para listar los cursos pr ID de la BD universidad
    static boolean listarCursoID(int cursoID, Connection conn) {

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Cursos WHERE curso_id = ?");
            statement.setInt(1, cursoID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cursoID = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoID);
                System.out.println("Nombre: " + nombre);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
            return false;
        }
    }

    //Método para listar todos los cursos de la BD universidad
    static void listarTodosCursos(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Cursos");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cursoID = resultSet.getInt("curso_id");
                String nombre = resultSet.getString("nombre");

                System.out.println("ID: " + cursoID);
                System.out.println("Nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar el profesor por ID
    static boolean listarProfesorID(int profesorID, Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Profesores WHERE profesor_id = ?");
            statement.setInt(1, profesorID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                profesorID = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorID);
                System.out.println(persona);
                System.out.println(profesor);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
            return false;
        }
    }

    //Método para listar todos los profesores de la BD universidad
    static void listarTodosProfesores(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.Profesores");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int profesorID = resultSet.getInt("profesor_id");
                Persona persona = new Persona(resultSet.getString("datos_personales"));
                Profesor profesor = new Profesor(resultSet.getString("profesor_info"));

                System.out.println("ID: " + profesorID);
                System.out.println(persona);
                System.out.println(profesor);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar todos los estudiantes y sus matrículas
    static void listarEstudianteCursos(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT (e.datos_personales).nombre AS nombre_estudiante, c.nombre AS nombre_curso"
                    + " FROM objetos.Estudiantes e "
                    + " INNER JOIN objetos.Inscripciones i ON e.estudiante_id = i.estudiante_id "
                    + " INNER JOIN objetos.Cursos c ON i.curso_id = c.curso_id;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombreEstudiante = resultSet.getString("nombre_estudiante");
                String nombreCurso = resultSet.getString("nombre_curso");

                System.out.println("Estudiante: " + nombreEstudiante + " Asiste al curso: " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para mostrar los cursos de un estudiante
    static void listarCursosEstudiante(Scanner teclado, Connection conn) {
        int idEstudiante = pedirInt(teclado, "ID del estudiante");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT c.nombre AS nombre_curso "
                    + " FROM objetos.Cursos c "
                    + " INNER JOIN objetos.Inscripciones i ON c.curso_id = i.curso_id "
                    + " WHERE i.estudiante_id = ?;");

            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreCurso = resultSet.getString("nombre_curso");

                System.out.println("Asiste al curso: " + nombreCurso);
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para saber el número de estudiante por carrera
    static void numeroEstudiantesCurso(Connection conn) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT c.nombre AS nombre_curso, COUNT(e.estudiante_id) AS numero_estudiantes "
                    + " FROM objetos.Cursos c "
                    + " LEFT JOIN objetos.Inscripciones i ON c.curso_id = i.curso_id "
                    + " LEFT JOIN objetos.Estudiantes e ON i.estudiante_id = e.estudiante_id "
                    + " GROUP BY c.nombre "
                    + " ORDER BY c.nombre");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreCurso = resultSet.getString("nombre_curso");
                int numeroEstudiantes = resultSet.getInt("numero_estudiantes");

                System.out.println("Curso: " + nombreCurso + " Asisten: " + numeroEstudiantes + " estudiantes");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

}
