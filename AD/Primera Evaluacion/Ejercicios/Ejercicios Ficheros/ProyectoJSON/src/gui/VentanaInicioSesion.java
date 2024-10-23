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

    private JButton btnRestablecer; // Botón para restablecer los campos
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

        // Agregar el botón de restablecer
        btnRestablecer = new JButton("Restablecer");
        btnRestablecer.setBounds(176 + 118 + 10, 303, 118, 23); // Coloca el botón a la derecha de los demás
        btnRestablecer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos(); // Limpiar los campos al presionar el botón
            }
        });
        contentPane.add(btnRestablecer);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnValidar) {
            String nombreAutor = textoAutor.getText().trim();
            String tituloLibro = textoTitulo.getText().trim();

            // Verifica que ambos campos estén completos
            if (nombreAutor.isEmpty() || tituloLibro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica que el nombreAutor contenga solo letras
            if (!nombreAutor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                JOptionPane.showMessageDialog(this, "El nombre del autor solo debe contener letras", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica que el nombreAutor no contenga tildes
            if  (!nombreAutor.matches("[a-zA-Z\\s]+")){
                JOptionPane.showMessageDialog(this, "El nombre del autor no puede contener tilde.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lógica de validación delegada a la clase AplicacionAutores
            boolean exito = app.iniciarValidacion(nombreAutor, tituloLibro);

            // Si la validación fue exitosa, mostrar el menú del autor
            // Validar los datos del autor
            if (exito) {
                // Cerrar la ventana de inicio de sesión
                dispose();

                // Abrir la ventana del menú del autor
                app.mostrarMenuAutor(nombreAutor);
            }
        } else if (e.getSource() == btnCrearNuevoAutorLibro) {
            app.mostrarVentanaCrearAutor();
            dispose();
        }
    }

    // Método para limpiar los datos que deja por defecto
    public void limpiarCampos() {
        textoAutor.setText("");
        textoTitulo.setText("");
    }
    }