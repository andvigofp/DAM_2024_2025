package Ej506;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MEj506 {
    // Método menú de opciones
    public void Menu(Scanner teclado, Connection conn) throws SQLException {
        final String menu = "1. PROCEDIMIENTO INSERTAR PRODUCTO."
                + "\n2. FUNCIÓN OBTENER PRODUCTO POR CÓDIGO."
                + "\n3. SALIR.";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    insertarProducto(teclado, conn);
                    break;
                case 2:
                    obtenerProductosPorCodigo(teclado, conn);
                    break;
                case 3:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-3");
            }
        } while (opcion != 3);
    }
    //Método para pedir por teclado para atributos de tipo int
    static double pedirDouble(Scanner teclado, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                return teclado.nextDouble();
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
                return teclado.nextLine();
            }catch (Exception e) {
                System.out.println("Error. " + e.toString());
            }
        }
    }

    //Método para insertar productos en la BD almacén
    static void insertarProducto(Scanner teclado, Connection conn) {
        String codigo = pedirString(teclado, "Código del producto: ");
        String nombre = pedirString(teclado, "Nombre del producto");

        System.out.print("Precio del producto: ");
        double precio = 0.0;
        try {
            String precioStr = teclado.nextLine().replace(",", "."); // Reemplazar coma con punto
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            System.err.println("Error: El precio ingresado no es válido. Asegúrate de usar un formato correcto (ej. 25.00).");
            return;
        }

        String descripcion = pedirString(teclado, "Descripción del producto");

        try {
            // Usamos setBigDecimal en lugar de setString para el precio
            String sql = "SELECT productos.InsertarProducto(?, ?, ?, ?)";
            CallableStatement statement = conn.prepareCall(sql);
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setBigDecimal(3, BigDecimal.valueOf(precio));  // Usar BigDecimal para el precio
            statement.setString(4, descripcion);

            statement.execute();

            System.out.println("Producto insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    //Método para listar productos por ID en la BD almacén
    static void obtenerProductosPorCodigo(Scanner teclado, Connection conn) {
        String codigo = pedirString(teclado, "Código del producto: ");

        try {
            String sql = "SELECT * FROM productos.ObtenerProductoPorCodigo(?)";
            CallableStatement statement = conn.prepareCall(sql);

            statement.setString(1, codigo);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                String descripcion = resultSet.getString("descripcion");

                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("Descripción: " + descripcion);
            }else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error. " + e.toString());
        }
    }
}
