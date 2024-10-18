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
import java.util.ArrayList;
import java.util.List;

public class AplicacionAutores {

    private final String RUTA_FICHERO = "./src/model/autoresJSON.json";
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

    public JSONArray getAutores() {
        return obtenerAutoresJson(); // Llama al método privado
    }

    // Método que recorre JSONArray de autores para recorrer la posición del autor que desea borrar
    private int obtenerPosicionAutor(String nombreAutor, JSONArray autores){
        for (int i = 0; i<autores.length();i++){
            JSONObject autor = autores.getJSONObject(i);
            if (autor.getString("autor").equalsIgnoreCase(nombreAutor)){
                return (i);
            }
        }
        return -1;
    }

    private JSONObject obtenerAutoresJson(String nombreAutor){
        JSONArray autores=obtenerAutoresJson();
        int pos = obtenerPosicionAutor(nombreAutor,autores);
        return pos>=0 ? autores.getJSONObject(pos):null;
    }

    // Método para actualizar la lista de autores en el archivo JSON
    public void actualizarDatosAutores() {
        // Obtener la lista de autores actual desde el archivo
        JSONArray autoresActualizados = obtenerAutoresJson();

        // Asegurarse de que el JSONArray no sea nulo antes de proceder
        if (autoresActualizados != null) {
            // Guardar la lista actualizada en el archivo
            guardarFicheroJson(autoresActualizados);
            System.out.println("Datos de autores actualizados exitosamente.");
        } else {
            System.out.println("No se pudo obtener la lista de autores.");
        }
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
            System.out.println("Lista de autores cargada: " + autores.toString()); // Depuración

            boolean autorEncontrado = false;  // Bandera para saber si se encuentra el autor
            boolean tituloEncontrado = false; // Bandera para saber si se encuentra el título

            // Convertir el nombre del autor y el título del libro ingresados a minúsculas
            String nombreAutorLower = nombreAutor.toLowerCase().trim();
            String tituloLibroLower = tituloLibroAutor.toLowerCase().trim();

            System.out.println("Autor ingresado (minúsculas): " + nombreAutorLower); // Depuración
            System.out.println("Título ingresado (minúsculas): " + tituloLibroLower); // Depuración

            // Iterar sobre cada autor en el JSON
            for (int i = 0; i < autores.length(); i++) {
                JSONObject libro = autores.getJSONObject(i);

                // Obtener el autor y título en minúsculas desde el JSON
                String autorJsonLower = libro.getString("autor").toLowerCase().trim();
                String tituloJsonLower = libro.getString("titulo").toLowerCase().trim();

                System.out.println("Comparando con autor en JSON: " + autorJsonLower); // Depuración
                System.out.println("Comparando con título en JSON: " + tituloJsonLower); // Depuración

                // Verifica si el autor coincide, comparando insensible a mayúsculas
                if (autorJsonLower.equals(nombreAutorLower)) {
                    autorEncontrado = true; // Autor encontrado

                    // Verifica si el título coincide, comparando insensible a mayúsculas
                    if (tituloJsonLower.equals(tituloLibroLower)) {
                        tituloEncontrado = true; // Título encontrado
                        break; // Sale del bucle si encuentra coincidencia
                    }
                }
            }

            // Respuesta basada en los resultados
            if (!autorEncontrado) {
                JOptionPane.showMessageDialog(null, "El autor '" + nombreAutor + "' no existe.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false; // Autor no encontrado
            } else if (!tituloEncontrado) {
                JOptionPane.showMessageDialog(null, "El título '" + tituloLibroAutor + "' no corresponde al autor '" + nombreAutor + "'.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false; // Título no encontrado para el autor
            } else {
                return true; // Autor y título encontrados correctamente
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

        JOptionPane.showMessageDialog(null, "Cerro Sesion Correctamente.", "Cerrar Sesion", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para comprobar si existe ese libro con ese autor
    public boolean autorYaExiste(String nombre, String titulo, JSONArray autores) {
        for (int i = 0; i < autores.length(); i++) {
            JSONObject autor = autores.getJSONObject(i);
            String nombreExistente = autor.getString("autor").toLowerCase().trim();
            String tituloExistente = autor.getString("titulo").toLowerCase().trim();

            // Comparar nombre y título ignorando mayúsculas/minúsculas
            if (nombreExistente.equalsIgnoreCase(nombre) && tituloExistente.equalsIgnoreCase(titulo)) {
                return true;  // El autor con el mismo libro ya existe
            }
        }
        return false;  // El autor no existe
    }

    public void crearAutor(String nombre, String titulo, String paginas, String editorial) {
       // Obtener la lista de autores existente
        JSONArray autores = obtenerAutoresJson();

        // Comprobar si el autor existe
       if(obtenerPosicionAutor(nombre, autores)!=-1) {
           JOptionPane.showMessageDialog(null, "El autor ya existe con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

        // Crear un nuevo objeto JSON para el autor
        JSONObject nuevoAutor = new JSONObject();
        nuevoAutor.put("autor", nombre);
        nuevoAutor.put("titulo", titulo);
        nuevoAutor.put("paginas", paginas);
        nuevoAutor.put("editorial", editorial);

        // Agregar el nuevo autor al arreglo de autores
        autores.put(nuevoAutor);

        // Guardar la lista actualizada de autores en el archivo JSON con formato bonito
        guardarFicheroJson(autores);

        JOptionPane.showMessageDialog(null, "Autor creado correctamente");

    }


    public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo) {
        // Obtener la lista de autores
        JSONArray autores = obtenerAutoresJson();

        // Obtener la posición del autor
        int posicion = obtenerPosicionAutor(nombreAutor, autores);

        if (posicion >= 0) {
            // Obtener el título actual del autor
            String tituloActual = autores.getJSONObject(posicion).getString("titulo");

            // Verificar si el nuevo título ya existe
            boolean tituloExiste = false;

            // Recorrer la lista de autores para verificar títulos
            for (int i = 0; i < autores.length(); i++) {
                // Evitar comparar con el título actual
                if (i != posicion) {
                    String tituloAutor = autores.getJSONObject(i).getString("titulo");
                    // Comparar títulos sin importar mayúsculas o minúsculas
                    if (tituloAutor.equalsIgnoreCase(nuevoTitulo)) {
                        tituloExiste = true;
                        break;
                    }
                }
            }

            if (tituloExiste) {
                JOptionPane.showMessageDialog(null, "El título ya existe para otro autor.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                // Cambiar el título solo si es diferente
                if (!tituloActual.equalsIgnoreCase(nuevoTitulo)) {
                    // Cambiar el título
                    autores.getJSONObject(posicion).put("titulo", nuevoTitulo);
                    guardarFicheroJson(autores);
                    JOptionPane.showMessageDialog(null, "Título cambiado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "El nuevo título es el mismo que el título actual.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Autor no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para borrar el autor de la lista de JSONArray de autores
    public boolean borrarAutor(String nombreAutor) {
        JSONArray autores = obtenerAutoresJson(); // Obtener la lista de autores
        if (autores != null) {
            boolean autorEliminado = false; // Variable para verificar si se eliminó al menos un autor
            StringBuilder librosEliminados = new StringBuilder(); // Para almacenar los títulos de los libros que se eliminan

            // Usamos un bucle para recorrer el array y eliminar todos los libros del autor
            for (int i = autores.length() - 1; i >= 0; i--) { // Iterar desde el final hacia el inicio
                JSONObject libro = autores.getJSONObject(i);
                // Comprobar si el autor coincide
                if (libro.getString("autor").equalsIgnoreCase(nombreAutor)) {
                    librosEliminados.append(libro.getString("titulo")).append("\n"); // Almacenar el título del libro
                    autores.remove(i); // Borrar el objeto del autor
                    autorEliminado = true; // Indicar que se ha eliminado un libro del autor
                }
            }

            if (autorEliminado) {
                // Mostrar un mensaje con los libros eliminados
                JOptionPane.showMessageDialog(null, "El autor " + nombreAutor + " ha sido eliminado junto con los siguientes libros:\n" + librosEliminados.toString());

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

    // Método para mostrar el listado de los libros de ese autor
    public List<String> obtenerLibrosPorAutor(String nombreAutor) {
        List<String> titulos = new ArrayList<>();

        JSONArray libros = obtenerAutoresJson();

        System.out.println("Libros cargados: " + libros); // Agregar esta línea para ver los libros cargados

        if (libros != null) {
            for (int i = 0; i < libros.length(); i++) {
                JSONObject libro = libros.getJSONObject(i);
                String autor = libro.getString("autor");
                String titulo = libro.getString("titulo");

                System.out.println("Autor: " + autor + ", Título: " + titulo); // Verificar cada autor y título

                if (autor.equalsIgnoreCase(nombreAutor)) {
                    titulos.add(titulo);
                }
            }
        }

        System.out.println("Títulos encontrados para " + nombreAutor + ": " + titulos); // Verificar la lista de títulos
        return titulos;
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

        // Limpiar los datos y que no deje por defecto
        ventanaInicioSesion.limpiarCampos();

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

            // Crear la ventana de cambiar título y pasar el nombre del autor (no el título)
            VentanaCambiarTitulo ventanaCambiarTitulo = new VentanaCambiarTitulo(this, nombreAutor); // Pasar nombreAutor
            ventanaCambiarTitulo.setVisible(true);
        } else {
            // Mensaje informativo si no se encuentra al autor
            JOptionPane.showMessageDialog(null, "Autor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentanaBorrarAutor(String nombreAutor) {
        // Obtener los datos del autor desde el JSON
        JSONObject autorJson = obtenerAutoresJson(nombreAutor);

        // Comprobar si el autor fue encontrado
        if (autorJson != null) {
            // Crear una nueva instancia de VentanaBorrarAutor
            VentanaBorrarAutor ventanaBorrarAutor = new VentanaBorrarAutor(this, nombreAutor);
            ventanaBorrarAutor.setVisible(true); // Mostrar la ventana de borrar autor
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
