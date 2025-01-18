package andres_fernandez.Aplicacion;

import andres_fernandez.Conexiones.DabaseMYSQL;
import andres_fernandez.Conexiones.DatabasePostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ValiacionesMyqlPost {
    private static Connection mysqlconn = DabaseMYSQL.getInstance();
    private static Connection postgresConn = DatabasePostgres.getInstance();

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

    //Método para pedir por teclado para atributos de tipo double
    static Double pedirDouble(Scanner teclado, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            try {
                String input = teclado.next(); // Leemos la entrada como un String

                // Reemplazamos la coma por el punto, si existe, para asegurar la compatibilidad con el formato decimal
                input = input.replace(',', '.');

                // Intentamos convertir la entrada a un Double
                Double result = Double.parseDouble(input);

                // Si la conversión es exitosa, retornamos el número
                return result;

            } catch (NumberFormatException e) {
                // En caso de que el número no sea válido
                System.out.println("Error: Debes ingresar un número decimal válido (usa punto para los decimales).");
            }
        }
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

    //Método de validar nombre de categoría que solo pueda poner letras
    static String obtenerNombreCategoriaValido(Scanner teclado) {
        do {

            // Obtener y mostrar los nombres de las categorias  de los proveedores antes de pedir  la categoria del proveedor
            List<String> nombres = obtenerNombreCategorias();

            System.out.println("Lista de todas categorias:");
            for (String nombreC : nombres) {
                System.out.println(nombreC);
            }

            // Pedimos el nombre de la categoría
            String nombreCategoria = pedirStringLine(teclado, "Ingrese el nombre de la categoría a insertar: ");

            // Eliminar espacios en blanco al principio y al final
            nombreCategoria = nombreCategoria.trim();

            // Validar que el nombre vategoria no esté vacío
            if (nombreCategoria.isEmpty()) {
                System.out.println("Error: El nombre de la categoría no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nombre
            }

            // Validar que solo contenga letras (sin espacios)
            if (nombreCategoria.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                // Si es válido, devolver el nombre
                return nombreCategoria;

            } else {
                System.out.println("Error: El nombre de la categoría solo debe contener letras, y sin espacios.");
            }

        } while (true); // Repite hasta que el nombre sea válido
    }




    // Método para validar el nombre del proveedor
    static String esNombreValidoProveeedor(Scanner teclado) {
        do {
            String nombreProveedor = pedirStringLine(teclado, "Ingrese el nombre del proveedor a insertar: ");

            nombreProveedor = nombreProveedor.trim();

            // Validar que el nombre proveedor no esté vacío
            if (nombreProveedor.isEmpty()) {
                System.out.println("Error: El nombre del proveedor no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nombre proveedor
            }

            // Validar que solo contenga letras y números
            if (nombreProveedor.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+")) {
                return nombreProveedor;
            } else {
                System.out.println("Error: El nombre del proveedor solo debe contener letras, números y espacios.");
            }
        } while (true); //Repite hasta que el nombre sea válido
    }

    // Método para validar el NIF (8 números y una letra)
    static String esNifValidoProveedor(Scanner teclado) {
        do {
            // Pedimos el NIF
            String nif = pedirStringLine(teclado, "Ingresa el nif del proveedor a insertar: ");

            // Validar que el nif no esté vacío
            if (nif.isEmpty()) {
                System.out.println("Error: El nif no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nif
            }

            // Eliminar todos los espacios en blanco
            nif = nif.replaceAll("\\s", ""); // Quita todos los espacios en blanco

            // Validar que el NIF no esté vacío
            if (nif.isEmpty()) {
                System.out.println("Error: El nif no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el NIF
            }

            // Validar que solo contenga 8 números y una letra
            if (nif.matches("\\d{8}[a-zA-Z]")) {
                return nif;  // Si es válido, se devuelve el NIF
            } else {
                System.out.println("Error: El nif del proveedor solo debe contener 8 números y una letra y sin espacios.");
            }
        } while (true); // Repite hasta que el nif sea válido
    }


    // Método para validar el teléfono (solo números)
    static String esTelefonoValidoProveedor(Scanner teclado) {
        do {
            String telefono = pedirStringLine(teclado, "Ingrese el número teléfono del proveedor a insertar: ");

            // Validar que el telefono no esté vacío
            if (telefono.isEmpty()) {
                System.out.println("Error: El teléfono no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el telefono
            }

            // Eliminar todos los espacios en blanco
            telefono = telefono.replaceAll("\\s", ""); // Quita todos los espacios en blanco

            if (telefono.matches("\\d{9}")) {
                return telefono;
            } else {
                System.out.println("Error: El teléfono del proveedor solo debe contener un máximo de 9 números y sin espacios.");
            }
        } while (true); // Repite hasta que el teléfono sea válido

    }

    // Método para validar el email con un formato válido
    static String esEmailValidoProveedor(Scanner teclado) {
        do {
            String email = pedirStringLine(teclado, "Ingrese el email del proveedor  a insertar: ");

            // Validar que el email no esté vacío
            if (email.isEmpty()) {
                System.out.println("Error: El nombre de la categoría no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nombre
            }

            // Eliminar todos los espacios en blanco
            email = email.replaceAll("\\s", ""); // Quita todos los espacios en blanco

            if (email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
                return email;
            } else {
                System.out.println("Error: El email del proveedor solo debe contener letras, números y carácter especial @. y sin espacios");
            }
        } while (true); // Repite hasta que el email sea válido
    }

    // Método para validar el nombre con un formato válido
    static String esNombreUsuario(Scanner teclado) {
        do {
            String nombreUsuario = pedirStringLine(teclado, "Introduce el nombre del usuario a insertar");

            // Validar que el nombre usuario no esté vacío
            if (nombreUsuario.isEmpty()) {
                System.out.println("Error: El nombre del usuario  no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nombre usuario
            }

            if (nombreUsuario.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+")) {
                return nombreUsuario;
            }else {
                System.out.println("Error: El nombre del usuario solo debe contener letras, números y espacios.");
            }
        }while (true);
    }

    // Método para validar el id con un formato válido
    static String esIdProveedor(Scanner teclado) {
        do {

            List<Integer> ids = obtenerIDProveedores();

            System.out.println("Lista de ids de los proveedores disponibles:");
            for (Integer id : ids) {
                System.out.println(id);
            }

            String id = pedirStringLine(teclado, "Ingrese la ID del proveedor a eliminar: ");

            // Eliminar todos los espacios en blanco
            id = id.replaceAll("\\s", ""); // Quita todos los espacios en blanco

            // Validar que el nombre no esté vacío
            if (id.isEmpty()) {
                System.out.println("Error: El id no puede vacío.");
                continue; // Si está vacío, vuelve a pedir el id
            }

            if (id.matches("\\d+")) {
                return id;
            }else {
                System.out.println("Error. El id del proveedor solo puede contener números, sin espacios.");
            }
        }while (true); // Repite hasta que el email sea válido
    }

    // Método para validar el email con un formato válido
    static String esEmailValidoUsuario(Scanner teclado) {
        do {
            String email = pedirStringLine(teclado, "Ingrese el email del usuario a insertar: ");

            // Validar que el nombre no esté vacío
            if (email.isEmpty()) {
                System.out.println("Error: El email vacío.");
                continue; // Si está vacío, vuelve a pedir el email
            }

            // Eliminar todos los espacios en blanco
            email = email.replaceAll("\\s", ""); // Quita todos los espacios en blanco

            if (email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
                return email;
            } else {
                System.out.println("Error: El email del usuario solo debe contener letras, números y carácter especial @. y sin espacios");
            }
        } while (true); // Repite hasta que el email sea válido
    }


    // Método para validar el año nacimiento con un formato válido
    static String esFechaNacimentoValidaUusario(Scanner teclado) {
        do {
            String ano_nacimento = pedirStringLine(teclado, "Introduce el año de nacimiento del usuario a insertar");

            // Validar que el nombre no esté vacío
            if (ano_nacimento.isEmpty()) {
                System.out.println("Error: El año de nacimiento  no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el año nacimiento
            }

            // Eliminar todos los espacios en blanco
            ano_nacimento = ano_nacimento.replaceAll("\\s", "");

            if (ano_nacimento.matches("\\d+")) {
                return ano_nacimento;
            }else {
                System.out.println("Error: El año de nacimiento del usuario solo puede tener un máximo de cuatro números y sin espacios.");
            }
        }while (true); // Repite hasta que el email sea válido
    }

    //Metodo para mostrar los nombres de las categorias
    static List<Integer> obtenerIDUsuario() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_usuario FROM usuarios";

        try (PreparedStatement stm = mysqlconn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()){
            while (rs.next()) {
                ids.add(rs.getInt("id_usuario"));
            }

        }catch (SQLException e) {
            System.out.println("Error no existe ningún ids de esa usuario. " + e.getMessage());
            e.printStackTrace();
        }
        return ids;
    }

    // Método para validar el id usuario con un formato válido
    static String esidUsuarioElimnar(Scanner teclado) {
        do {
            List<Integer> id1 = obtenerIDUsuario();
            System.out.println("Lista de ids de los usuarios disponibles:");

            for (Integer ids : id1) {
                System.out.println(ids);
            }

            String id = pedirStringLine(teclado, "Introduce la ID del usuario a eliminar: ");

            // Eliminar todos los espacios en blanco
            id = id.replaceAll("\\s", "");

            if (id.matches("\\d+")) {
                return id;
            }else {
                System.out.println("Error. El id del proveedor solo puede contener números.");
            }
        }while (true); // Repite hasta que el id sea válido
    }

    //Metodo para mostrar los nombres de las categorias
    static List<String> obtenerNombreCategorias() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre_categoria FROM objetos.categorias";

        try (PreparedStatement stm = postgresConn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()){
            while (rs.next()) {
                nombres.add(rs.getString("nombre_categoria"));
            }

        }catch (SQLException e) {
            System.out.println("Error no existe ningún nombre de esa categoría. " + e.getMessage());
            e.printStackTrace();
        }
        return nombres;
    }

    //Método para mostrar los dni de los proveedores
   static List<String> obtenerNifsProveedores()  {
        List<String> nifs = new ArrayList<>();
        String sql = "SELECT (contacto).nif FROM objetos.proveedores";

        try (PreparedStatement stmt = postgresConn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nifs.add(rs.getString("nif"));
            }
        }catch (SQLException e) {
            System.out.println("Error no existe ese de nif de contacto en la base de datos." + e.getMessage());
            e.printStackTrace();
        }
        return nifs;
    }


    //Método para mostrar los ids de los proveedores
    static List<Integer> obtenerIDProveedores() {
        List<Integer> id = new ArrayList<>();

        String sql = "SELECT id_proveedor from objetos.proveedores";

        try (PreparedStatement stm = postgresConn.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                id.add(rs.getInt("id_proveedor"));
            }
        }catch (SQLException e) {
            System.out.println("Error no existe ese id del proveedor en la base de datos. " + e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

        // Método para validar y capturar los datos del producto
        static ArrayList<String> esNombreCrearProducto(Scanner teclado) {
            ArrayList<String> productoData = new ArrayList<>();

            while (true) {
                // Validación solo del nombre
                String nombre = pedirStringLine(teclado, "Ingrese el nombre del producto a insertar: ");


                // Validar que el nombre no esté vacío
                if (nombre.isEmpty()) {
                    System.out.println("Error: El nombre producto  no puede estar vacío.");
                    continue; // Si está vacío, vuelve a pedir el nombre
                }

                while (!nombre.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+")) {
                    System.out.println("Error. El nombre solo puede contener letras, números y sin espacios.");
                    nombre = pedirStringLine(teclado, "Ingrese el nombre del producto a insertar: ");
                }



                // Validación de los otros campos
                String precio = pedirStringLine(teclado, "Ingrese el precio del producto a insertar: ");

                // Validar que el precio no esté vacío
                if (precio.isEmpty()) {
                    System.out.println("Error: El precio  no puede estar vacío.");
                    continue; // Si está vacío, vuelve a pedir el precio
                }

                // Eliminar todos los espacios en blanco
                precio = precio.replaceAll("\\s", "");

                while (!precio.matches("\\d+(\\.\\d{1,2})?")) {
                    System.out.println("Error. El precio debe ser un número positivo, con hasta dos decimales y sin espacios.");
                    precio = pedirStringLine(teclado, "Ingrese el precio del producto a insertar: ");
                }

                String stock = pedirStringLine(teclado, "Ingrese el stock del producto a insertar: ");

                // Validar que el stock no esté vacío
                if (stock.isEmpty()) {
                    System.out.println("Error: El stock  no puede estar vacío.");
                    continue; // Si está vacío, vuelve a pedir el stock
                }

                // Eliminar todos los espacios en blanco
                stock = stock.replaceAll("\\s", "");

                while (!stock.matches("\\d+")) {
                    System.out.println("Error. El stock debe ser un número entero y sin espacios.");
                    stock = pedirStringLine(teclado, "Ingrese el stock del producto a insertar: ");
                }

                // Obtener y mostrar los nombres de las categorias  de los proveedores antes de pedir  la categoria del proveedor
                List<String> nombres = obtenerNombreCategorias();
                System.out.println("Lista de todas categorias:");
                for (String nombreC : nombres) {
                    System.out.println(nombreC);
                }

                String categoria = pedirStringLine(teclado, "Ingrese el nombre de la categoría del producto a insertar: ");

                // Validar que el nombre categoría no esté vacío
                if (categoria.isEmpty()) {
                    System.out.println("Error: El nombre de la categoría no puede estar vacío.");
                    continue; // Si está vacío, vuelve a pedir el el nombre de categoría
                }

                while (!categoria.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+")) {
                    System.out.println("Error. La categoría solo puede contener letras, números y  espacios.");
                    categoria = pedirStringLine(teclado, "Ingrese el nombre de la categoría del producto a insertar: ");
                }

                // Si todos los campos son válidos, los agregamos al ArrayList
                productoData.add(nombre);
                productoData.add(precio);
                productoData.add(stock);
                productoData.add(categoria);

                return productoData;  // Devolvemos los datos cuando son válidos
            }
        }

    //Método para obtener los nombres de los productos
    static List<String> obtenerNombreProducto() {
        List<String> nombre = new ArrayList<>();

        String sql = "SELECT nombre_producto from productos";

        try (PreparedStatement stm = mysqlconn.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                nombre.add(rs.getString("nombre_producto"));
            }
        }catch (SQLException e) {
            System.out.println("Error no existe ese nombre del producto en la base de datos. " + e.getMessage());
            e.printStackTrace();
        }
        return nombre;
    }

    //Método para validar el nombre producto con un formato válido
    static String esNombreProductoEliminar(Scanner teclado) {
        do {

            // Obtener y mostrar los nombres de los productos
            List<String> nombres = obtenerNombreProducto();
            System.out.println("Lista de todos los productos:");
            for (String nombreC : nombres) {
                System.out.println(nombreC);
            }

            String nombreProducto = pedirStringLine(teclado, "Introduce el nombre del producto a eliminar: ");
            
            // Validar que el nombre del producto no esté vacío
            if (nombreProducto.isEmpty()) {
                System.out.println("Error: El nombre producto no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el nombre del producto
            }

            if (nombreProducto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+")) {
                return nombreProducto;
            }else {
                System.out.println("Error: El nombre del producto solo debe contener letras, números y con espacios.");
            }
        }while (true);
    }

    // Método para validar el stock con un formato válido
    static String esStock(Scanner teclado) {
        do {
            String stock = pedirStringLine(teclado, "Introduce stock para listar (menos de X unidades disponibles): ");

            // Validar que el stock no esté vacío
            if (stock.isEmpty()) {
                System.out.println("Error: El stock  no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el stock
            }

            // Eliminar todos los espacios en blanco
            stock = stock.replaceAll("\\s", "");

            if (stock.matches("\\d+")) {
                return stock;
            }else {
                System.out.println("Error. El stock de los productos solo puede contener números y sin espacios.");
            }
        }while (true);
    }

    //Metodo para mostrar los nombres de las categorias
    static List<String> obtenerIdCatgoria() {
        List<String> ids = new ArrayList<>();
        String sql = "SELECT id_categoria FROM objetos.categorias";

        try (PreparedStatement stm = postgresConn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()){
            while (rs.next()) {
                ids.add(rs.getString("ids_categoria"));
            }

        }catch (SQLException e) {
            System.out.println("Error no existe ningún nombre de esa categoría. " + e.getMessage());
            e.printStackTrace();
        }
        return ids;
    }

    // Método para validar el id de la categoría con un formato válido
    static String esidCategoria(Scanner teclado) {
        do {
            String id = pedirStringLine(teclado, "Ingrese el ID de la categoría: ");

            // Validar que el id no esté vacío
            if (id.isEmpty()) {
                System.out.println("Error: El id no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el id
            }

            // Eliminar todos los espacios en blanco
            id = id.replaceAll("\\s", "");

            if (id.matches("\\d+")) {
                return id;
            }else {
                System.out.println("Error. El id de la categoría solo puede contener números y sin espacios.");
            }
        }while (true); // Repite hasta que el id sea válido
    }

    // Método para validar el nif de la categoría con un formato válido
    static String esNifCrearProducto(Scanner teclado) {
        do {
            List<String> nifsProveedores = obtenerNifsProveedores();
            System.out.println("Lista de NIFs de los proveedores disponibles:");

            for (String nifProveedor : nifsProveedores) {
                System.out.println(nifProveedor);
            }

            String nif = pedirStringLine(teclado, "Ingrese el dni del proveedor del producto a insertar: ");


            // Validar que el nif no esté vacío
            if (nif.isEmpty()) {
                System.out.println("Error: El nif no puede estar vacío.");
                continue; // Si está vacío, vuelve a pedir el stock
            }

            // Eliminar todos los espacios en blanco
            nif = nif.replaceAll("\\s", "");

            if (nif.matches("\\d{8}[a-zA-Z]")) {
                return nif;
            }else {
                System.out.println("Error: El nif del proveedor solo debe contener 8 números y letra y sin espacios.");
            }
        }while (true); // Repite hasta que el nif sea válido
    }
    }

