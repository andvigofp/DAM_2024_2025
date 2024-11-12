package Ej503;

import java.sql.*;
import java.util.Scanner;

public class MEj503 {

    // Método menú de opciones
    public void Menu(Scanner teclado, Connection conn) throws SQLException {
        final String menu = "1. INSERTAR UN NUEVO POKEMON."
                + "\n2. MODIFICAR UN POKEMON."
                + "\n3. ELIMINAR UN POKEMON."
                + "\n4. CONSULTAR UN POKEMON."
                + "\n5. SALIR";

        int opcion = -1;

        do {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    insertarPokemon(teclado, conn);
                    break;
                case 2:
                    modificarPokemon(teclado, conn);
                    break;
                case 3:
                    eliminarPokemon(teclado, conn);
                    break;
                case 4:
                    consultarPokemons(conn);
                    break;
                case 5:
                    System.out.println("Fin del programa");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-5");
            }
        } while (opcion != 5);
    }

    //Método insertar los datos en la BD pokemons
    static void insertarPokemon(Scanner teclado, Connection conn) throws SQLException {
        System.out.println("Nombre de Pokémon: ");
        String nombre = teclado.nextLine();
        System.out.println("Tipo del Pokémon: ");
        String tipo = teclado.nextLine();
        System.out.println("Nivel del Pókemon: ");
        int nivel = teclado.nextInt();

        String insertSQL = "INSERT INTO objetos.Pokemons (pokemon) VALUES (ROW(?, ?, ?))";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, tipo);
        preparedStatement.setInt(3, nivel);

        int rowInserted = preparedStatement.executeUpdate();

        if (rowInserted > 0) {
            System.out.println("Pokémon insertado con éxito.");
        }else {
            System.out.println("No se pudo insertar el Pokémon");
        }
    }

    //Método para modificar datos en BD pokemons
    static void modificarPokemon(Scanner teclado, Connection conn) throws SQLException {
        System.out.println("ID del Pokémon a modificar: ");
        int idPokemon = teclado.nextInt();
        teclado.nextLine(); //Consumir la nueva línea

        System.out.println("Nuevo nombre del Pokémon: ");
        String nuevoNombre = teclado.nextLine();
        System.out.println("Nuevo tipo del Pokémon: ");
        String nuevoTipo = teclado.nextLine();
        System.out.println("Nuevo nivel del Pokémon: ");
        int nuevoNivel = teclado.nextInt();

        String updateSQL = "UPDATE objetos.Pokemons SET pokemon = ROW(?, ?, ?) WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setString(2, nuevoTipo);
        preparedStatement.setInt(3, nuevoNivel);
        preparedStatement.setInt(4, idPokemon);

        int rowUpdated = preparedStatement.executeUpdate();

        if (rowUpdated > 0) {
            System.out.println("Pokémon actualizado con éxito.");
        }else {
            System.out.println("No se pudo actualizar el Pokémon. Verifique el ID.");
        }
    }

    static void eliminarPokemon(Scanner teclado, Connection conn) throws SQLException {
        System.out.println("ID del Pokémon a eliminar: ");
        int idPokemon = teclado.nextInt();

        String deleteSQL = "DELETE FROM objetos.Pokemons WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, idPokemon);

        int rowDeleted = preparedStatement.executeUpdate();

        if (rowDeleted > 0) {
            System.out.println("Pokémon eliminado con éxito.");
        }else {
            System.out.println("No se pudo eliminar el Pokémon. Verifique el ID.");
        }
    }
    //Método para consultar los pokemons
    static void consultarPokemons(Connection conn) throws SQLException {
        String querySQL = "SELECT p.id, (p.pokemon).nombre AS nombre, (p.pokemon).tipo AS tipo, (p.pokemon).nivel AS nivel FROM objetos.Pokemons p";

        PreparedStatement preparedStatement = conn.prepareStatement(querySQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Lista de Pokémon:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String tipo = resultSet.getString("tipo");
            int nivel = resultSet.getInt("nivel");

            System.out.printf("ID: %d, Nombre: %s, Tipo: %s, Nivel: %d%n", id, nombre, tipo, nivel);
        }

        resultSet.close();
        preparedStatement.close();
    }

}
