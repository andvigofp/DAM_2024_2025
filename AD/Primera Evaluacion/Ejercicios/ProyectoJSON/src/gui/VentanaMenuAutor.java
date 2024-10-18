package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;
import org.json.JSONArray;
import org.json.JSONObject;

public class VentanaMenuAutor extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaMenuAutor;
    private JTextPane textoNombreAutor;
    private JButton btnVerDatos;
    private JButton btnCambiarTituloLibro;
    private JButton btnBorrarAutor;
    private JButton btnCerrarValidacion;
    private JButton btnVerLibros; // Nuevo botón para ver libros
    private AplicacionAutores app;
    private String nombreAutor;

    public VentanaMenuAutor(AplicacionAutores app, String nombreAutor) {
        this.app = app;
        this.nombreAutor = nombreAutor;

        setTitle("Aplicación autores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 325, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaMenuAutor = new JLabel("Menú del autor:");
        etiquetaMenuAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaMenuAutor.setBounds(10, 24, 147, 14);
        contentPane.add(etiquetaMenuAutor);

        btnVerDatos = new JButton("Ver datos");
        btnVerDatos.setBounds(71, 64, 163, 23);
        btnVerDatos.addActionListener(this);
        contentPane.add(btnVerDatos);

        btnCambiarTituloLibro = new JButton("Cambiar título del libro");
        btnCambiarTituloLibro.setBounds(71, 98, 163, 23);
        btnCambiarTituloLibro.addActionListener(this);
        contentPane.add(btnCambiarTituloLibro);

        btnBorrarAutor = new JButton("Borrar autor");
        btnBorrarAutor.setBounds(71, 132, 163, 23);
        btnBorrarAutor.addActionListener(this);
        contentPane.add(btnBorrarAutor);

        btnCerrarValidacion = new JButton("Cerrar validación");
        btnCerrarValidacion.setBounds(150, 227, 145, 23);
        btnCerrarValidacion.addActionListener(this);
        contentPane.add(btnCerrarValidacion);

        // Botón para ver la lista de libros
        btnVerLibros = new JButton("Ver libros");
        btnVerLibros.setBounds(71, 164, 163, 23); // Nueva posición del botón
        btnVerLibros.addActionListener(this);
        contentPane.add(btnVerLibros);

        textoNombreAutor = new JTextPane();
        textoNombreAutor.setEditable(false);
        textoNombreAutor.setBounds(167, 24, 132, 20);
        textoNombreAutor.setText(nombreAutor);
        contentPane.add(textoNombreAutor);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerDatos) {
            app.mostrarVentanaVerDatos(nombreAutor);
        } else if (e.getSource() == btnCambiarTituloLibro) {
            app.mostrarVentanaCambiarTitulo(nombreAutor);
        } else if (e.getSource() == btnBorrarAutor) {
            app.mostrarVentanaBorrarAutor(nombreAutor);
            this.dispose();
        } else if (e.getSource() == btnCerrarValidacion) {
            app.cerrarSesion();
            dispose();
            app.mostrarVentanaInicioSesion();
        } else if (e.getSource() == btnVerLibros) { // Acción para el nuevo botón
            mostrarListaLibros();
        }
    }

    private void mostrarListaLibros() {
        // Forzar la recarga de los datos del archivo JSON
        app.actualizarDatosAutores();  // Este método debería leer el archivo JSON y cargar los datos en memoria

        // Crear una nueva ventana para mostrar la lista de libros
        JFrame ventanaLibros = new JFrame("Libros de " + nombreAutor);
        ventanaLibros.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaLibros.setSize(300, 400);
        ventanaLibros.setLocationRelativeTo(null);

        // Crear un panel para la lista
        JPanel panel = new JPanel();
        panel.setLayout(null);
        ventanaLibros.setContentPane(panel);

        // Crear un área de texto para mostrar los libros
        JTextPane textoLibros = new JTextPane();
        textoLibros.setEditable(false);
        textoLibros.setBounds(10, 10, 260, 300); // Ajustar el tamaño del área de texto

        // Obtener los libros del autor como una lista de Strings
        List<String> libros = app.obtenerLibrosPorAutor(nombreAutor);
        StringBuilder listaDeLibros = new StringBuilder();

        if (libros != null && !libros.isEmpty()) {
            for (String libroTitulo : libros) {
                listaDeLibros.append(libroTitulo).append("\n"); // Agregar cada título a la lista
            }
        } else {
            listaDeLibros.append("No hay libros registrados para este autor.");
        }

        // Mostrar la lista de libros en el JTextPane
        textoLibros.setText(listaDeLibros.toString());
        panel.add(textoLibros);

        // Hacer visible la ventana
        ventanaLibros.setVisible(true);
    }
}