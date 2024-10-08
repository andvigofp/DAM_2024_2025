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

public class AplicacionAutores {

    private final String RUTA_FICHERO = "./src/model/autoresJSON.txt";
    private VentanaInicioSesion ventanaInicioSesion;
    private VentanaCrearAutor ventanaCrearAutor;
    private VentanaMenuAutor ventanaMenuAutor;
    private VentanaVerDatos ventanaVerDatos;
    private VentanaCambiarTitulo ventanaCambiarTitulo;
    private VentanaBorrarAutor ventanaBorrarAutor;

    // Método para crear el archivo .txt
    private void crearFicheroJson() {
        File file = new File(RUTA_FICHERO);
        if (!file.exists()) {
            try {
                // Intentar crear el archivo si no existe
                file.createNewFile();

                // Verificar si el archivo se ha creado correctamente
                if (file.exists()) {
                    System.out.println("Archivo creado exitosamente.");

                    // Inicializar el archivo con un arreglo vacío
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write("[]"); // Escribe un arreglo vacío
                    }
                } else {
                    System.out.println("El archivo no se creó.");
                    JOptionPane.showMessageDialog(null, "El archivo no se pudo crear.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                System.out.println("No se ha podido crear el archivo.");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al crear el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("El archivo JSON ya existe.");
        }
    }

    // Método para guardar los datos de vuelta en el archivo JSON
    private void guardarFicheroJson(JSONArray autores) {
        try (FileWriter file = new FileWriter(RUTA_FICHERO)) {
            file.write(autores.toString(4)); // El '4' indica el nivel de indentación (4 espacios)
            file.flush();
        } catch (IOException e) {
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
    private int obtenerPosicionAutor(String nombreAutor, JSONArray autores) {
        for (int i = 0; i < autores.length(); i++) {
            JSONObject autor = autores.getJSONObject(i);

            // Aquí estamos comparando el nombre del autor
            String autorNombre = autor.getString("autor");
            System.out.println("Buscando autor: " + nombreAutor);
            System.out.println("Comparando con: " + autorNombre);

            if (autorNombre.equalsIgnoreCase(nombreAutor)) {
                return i; // Devolver la posición si se encuentra el autor
            }
        }
        return -1; // No se encontró el autor
    }

    private JSONObject obtenerAutoresJson(String nombreAutor) {
        JSONArray autores = obtenerAutoresJson(); // Obtén la lista de autores
        for (int i = 0; i < autores.length(); i++) {
            JSONObject autor = autores.getJSONObject(i);
            // Compara el nombre del autor (puedes ajustar la comparación si es necesario)
            if (autor.getString("autor").equalsIgnoreCase(nombreAutor)) {
                System.out.println("Autor encontrado: " + autor.toString()); // Mensaje de depuración
                return autor; // Retorna el autor si se encuentra
            }
        }
        System.out.println("Autor no encontrado: " + nombreAutor); // Mensaje de depuración
        return null; // Retorna null si no se encuentra el autor
    }


    // Método para ejecutar
    public void ejecutar() {
        crearFicheroJson();
        ventanaInicioSesion = new VentanaInicioSesion(this);
        ventanaInicioSesion.setVisible(true);
    }

    public boolean iniciarValidacion(String nombreAutor, String tituloLibroAutor) {
        JSONArray autores = obtenerAutoresJson(); // Obtener la lista de autores

        if (autores != null) {
            boolean tituloEncontrado = false; // Inicializa como falso

            // Itera sobre cada autor en el JSON
            for (int i = 0; i < autores.length(); i++) {
                JSONObject libro = autores.getJSONObject(i);

                // Verifica si el autor coincide
                if (libro.getString("autor").equalsIgnoreCase(nombreAutor)) {
                    // Verifica si el título coincide
                    if (libro.getString("titulo").equalsIgnoreCase(tituloLibroAutor)) {
                        tituloEncontrado = true; // Título encontrado
                        break; // Sale del bucle
                    }
                }
            }

            if (tituloEncontrado) {
                return true; // Validación exitosa
            } else {
                JOptionPane.showMessageDialog(null, "Combinación de autor y título no existente.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false; // No se puede validar
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo obtener la lista de autores.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void cerrarSesion() {
        // Cerrar todas las ventanas relacionadas con la sesión del autor
        if (ventanaMenuAutor != null) ventanaMenuAutor.dispose();
        if (ventanaVerDatos != null) ventanaVerDatos.dispose();
        if (ventanaBorrarAutor != null) ventanaBorrarAutor.dispose();
        if (ventanaCambiarTitulo != null) ventanaCambiarTitulo.dispose();
        if (ventanaCrearAutor != null) ventanaCrearAutor.dispose();
        if (ventanaInicioSesion != null) ventanaInicioSesion.dispose();

        System.out.println("Sesión cerrada correctamente.");
    }

    public void crearAutor(String nombre, String titulo, String paginas, String editorial) {
        // Crear un nuevo objeto JSON para el autor
        JSONObject nuevoAutor = new JSONObject();
        nuevoAutor.put("autor", nombre);
        nuevoAutor.put("titulo", titulo);
        nuevoAutor.put("paginas", paginas);
        nuevoAutor.put("editorial", editorial);

        // Obtener la lista de autores existente
        JSONArray autores = obtenerAutoresJson();

        // Comprobar si la lista de autores se obtuvo correctamente
        if (autores != null) {
            // Agregar el nuevo autor al arreglo de autores
            autores.put(nuevoAutor);

            // Guardar la lista actualizada de autores en el archivo JSON con formato bonito
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
            // Obtener la posición del autor buscando por nombre del autor (y no por el título)
            int posicion = obtenerPosicionAutor(nombreAutor, autores);
            if (posicion != -1) {
                // Obtener el objeto autor
                JSONObject autor = autores.getJSONObject(posicion);

                // Cambiar el título (es un String)
                autor.put("titulo", nuevoTitulo);

                // Guardar el JSONArray actualizado en el archivo
                guardarFicheroJson(autores);

                System.out.println("Título cambiado correctamente.");
                return true;
            } else {
                System.out.println("Autor no encontrado.");
            }
        } else {
            System.out.println("No se pudo obtener la lista de autores.");
        }
        return false;
    }

    // Método para borrar el autor de la lista de JSONArray de autores
    public boolean borrarAutor(String nombreAutor) {
        JSONArray autores = obtenerAutoresJson(); // Obtener la lista de autores
        if (autores != null) {
            boolean autorEliminado = false; // Variable para verificar si se eliminó al menos un autor

            // Usamos un bucle para recorrer el array y eliminar todos los libros del autor
            for (int i = autores.length() - 1; i >= 0; i--) { // Iterar desde el final hacia el inicio
                JSONObject libro = autores.getJSONObject(i);
                // Comprobar si el autor coincide
                if (libro.getString("autor").equalsIgnoreCase(nombreAutor)) {
                    autores.remove(i); // Borrar el objeto del autor
                    autorEliminado = true; // Indicar que se ha eliminado un libro del autor
                }
            }

            if (autorEliminado) {
                guardarFicheroJson(autores); // Guardar la lista actualizada en el fichero
                return true; // Retornar true si se eliminó al menos un autor
            } else {
                // El autor no fue encontrado
                JOptionPane.showMessageDialog(null, "No se encontró el autor a borrar.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Manejar el caso en el que no se pudo obtener la lista de autores
            JOptionPane.showMessageDialog(null, "No se pudo obtener la lista de autores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Retornar false si no se pudo obtener la lista de autores
    }



    public void mostrarVentanaCrearAutor() {
        // Comprobar si la ventana ya está creada, si no, crear una nueva instancia
        if (ventanaCrearAutor == null) {
            ventanaCrearAutor = new VentanaCrearAutor(this);
        }

        // Limpiar los datos y que no deje por defecto
        ventanaCrearAutor.limpiarCampos();
        // Hacer visible la ventana
        ventanaCrearAutor.setVisible(true);
    }

    // Método para mostar la Ventana de valiación
    public void mostrarVentanaInicioSesion() {
        // Comprobar si la ventana ya está creada, si no, crear una nueva instancia
        if (ventanaInicioSesion == null) {
            ventanaInicioSesion = new VentanaInicioSesion(this);
        }

        // Hacer visible la ventana
        ventanaInicioSesion.setVisible(true);
    }

    public void mostrarVentanaVerDatos(String nombreAutor) {
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
        // Obtener los datos del autor desde el JSON usando su nombre
        JSONObject autorJson = obtenerAutoresJson(nombreAutor);

        // Comprobar si el autor fue encontrado
        if (autorJson != null) {
            // Obtener el título actual (como un String, no JSONArray)
            String tituloActual = autorJson.getString("titulo"); // Es un String, no un JSONArray

            // Crear la ventana de cambiar título y pasar el nombre del autor (no el título)
            VentanaCambiarTitulo ventanaCambiarTitulo = new VentanaCambiarTitulo(this, nombreAutor); // Pasar nombreAutor
            ventanaCambiarTitulo.setVisible(true);
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
