package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaInicioSesion extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textoAutor;
    private JTextField textoTitulo;
    private JButton btnValidar;
    private JButton btnCrearNuevoAutorLibro;
    private AplicacionAutores app;

    public VentanaInicioSesion(AplicacionAutores app) {
        this.app = app;

        setTitle("Aplicación autores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 507, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel etiquetaInicioSesion = new JLabel("Validación");
        etiquetaInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
        etiquetaInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaInicioSesion.setBounds(97, 26, 270, 44);
        contentPane.add(etiquetaInicioSesion);

        JLabel etiquetaAutor = new JLabel("Nombre autor:");
        etiquetaAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaAutor.setBounds(160, 90, 80, 14);
        contentPane.add(etiquetaAutor);

        textoAutor = new JTextField();
        textoAutor.setBounds(160, 115, 149, 20);
        contentPane.add(textoAutor);
        textoAutor.setColumns(10);

        JLabel etiquetaContraseña = new JLabel("Título del libro:");
        etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaContraseña.setBounds(160, 146, 149, 14);
        contentPane.add(etiquetaContraseña);

        textoTitulo = new JTextField();
        textoTitulo.setColumns(10);
        textoTitulo.setBounds(160, 171, 149, 20);
        contentPane.add(textoTitulo);

        btnValidar = new JButton("Validar");
        btnValidar.setBounds(176, 215, 118, 23);
        btnValidar.addActionListener(this);
        contentPane.add(btnValidar);

        btnCrearNuevoAutorLibro = new JButton("Crear nuevo autor");
        btnCrearNuevoAutorLibro.setBounds(10, 303, 149, 23);
        btnCrearNuevoAutorLibro.addActionListener(this);
        contentPane.add(btnCrearNuevoAutorLibro);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnValidar) {
            String nombreAutor = textoAutor.getText().toLowerCase().trim();
            String tituloLibro = textoTitulo.getText().trim();

            // Verifica que ambos campos estén completos
            if (nombreAutor.isEmpty() || tituloLibro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica que el nombreAutor contenga solo letras
            if (!nombreAutor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                JOptionPane.showMessageDialog(this, "El nombre del autor solo debe contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Lógica de validación delegada a la clase AplicacionAutores
            boolean exito = app.iniciarValidacion(nombreAutor, tituloLibro);

            // Si la validación fue exitosa, mostrar el menú del autor
            if (exito) {
                app.mostrarMenuAutor(nombreAutor); // Aquí se muestra el menú del autor
                this.dispose(); // Cierra la ventana de inicio de sesión
            }

        } else if (e.getSource() == btnCrearNuevoAutorLibro) {
            app.mostrarVentanaCrearAutor();
        }
    }
}