package Ej302;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Dada la siguiente base de datos de empleados se pide desarrollar un programa Java que se conecte a la base de datos utilizando el usuario root y la contraseña abc123. y permita:
 *
 * Mostrar la información de la base de datos.
 * Mostrar la información de la tabla proyectos.
 * Insertar un nuevo proyecto con los siguientes datos (11, 'Acceso a datos', 'Lugo', 3) en la tabla proyecto.
 * Eliminar una fila de la tabla proyecto a partir de su número de proyecto numproy.
 * NOTA: Sería conveniente crear una función independiente para cada una de las funcionalidades de forma que se puedan reutilizar en un futuro. NOTA: Será importante gestionar los posibles errores que pueda generar el código.
 *
 * Información adicional
 * Algunas de las clases qeu se deberían utilizar para implementar este ejercicio son:
 *
 * Statement: para gestionar las consultas
 * PreparedStatement: para poder reutilizar consultas.
 * SQLException: para gestionar las excepciones
 */
public class Ej302 {
    public static void main(String[] args) {
        MEj302 metodos = new MEj302();

        metodos.conection();
    }
}

