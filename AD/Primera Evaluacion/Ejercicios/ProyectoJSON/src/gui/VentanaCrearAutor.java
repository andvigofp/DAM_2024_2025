package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;
import org.json.JSONArray;

public class VentanaCrearAutor extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaCrearAutor;
    private JLabel etiquetaAutor;
    private JLabel etiquetaTituloLibro;
    private JLabel etiquetaPaginas;
    private JLabel etiquetaEditorial;
    private JTextField textoNombreAutor;
    private JTextField textoTituloLibro;
    private JTextField textoPaginas;
    private JTextField textoEditorial;
    private JButton btnCrear;
    private JButton btnCancelar;
    private AplicacionAutores app;

    public VentanaCrearAutor(AplicacionAutores app) {
        this.app = app;
        setTitle("Aplicación autores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 322, 385);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaAutor = new JLabel("Nombre autor:");
        etiquetaAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaAutor.setBounds(65, 56, 120, 14);
        contentPane.add(etiquetaAutor);

        etiquetaCrearAutor = new JLabel("CREAR AUTOR");
        etiquetaCrearAutor.setBounds(83, 11, 154, 20);
        etiquetaCrearAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(etiquetaCrearAutor);

        textoNombreAutor = new JTextField();
        textoNombreAutor.setBounds(65, 81, 214, 20);
        contentPane.add(textoNombreAutor);
        textoNombreAutor.setColumns(10);

        etiquetaTituloLibro = new JLabel("Título libro:");
        etiquetaTituloLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaTituloLibro.setBounds(65, 112, 68, 14);
        contentPane.add(etiquetaTituloLibro);

        textoTituloLibro = new JTextField();
        textoTituloLibro.setColumns(10);
        textoTituloLibro.setBounds(65, 137, 214, 20);
        contentPane.add(textoTituloLibro);

        etiquetaPaginas = new JLabel("Número de páginas:");
        etiquetaPaginas.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaPaginas.setBounds(65, 168, 120, 14);
        contentPane.add(etiquetaPaginas);

        textoPaginas = new JTextField();
        textoPaginas.setColumns(10);
        textoPaginas.setBounds(65, 193, 214, 20);
        contentPane.add(textoPaginas);

        etiquetaEditorial = new JLabel("Editorial:");
        etiquetaEditorial.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaEditorial.setBounds(65, 224, 214, 14);
        contentPane.add(etiquetaEditorial);

        textoEditorial = new JTextField();
        textoEditorial.setColumns(10);
        textoEditorial.setBounds(65, 249, 214, 20);
        contentPane.add(textoEditorial);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(172, 299, 89, 23);
        btnCrear.addActionListener(this);
        contentPane.add(btnCrear);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(39, 299, 89, 23);
        btnCancelar.addActionListener(this);
        contentPane.add(btnCancelar);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrear) {
            String nombreAutor = textoNombreAutor.getText().trim();
            String tituloLibro = textoTituloLibro.getText().trim();
            String paginas = textoPaginas.getText().trim();
            String editorial = textoEditorial.getText().trim();

            // Verificar que todos los campos estén completos
            if (nombreAutor.isEmpty() || tituloLibro.isEmpty() || paginas.isEmpty() || editorial.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si los campos contienen solo letras
            if (!nombreAutor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                JOptionPane.showMessageDialog(this, "El nombre del autor solo debe contener letras .", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica que el nombreAutor no contenga tildes
            if  (!nombreAutor.matches("[a-zA-Z\\s]+")){
                JOptionPane.showMessageDialog(this, "El nombre del autor no puede contener tilde.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el campo de páginas contenga solo números
            if (!paginas.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El número de páginas debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Convertir el texto de páginas a entero para validar que no sea 0
                int paginasTexto = Integer.parseInt(paginas);

                if (paginasTexto <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de páginas debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El número de páginas debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de autores existente
            JSONArray autores = app.getAutores();

            // Verificar si el autor ya existe con el mismo nombre y título de libro
            if (app.autorYaExiste(nombreAutor, tituloLibro, autores)) {
                JOptionPane.showMessageDialog(this, "El autor con este libro ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // No cerrar la ventana, evitar continuar
            }
            // Lógica para guardar el nuevo autor y su libro
            app.crearAutor(nombreAutor, tituloLibro, paginas, editorial);
            JOptionPane.showMessageDialog(this, "Autor creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cierra la ventana de crear autor

            // Asegúrate de que VentanaInicioSesion se muestre
           app.mostrarVentanaInicioSesion();

        } else if (e.getSource() == btnCancelar) {
            // Cierra la ventana de crear autor y muestra la ventana de validación
            dispose();
            app.mostrarVentanaInicioSesion();

        }
    }

    // Método para limpiar los datos que deja por defecto
    public void limpiarCampos() {
        textoNombreAutor.setText("");
        textoTituloLibro.setText("");
        textoEditorial.setText("");
        textoPaginas.setText("");
    }
}