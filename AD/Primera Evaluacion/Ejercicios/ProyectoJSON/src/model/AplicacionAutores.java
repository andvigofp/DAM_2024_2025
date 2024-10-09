package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gui.VentanaBorrarAutor;
import gui.VentanaCambiarTitulo;
import gui.VentanaCrearAutor;
import gui.VentanaInicioSesion;
import gui.VentanaMenuAutor;
import gui.VentanaVerDatos;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AplicacionAutores
{

    private final String RUTA_FICHERO = "./src/model/autoresJSON.txt";
    private VentanaInicioSesion ventanaInicioSesion;
    private VentanaCrearAutor ventanaCrearAutor;
    private VentanaMenuAutor ventanaMenuAutor;
    private VentanaVerDatos ventanaVerDatos;
    private VentanaCambiarTitulo ventanaCambiarTitulo;
    private VentanaBorrarAutor ventanaBorrarAutor;

    // Método para crear el archivo .txt
    private void crearFicheroJson()	{
        File file = new File(RUTA_FICHERO);
        if (!file.exists()) {
            try {
                file.createNewFile();
                // Inicializar el archivo con un arreglo vacío
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // Escribe un arreglo vacío
                }
            } catch (IOException e) {
                System.out.println("No se ha podido crear el archivo ");
                e.printStackTrace();
            }
        }
    }

    // Método para guardar los datos de vuelta en el archivo JSON
    private void guardarFicheroJson(JSONArray autores) {
        try (FileWriter file = new FileWriter(RUTA_FICHERO)){
            file.write(autores.toString());
            file.write(System.lineSeparator()); // Agregar salto de línea
            file.flush();
        }catch (IOException e) {
            System.out.println("Error al guardar el fichero.");
            e.printStackTrace();
        }
    }

    private JSONArray obtenerAutoresJson() {
        JSONArray autores = new JSONArray();
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)), StandardCharsets.UTF_8);

            if (contenido.trim().isEmpty()) {
                // Si el archivo está vacío, inicializamos un JSONArray vacío
                return autores;
            }
            autores = new JSONArray(contenido);
        } catch (IOException e) {
            System.out.println("Error: el archivo especificado no existe o no se puede leer");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Error: el contenido del archivo no es un JSONArray válido");
            e.printStackTrace();
        }
        return autores;
    }

    // Método que recorre JSONArray de autores para recorrer la posición del autor que desea borrar
    private int obtenerPosicionAutor(String nombreAutor, JSONArray autores){
        for (int i = 0; i < autores.length(); i++) {
            JSONObject autor = autores.getJSONObject(i);
            if (autor.getString("nombre").equals(nombreAutor)) {
                return i; // Retorna la posición si encuentra el autor
            }
        }
        return -1; // Retorna -1 si no encuentra el autor
    }

    private JSONObject obtenerAutoresJson(String nombreAutor){
        JSONArray autores = obtenerAutoresJson(); // Otiene la lista de autores

        if (autores != null) {
            for (int i=0; i<autores.length(); i++) {
                JSONObject autor = autores.getJSONObject(i);

                // Compara el nombre del autor con el nombre buscado
                if (autor.getString("nombre").equals(nombreAutor)) {
                    return autor; // Retorna JSONObject del autor enocntrado
                }
            }
        }
        return null; // Retorna null si no se encuentra el autor
    }


    // Método para ejecutar
    public void ejecutar(){
        crearFicheroJson();
        ventanaInicioSesion = new VentanaInicioSesion(this);
        ventanaInicioSesion.setVisible(true);
    }

    public boolean iniciarValidacion(String nombreAutor, String tituloLibroAutor) {
        // Obtener la lista de autores
        JSONArray autores = obtenerAutoresJson();

        if (autores != null) {
            // Buscar la posición del autor
            int posicionAutor = obtenerPosicionAutor(nombreAutor, autores);

            if (posicionAutor != -1) {
                // El autor existe, ahora verificar el título
                JSONObject autor = autores.getJSONObject(posicionAutor);
                JSONArray libros = autor.getJSONArray("libros");

                // Comprobar si el título corresponde al autor
                boolean tituloEncontrado = false;
                for (int i = 0; i < libros.length(); i++) {
                    if (libros.getString(i).equals(tituloLibroAutor)) {
                        tituloEncontrado = true;
                        break;
                    }
                }

                if (!tituloEncontrado) {
                    // Mostrar mensaje si el título no corresponde con el autor
                    JOptionPane.showMessageDialog(null, "Combinación de autor y título no existente.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false; // No se puede validar
                }

                // Si el título es correcto, continuar con la validación
                return true; // Validación exitosa

            } else {
                // Mostrar mensaje si el autor no existe
                JOptionPane.showMessageDialog(null, "El autor no existe.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false; // No se puede validar
            }
            
        } else {
            // Si no se puede obtener el JSON, manejar el error
            JOptionPane.showMessageDialog(null, "No se pudo obtener la lista de autores.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void cerrarSesion(){
        // Cerrar todas las ventanas relacionadas con la sesión del autor
        if (ventanaMenuAutor !=null) ventanaMenuAutor.dispose();
        if (ventanaVerDatos != null) ventanaVerDatos.dispose();
        if (ventanaBorrarAutor != null) ventanaBorrarAutor.dispose();
        if (ventanaCambiarTitulo != null) ventanaCambiarTitulo.dispose();
        if (ventanaCrearAutor != null) ventanaCrearAutor.dispose();
        if (ventanaInicioSesion != null) ventanaInicioSesion.dispose();

        System.out.println("Sesión cerrada correctamente.");
    }

    public void crearAutor(String nombre, String titulo, String paginas, String editorial){
        // Crear un nuevo objecto JSON para el autor
        JSONObject nuevoAutor = new JSONObject();
        nuevoAutor.put("nombre", nombre);

        // Crear un arreglo JSON para los libros
        JSONArray libros = new JSONArray();
        libros.put(titulo); // Agregar el título del libro al arreglo
        nuevoAutor.put("libros", libros);

        // Agregar más detalles, si es necesario
        nuevoAutor.put("paginas", paginas);
        nuevoAutor.put("editorial", editorial);

        // Obtener la lista de autores existente
        JSONArray autores = obtenerAutoresJson();

        // Comprobar si la lista de autores se obtuvo correctamente
        if (autores != null) {
            // Agregar el nuevo autor al arreglo de autores
            autores.put(nuevoAutor);

            // Guardar la lista actualizada de autores en el archivo JSON
            guardarFicheroJson(autores);

            System.out.println("Autor creado con éxito: " + nombre);
        } else {
            System.out.println("No se pudo obtener la lista de autores para agregar el nuevo autor.");
        }
    }

    public boolean cambiarTituloLibro(String nombreAutor, String nuevoTitulo) {
            // Obtener la lista de autores
            JSONArray autores = obtenerAutoresJson();

            if (autores != null) {
                // Obtener la posición del autor
                int posicion = obtenerPosicionAutor(nombreAutor, autores);
                if (posicion != -1) {
                    // Obtener el objeto autor
                    JSONObject autor = autores.getJSONObject(posicion);

                    // Obtener el arreglo de títulos de libros del autor
                    JSONArray libros = autor.getJSONArray("libros");

                    // Asegúrate de que haya libros en el arreglo
                    if (libros.length() > 0) {
                        // Cambiar el título del primer libro como ejemplo
                        libros.put(0, nuevoTitulo); // Cambia 0 por la posición del libro que deseas cambiar

                        // Guardar el JSONArray actualizado en el archivo
                        guardarFicheroJson(autores);

                        return true; // Retorna true si el cambio fue exitoso
                    } else {
                        System.out.println("No hay libros asociados al autor.");
                    }
                } else {
                    System.out.println("Autor no encontrado.");
                }
            } else {
                System.out.println("No se pudo obtener la lista de autores.");
            }
            return false; // Retorna false si no se pudo cambiar el título
        }

    // Método para borrar el autor de la lista de JSONArray de autores
    public boolean borrarAutor(String nombreAutor){
        JSONArray autores = obtenerAutoresJson(); // Obtener la lista de autores
        if (autores !=null) {
            int posicion = obtenerPosicionAutor(nombreAutor, autores); // Obtener la posición del autor a borrar

            if (posicion != -1) {
                autores.remove(posicion); // Borrar el autor de la lista
                guardarFicheroJson(autores); // Guardar la lista actualizada en el fichero
                return true;
            }
        }
        return false;
    }

    public void mostrarVentanaCrearAutor(){
        // Comprobar si la ventana ya está creada, si no, crear una nueva instancia
        if (ventanaCrearAutor == null) {
            ventanaCrearAutor = new VentanaCrearAutor(this);
        }

        // Hacer visible la ventana
        ventanaCrearAutor.setVisible(true);
    }

    public void mostrarVentanaVerDatos(String nombreAutor){
        // Obtener los datos del autor desde el JSON
        JSONObject autorJson = obtenerAutoresJson(nombreAutor);

        // Comprobar si el autor fue encontrado
        if (autorJson != null) {
            String paginas = autorJson.getString("paginas");
            String editorial = autorJson.getString("editorial");

            // Crear la ventana de ver datos del autor
            ventanaVerDatos = new VentanaVerDatos(this, nombreAutor, paginas, editorial);

            // Hacer visible la ventana
            ventanaVerDatos.setVisible(true);
        } else {
            System.out.println("Autor no encontrado.");
            // Aquí podrías agregar un diálogo de advertencia si lo deseas
            JOptionPane.showMessageDialog(null, "Autor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentanaCambiarTitulo(String nombreAutor) {
        // Obtener los datos del autor desde el JSON
        JSONObject autorJson = obtenerAutoresJson(nombreAutor);

        // Comprobar si el autor fue encontrado
        if (autorJson != null) {
            // Obtener el arreglo de títulos de libros del autor
            JSONArray libros = autorJson.getJSONArray("libros");

            // Aquí asumimos que el primer libro es el que queremos cambiar
            // Si hay múltiples libros, podrías implementar lógica adicional para seleccionar uno
            if (libros.length() > 0) {
                String tituloActual = libros.getString(0); // Obtener el título actual

                // Crear la ventana de cambiar título y pasar el título actual
                VentanaCambiarTitulo ventanaCambiarTitulo = new VentanaCambiarTitulo(this, nombreAutor);
                ventanaCambiarTitulo.setVisible(true);
            } else {
                // Mensaje informativo si no hay libros
                JOptionPane.showMessageDialog(null, "El autor no tiene libros registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Mensaje informativo si no se encuentra al autor
            JOptionPane.showMessageDialog(null, "Autor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentanaBorrarAutor(String nombreAutor){
        // Obtener los datos del autor desde el JSON
        JSONObject autorJson = obtenerAutoresJson(nombreAutor);

        // Comprobar si el autor fue encontrado
        if (autorJson != null) {
            // Mostrar una ventana de confirmación para borrar el autor
            int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de que desea borrar al autor " + nombreAutor + "?",
                    "Confirmar Borrado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            // Si el usuario confirma la acción
            if (opcion == JOptionPane.YES_OPTION) {
                // Llamar al método para borrar el autor
                boolean eliminado = borrarAutor(nombreAutor);
                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Autor borrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo borrar al autor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Mensaje informativo si no se encuentra al autor
            JOptionPane.showMessageDialog(null, "Autor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarMenuAutor(String nombreAutor){
        // Crear una nueva ventana para el menú del autor
        VentanaMenuAutor ventanaMenu = new VentanaMenuAutor(this, nombreAutor);
        ventanaMenu.setVisible(true);
    }
}
