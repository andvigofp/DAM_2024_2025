package Ej506;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Crea una base de datos PostgreSQL denominada almacen la cual contendrá:
 *
 * Un esquema llamado productos donde se almacenarán todos los siguientes elementos
 *
 * Un objeto Producto el cual está formado por:
 *
 * codigo_producto: varchar
 * nombre: varchar
 * precio: decimal
 * descripcion: tipo texto
 * Una tabla Productos formada por:
 *
 * producto_id: serial clave primaria
 * producto_info: de tipo Producto
 * Un procedimiento InsertarProducto
 *
 * Recibe el codigo_producto, nombre, precio y descripción
 * Se encarga de insertar un nuevo producto en la tabla Productos
 * Una función ObtenerProductoPorCodigo
 *
 * Recibe el codigo_producto
 * Devuelve una tabla con el codigo_producto, nombre, precio y descripción del producto indicado.
 * Se encarga de devolver la información del Producto cuyo codigo_producto sea el indicado por el usuario.
 * A continuación, crea un programa en Java que implemente un menú que permita utilizar la función y el procedimiento anteriores.
 */

public class Ej506 {
    private static Connection conn;
    public static void main(String[] args) {
        String dbURL = "jdbc:postgresql://localhost/almacen";
        String user = "postgres";
        String clave = "abc123.";

        try {
            conn = DriverManager.getConnection(dbURL, user, clave);

            Scanner teclado = new Scanner(System.in);

            MEj506 metodos = new MEj506();
            metodos.Menu(teclado, conn);
        } catch (SQLException e) {
            System.out.println("Error de conexión " + e.toString());
        }
    }
}
