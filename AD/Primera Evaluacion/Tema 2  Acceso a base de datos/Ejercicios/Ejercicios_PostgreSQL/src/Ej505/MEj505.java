package Ej505;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MEj505 {
    // Método menú de opciones
    public void Menu(Scanner teclado, Connection conn) throws SQLException {
        final String menu = "1. INSERTAR MEDICO."
                + "\n2. INSERTAR PACIENTE."
                + "\n3. INSERTAR EXAMEN MEDICO."
                + "\n4. INSERTAR CITA MEDICA."
                + "\n5. MODIFICAR MEDICO."
                + "\n6. MODIFICAR PACIENTE."
                + "\n7. MODIFICAR EXAMEN MEDICO."
                + "\n8. MODIFICAR CITA MEDICA."
                + "\n9. ELIMINAR MEDICO."
                + "\n10. ELIMINAR PACIENTE."
                + "\n11. ELIMINAR EXAMEN MEDICO."
                + "\n12. ELIMINAR CITA MEDICA."
                + "\n13. LISTAR INFORMACIÓN DE UN PACIENTE POR ID ."
                + "\n14. LISTAR INFORMACIÓN DE TODOS LOS PACIENTES."
                + "\n15. LISTAR INFORMACIÓN DE UN MÉDICO POR ID."
                + "\n16. LISTAR INFORMACIÓN DE TODOS LOS MÉDICOS."
                + "\n17. LISTAR INFORMACIÓN DE UNA CITAMEDICA POR ID DE UN PACIENTE."
                + "\n18. LISTAR INFORMACIÓN DE UNA CITAMEDICA POR ID DE UN MÉDICO."
                + "\n19. LISTAR INFORMACIÓN DE TODOS LOS EXÁMENES MÉDICOS DE UN PACIENTE."
                + "\n20. LISTAR INFORMACIÓN DE UN PACIENTE BUSCANDO POR UN GRUPO SANGUÍNEO."
                + "\n21. LISTAR INFORMACIÓN DE UN MÉDICO QUE ES ATENDIDA A UN DETERMINADO PACIENTE."
                + "\n22. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    //Insertar Medico
                    insertarMedico(teclado, conn);
                    break;
                case 2:
                    //Insertar Paciente
                    insertarPaciente(teclado, conn);
                    break;
                case 3:
                    //Insertar Exámen Médico
                    insertarExamenMedico(teclado, conn);
                    break;
                case 4:
                    //Insertar Cita Médica
                    insertarCitaMedica(teclado, conn);
                    break;
                case 5:
                    //Modificar Médico
                    modificarMedico(teclado, conn);
                    break;
                case 6:
                    //Modificar Paciente
                    modificarPaciente(teclado, conn);
                    break;
                case 7:
                    //Modificar Exámen Médico
                    modificarExamenMedico(teclado, conn);
                    break;
                case 8:
                    //Modificar Cita Médica
                    modificarCitaMedica(teclado, conn);
                    break;
                case 9:
                    //Eliminar Médico
                    eliminarMedico(teclado, conn);
                    break;
                case 10:
                    //Eliminar Paciente
                    eliminarPaciente(teclado, conn);
                    break;
                case 11:
                    // Eliminar Exámen Médico
                    eliminarExamenMedico(teclado, conn);
                    break;
                case 12:
                    //Eliminar Cita Médica
                    eliminarCitaMedica(teclado, conn);
                    break;
                case 13:
                    //Listar información de un Paciente por ID
                    listarInformacionPacienteID(teclado, conn);
                    break;
                case 14:
                    //Listar información de todos los Pacientes
                    listarInformacionTodosPacientes(conn);
                    break;
                case 15:
                    //Listar información de un Médico por ID
                    listarInformacionMedicoID(teclado, conn);
                    break;
                case 16:
                    //Listar información de todos los Medicos
                    listarInformacionTodosMedicos(conn);
                    break;
                case 17:
                    // //Listar información de una Cita Médica por ID de un Paciente
                    listarInformacionCitaMedicaIDPaciente(teclado, conn);
                    break;
                case 18:
                    //Listar información de un CitaMedica por ID de Médico
                    listarInformacionCitaMedicaIDMedico(teclado, conn);
                    break;
                case 19:
                    //Listar información de todos los Exámenes Medicos de un Paciente
                    listarInformacionTodosExamenesMedicosPacientes(teclado, conn);
                    break;
                case 20:
                    //Listar información de un Paciente buscándolo por grupo sanguineo
                    listarInformacionPacienteGrupoSanguineo(teclado, conn);
                    break;
                case 21:
                    //Listar información de un Médico que atendia a un determinado Paciente
                    listarInformacionMedicoAtendidoPaciente(teclado, conn);
                    break;
                case 22:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-23");
            }
        } while (opcion != 22);
    }

    //Método para pedir por teclado para atributos de tipo int
    static int pedirInt(Scanner teclado, String mensaje) {
        int valor = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print(mensaje);
                valor = teclado.nextInt();
                entradaValida = true; // Si la entrada es válida, sale del bucle
            } catch (InputMismatchException e) {
                System.out.println("¡Error! Debes ingresar un número entero.");
                teclado.nextLine(); // Limpiar el buffer de entrada
            }
        }
        return valor;
    }

    //Método para pedir por teclado para atributos de tipo String
    static String pedirStringLine(Scanner teclado, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextLine();
            } catch (Exception e) {
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
            } catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    //Método para insertar un médico
    static void insertarMedico(Scanner teclado, Connection conn) {
        String nombre = pedirStringLine(teclado, "Ingrese el nombre del médico: ");
        int edad = pedirInt(teclado, "Ingrese la edad del médico: ");
        teclado.nextLine(); //Limpiar el buffer
        String codigoMedico = pedirStringLine(teclado, "Ingrese el código del médico: ");
        String especialidad = pedirString(teclado,"Ingrese la especialidad del médico: ");

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.Medicos (datos_personales, medico_info) VALUES (ROW(?, ?), ROW(?, ?))");

            statement.setString(1, nombre);
            statement.setInt(2, edad);
            statement.setString(3, codigoMedico);
            statement.setString(4, especialidad);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Médico insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar ese médico.");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para insertar un paciente
    static void insertarPaciente(Scanner teclado, Connection conn) {
        String nombre = pedirStringLine(teclado,"Ingrese el nombre del paciente: ");
        int edad = pedirInt(teclado,"Ingrese la edad del paciente: ");
        teclado.nextLine(); //Limpiar el buffer
        String numeroHistoria = pedirStringLine(teclado,"Ingrese el número de historia del paciente: ");
        String grupoSanguineo = pedirString(teclado,"Ingrese el grupo sanguíneo del paciente: ");

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.Pacientes (datos_personales, paciente_info) VALUES (ROW(?, ?), ROW(?, ?))");

            statement.setString(1, nombre);
            statement.setInt(2, edad);
            statement.setString(3, numeroHistoria);
            statement.setString(4, grupoSanguineo);

            int rowInsertd = statement.executeUpdate();

            if (rowInsertd > 0) {
                System.out.println("Paciente insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar el paciente.");
            }
        }catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }

    }

    //Método para isertar examén médico
    static void insertarExamenMedico(Scanner teclado, Connection conn) {
        int pacienteId = pedirInt(teclado,"Ingrese el ID del paciente para el examen médico: ");
        teclado.nextLine(); //Limpiar el buffer
        String nombreExamen = pedirStringLine(teclado,"Ingrese el nombre del examen médico: ");
        String fechaRealizacion = pedirStringLine(teclado,"Ingrese la fecha de realización del examen médico (YYYY-MM-DD): ");
        String resultado = pedirStringLine(teclado,"Ingrese el resultado del examen médico: ");

        if (!fechaRealizacion.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
            return;
        }

        // Convertir la fecha a tipo java.sql.Date (se asume que la fecha está en formato correcto)
        java.sql.Date fecha = java.sql.Date.valueOf(fechaRealizacion);

        try {
            String sql = "INSERT INTO objetos.ExamenesMedicos (paciente_id, examen_info) VALUES (?, ROW(?, ?, ?))";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, pacienteId);
            statement.setString(2, nombreExamen);
            statement.setDate(3, (fecha));
            statement.setString(4, resultado);

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Examen médico insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar el examén médico.");
            }
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para insertar una cita médica
    static void insertarCitaMedica(Scanner teclado, Connection conn) {
        String fechaHoraStr = pedirStringLine(teclado,"Ingrese la fecha y hora de la cita médica (YYYY-MM-DD HH:mm:ss): ");
        int pacienteId = pedirInt(teclado,"Ingrese el ID del paciente para la cita médica: ");
        int medicoId = pedirInt(teclado,"Ingrese el ID del médico para la cita médica: ");

        try {
            // Convertir la fecha y hora ingresada a un objeto Timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(fechaHoraStr);
            Timestamp fechaHora = new Timestamp(parsedDate.getTime());

            PreparedStatement statement = conn.prepareStatement("INSERT INTO objetos.CitasMedicas (fecha_hora, paciente_id, medico_id) VALUES (?, ?, ?)");

            statement.setTimestamp(1, fechaHora);
            statement.setInt(2, pacienteId);
            statement.setInt(3, medicoId);

            int rowInsert = statement.executeUpdate();

            if (rowInsert > 0) {
                System.out.println("La cita medica se a insertado correctamente.");
            }else {
                System.out.println("No se a podido insertar la cita medica.");
            }
        } catch (SQLException  | ParseException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar los datos del médico
    static void modificarMedico(Scanner teclado, Connection conn) {
        int medicoId = pedirInt(teclado,"Ingrese el ID del médico a modificar: ");
        teclado.nextLine(); //Limpiar el buffer
        String nuevoNombre = pedirStringLine(teclado,"Ingrese el nuevo nombre del médico: ");
        int nuevaEdad = pedirInt(teclado,"Ingrese la nueva edad del médico: ");
        String nuevoCodigoMedico = pedirString(teclado,"Ingrese el nuevo código del médico: ");
        String nuevaEspecialidad = pedirString(teclado,"Ingrese la nueva especialidad del médico: ");

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.Medicos SET datos_personales = ROW(?, ?), medico_info = ROW(?, ?) WHERE medico_id = ?");

            statement.setString(1, nuevoNombre);
            statement.setInt(2, nuevaEdad);
            statement.setString(3, nuevoCodigoMedico);
            statement.setString(4, nuevaEspecialidad);
            statement.setInt(5, medicoId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Se a modificado los datos del médico correctamente.");
            }else {
                System.out.println("No se a podido modificar los datos del médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar los datos de un paciente
    static void modificarPaciente(Scanner teclado, Connection conn) {
        int pacienteId = pedirInt(teclado,"Ingrese el ID del paciente a modificar: ");
        teclado.nextLine(); //Limpiar el buffer
        String nuevoNombre = pedirStringLine(teclado,"Ingrese el nuevo nombre del paciente: ");
        int nuevaEdad = pedirInt(teclado,"Ingrese la nueva edad del paciente: ");
        teclado.nextLine(); //Limpiar el buffer
        String nuevoNumeroHistoria = pedirString(teclado,"Ingrese el nuevo número de historia del paciente: ");
        String nuevoGrupoSanguineo = pedirString(teclado,"Ingrese el nuevo grupo sanguíneo del paciente: ");

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.Pacientes SET datos_personales = ROW(?, ?), paciente_info = ROW(?, ?) WHERE paciente_id = ?");

            statement.setString(1, nuevoNombre);
            statement.setInt(2, nuevaEdad);
            statement.setString(3, nuevoNumeroHistoria);
            statement.setString(4, nuevoGrupoSanguineo);
            statement.setInt(5, pacienteId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Los datos del paciente se a modificado correctamente");
            }else {
                System.out.println("No se pudo modificar los datos del paciente");
            }

        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    static void modificarExamenMedico(Scanner teclado, Connection conn) {
        int examenId = pedirInt(teclado,"Ingrese el ID del examen médico a modificar: ");
        teclado.nextLine();
        String nuevoNombreExamen = pedirStringLine(teclado,"Ingrese el nuevo nombre del examen médico: ");
        String nuevaFechaRealizacion = pedirStringLine(teclado,"Ingrese la nueva fecha de realización del examen médico (YYYY-MM-DD): ");
        String nuevoResultado = pedirStringLine(teclado,"Ingrese el nuevo resultado del examen médico: ");

        if (!nuevaFechaRealizacion.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
            return;
        }

        // Convertir la fecha a tipo java.sql.Date (se asume que la fecha está en formato correcto)
        java.sql.Date fecha = java.sql.Date.valueOf(nuevaFechaRealizacion);

        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.ExamenesMedicos SET examen_info = ROW(?, ?, ?) WHERE examen_id = ?");

            statement.setString(1, nuevoNombreExamen);
            statement.setDate(2, fecha);
            statement.setString(3, nuevoResultado);
            statement.setInt(4, examenId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Examen médico modificado correctamente");
            }else {
                System.out.println("No se a podido modificar el examen médico");
            }
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para modificar una cita médica
    static void modificarCitaMedica(Scanner teclado, Connection conn) {
        int citaId = pedirInt(teclado,"Ingrese el ID de la cita médica a modificar: ");
        teclado.nextLine(); //Limpiar el buffer
        String fechaHoraStr = pedirStringLine(teclado,"Ingrese la nueva fecha y hora de la cita médica (YYYY-MM-DD HH:mm:ss): ");
        int nuevoPacienteId = pedirInt(teclado,"Ingrese el nuevo ID del paciente para la cita médica: ");
        int nuevoMedicoId = pedirInt(teclado,"Ingrese el nuevo ID del médico para la cita médica: ");

        try {
            // Convertir la fecha y hora ingresada a un objeto Timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(fechaHoraStr);
            Timestamp nuevaFechaHora = new Timestamp(parsedDate.getTime());

            PreparedStatement statement = conn.prepareStatement("UPDATE objetos.CitasMedicas SET fecha_hora = ?, paciente_id = ?, medico_id = ? WHERE cita_id = ?");

            statement.setTimestamp(1, nuevaFechaHora);
            statement.setInt(2, nuevoPacienteId);
            statement.setInt(3, nuevoMedicoId);
            statement.setInt(4, citaId);

            int rowUpdate = statement.executeUpdate();

            if (rowUpdate > 0) {
                System.out.println("Cita médica modificada correctamente.");
            }else {
                System.out.println("No se a podido modificar una cita médica.");
            }

        } catch (SQLException |ParseException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar un médico de BD hospital
    static void eliminarMedico(Scanner teclado, Connection conn) {
        int medicoId = pedirInt(teclado, "Ingrese el ID del médico a eliminar: ");

        try {
            // Primero, eliminar las citas médicas asociadas al médico
            PreparedStatement deleteCitasStmt = conn.prepareStatement("DELETE FROM objetos.CitasMedicas WHERE medico_id = ?");
            deleteCitasStmt.setInt(1, medicoId);
            int rowDeleteCita = deleteCitasStmt.executeUpdate(); // Ejecuta una sola vez

            if (rowDeleteCita > 0) {
                System.out.println("Citas médicas asociadas al médico eliminadas correctamente.");
            } else {
                System.out.println("No se ha encontrado ninguna cita médica asociada al médico.");
            }

            // Luego, eliminar el médico
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Medicos WHERE medico_id = ?");
            statement.setInt(1, medicoId);
            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Médico eliminado correctamente.");
            } else {
                System.out.println("No se ha podido eliminar el médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar un paciente
    static void eliminarPaciente(Scanner teclado, Connection conn) {
        int pacienteId = pedirInt(teclado,"Ingrese el ID del paciente a eliminar: ");

        try {
            // Primero, eliminar el examen médico asociado al paciente
            PreparedStatement deleteExamenMedicosStmt = conn.prepareStatement("DELETE FROM objetos.ExamenesMedicos WHERE paciente_id = ?");
            deleteExamenMedicosStmt.setInt(1, pacienteId);
            int rowDeleteExamenMedico = deleteExamenMedicosStmt.executeUpdate(); // Ejecuta una sola vez

            if (rowDeleteExamenMedico > 0) {
                System.out.println("Examen médico asociadas al paciente eliminadas correctamente.");
            } else {
                System.out.println("No se ha encontrado ningún examen médico asociada al paciente.");
            }

            // Luego, eliminar el paciente
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.Pacientes WHERE paciente_id = ?");

            statement.setInt(1, pacienteId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Paciente eliminado correctamente.");
            }else {
                System.out.println("No se a podido eliminar ese paciente");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar un examen médico de la BD hospital
    static void eliminarExamenMedico(Scanner teclado, Connection conn) {
        int examenId = pedirInt(teclado,"Ingrese el ID del examen médico a eliminar: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.ExamenesMedicos WHERE examen_id = ?");

            statement.setInt(1, examenId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Examen médico eliminado correctamente.");
            }else {
                System.out.println("No se podido eliminar el examen médico.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para eliminar una cita médica
    static void eliminarCitaMedica(Scanner teclado, Connection conn) {
        int citaId = pedirInt(teclado,"Ingrese el ID de la cita médica a eliminar: ");

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM objetos.CitasMedicas WHERE cita_id = ?");

            statement.setInt(1, citaId);

            int rowDelete = statement.executeUpdate();

            if (rowDelete > 0) {
                System.out.println("Cita médica eliminada correctamente.");
            }else {
                System.out.println("No se a podido eliminar una cita médica.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }


    //Método para listar la información de un Paciente por ID
    static void listarInformacionPacienteID(Scanner teclado, Connection conn) {
        int pacienteID = pedirInt(teclado,"Ingrese el ID del paciente para ver la información: ");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT paciente_id, " +
                    " (datos_personales). nombre AS nombre, " +
                    " (datos_personales).edad AS edad, " +
                    " (paciente_info).numero_historia AS numero_historia, " +
                    " (paciente_info).grupo_sanguineo AS grupo_sanguineo " +
                    " FROM objetos.Pacientes " +
                    " WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\nInformación del paciente con ID: " + pacienteID);
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar toda la información de los pacientes
    static void listarInformacionTodosPacientes(Connection conn) {
        try {
            ////Consulta para obtener todos los pacientes
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT paciente_id, " +
                            " (datos_personales).nombre, (datos_personales).edad, " +
                            " (paciente_info).numero_historia, (paciente_info).grupo_sanguineo " +
                            " FROM objetos.Pacientes"
            );

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Mostrar los resultados
            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información médica por ID
    static void listarInformacionMedicoID(Scanner teclado, Connection conn) {
        int medicoID = pedirInt(teclado,"Ingrese el ID del médico para ver la información: ");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT medico_id, " +
                    " (datos_personales). nombre AS nombre, " +
                    " (datos_personales).edad AS edad, " +
                    " (medico_info).codigo_medico AS codigo_medico, " +
                    " (medico_info).especialidad AS especialidad " +
                    " FROM objetos.Medicos " +
                    " WHERE medico_id = ?");

            statement.setInt(1, medicoID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nInformación del médico con ID: " + medicoID);
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Código de Médico: " + resultSet.getString("codigo_medico"));
                System.out.println("Especialidad: " + resultSet.getString("especialidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar toda la información de todos los médicos
    static void listarInformacionTodosMedicos(Connection conn) {
        try {
            //Consulta para obtener todos los médicos
            PreparedStatement statement = conn.prepareStatement("SELECT medico_id, " +
                    " (datos_personales).nombre, (datos_personales).edad, " +
                    " (medico_info).codigo_medico, (medico_info).especialidad " +
                    " FROM objetos.Medicos");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("medico_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Código Médico: " + resultSet.getString("codigo_medico"));
                System.out.println("Especialidad: " + resultSet.getString("especialidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }


    //Método para listar la información de una cita Médica por ID de un Paciente
    static void listarInformacionCitaMedicaIDPaciente(Scanner teclado, Connection connection) {
        int pacienteID = pedirInt(teclado, "Ingrese el ID del paciente para listar las citas médicas: ");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objetos.CitasMedicas WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("cita_id"));
                System.out.println("Fecha y Hora: " + resultSet.getString("fecha_hora"));
                System.out.println("Paciente ID: " + resultSet.getInt("paciente_id"));
                System.out.println("Médico ID: " + resultSet.getInt("medico_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de una cita Médica por ID de un Médico
    static void listarInformacionCitaMedicaIDMedico(Scanner teclado, Connection conn) {
        int medicoID = pedirInt(teclado, "Ingrese el ID del médico para listar las citas médicas: ");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM objetos.CitasMedicas WHERE medico_id = ?");

            statement.setInt(1, medicoID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("cita_id"));
                System.out.println("Fecha y Hora: " + resultSet.getString("fecha_hora"));
                System.out.println("Paciente ID: " + resultSet.getInt("paciente_id"));
                System.out.println("Médico ID: " + resultSet.getInt("medico_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar información de todos los exámenes médicos de un paciente
    static void listarInformacionTodosExamenesMedicosPacientes(Scanner teclado, Connection conn) {
        int pacienteID = pedirInt(teclado, "Ingese el ID del paciente para listar los exámenes médicos: ");

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT examen_id, " +
                    " (examen_info).nombre_examen, (examen_info).fecha_realizacion, (examen_info).resultado " +
                    " FROM objetos.ExamenesMedicos " +
                    " WHERE paciente_id = ?");

            statement.setInt(1, pacienteID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID de Examen: " + resultSet.getInt("examen_id"));
                System.out.println("Nombre del Examen: " + resultSet.getString("nombre_examen"));
                System.out.println("Fecha de Realización: " + resultSet.getDate("fecha_realizacion"));
                System.out.println("Resultado: " + resultSet.getString("resultado"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de un paciente por su grupo sanquineo
    static void listarInformacionPacienteGrupoSanguineo(Scanner teclado, Connection conn) {
        String grupoSanguineo = pedirString(teclado, "Ingrese el grupo sanguineo para listar los pacientes: ").toUpperCase();

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT paciente_id, " +
                            " (datos_personales).nombre, (datos_personales).edad, " +
                            " (paciente_info).numero_historia, (paciente_info).grupo_sanguineo " +
                            " FROM objetos.Pacientes " +
                            " WHERE (paciente_info).grupo_sanguineo = ?");

            statement.setString(1, grupoSanguineo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nID: " + resultSet.getInt("paciente_id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Edad: " + resultSet.getInt("edad"));
                System.out.println("Número de Historia: " + resultSet.getString("numero_historia"));
                System.out.println("Grupo Sanguíneo: " + resultSet.getString("grupo_sanguineo"));
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }

    //Método para listar la información de un médico atendido por un paciente
    static void listarInformacionMedicoAtendidoPaciente(Scanner teclado, Connection conn) {
        int pacienteID = pedirInt(teclado, "Ingrese el ID del paciente para listar el médico que lo atendió:");

        // Consulta SQL para obtener información del médico que atendió al paciente
        String query = "SELECT m.medico_id, m.datos_personales.nombre AS medico_nombre, m.datos_personales.edad AS medico_edad, "
                + "m.medico_info.codigo_medico, m.medico_info.especialidad "
                + "FROM objetos.Medicos m "
                + "JOIN objetos.CitasMedicas c ON m.medico_id = c.medico_id "
                + "WHERE c.paciente_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, pacienteID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Mostrar los resultados si existen
                    System.out.println("\nMédico atendiendo al paciente ID: " + pacienteID);
                    System.out.println("ID Médico: " + resultSet.getInt("medico_id"));
                    System.out.println("Nombre Médico: " + resultSet.getString("medico_nombre"));
                    System.out.println("Edad Médico: " + resultSet.getInt("medico_edad"));
                    System.out.println("Código Médico: " + resultSet.getString("codigo_medico"));
                    System.out.println("Especialidad Médico: " + resultSet.getString("especialidad"));
                } else {
                    System.out.println("No se encontró un médico atendiendo a este paciente.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar la información del médico: " + e.toString());
        }
    }
}
