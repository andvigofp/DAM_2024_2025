package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaCambiarTitulo extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaNuevoTitulo;
    private JTextField textoNuevoTitulo;
    private JButton btnCambiarTitulo;
    private JButton btnCancelar;
    private AplicacionAutores app;
    private String nombreAutor;

    public VentanaCambiarTitulo(AplicacionAutores app, String nombreAutor) {
        this.app = app;
        this.nombreAutor = nombreAutor;
        setTitle("Aplicación autores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 265, 188);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaNuevoTitulo = new JLabel("Escribe el nuevo título del libro:");
        etiquetaNuevoTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
        etiquetaNuevoTitulo.setBounds(21, 22, 207, 14);
        contentPane.add(etiquetaNuevoTitulo);

        textoNuevoTitulo = new JTextField();
        textoNuevoTitulo.setBounds(21, 58, 194, 20);
        contentPane.add(textoNuevoTitulo);
        textoNuevoTitulo.setColumns(10);

        btnCambiarTitulo = new JButton("Cambiar");
        btnCambiarTitulo.setBounds(134, 111, 89, 23);
        btnCambiarTitulo.addActionListener(this);
        contentPane.add(btnCambiarTitulo);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(21, 111, 89, 23);
        btnCancelar.addActionListener(this);
        contentPane.add(btnCancelar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiarTitulo) {
            // Obtener el nuevo título del campo de texto
            String nuevoTitulo = textoNuevoTitulo.getText().trim();
            if (!nuevoTitulo.isEmpty()) {
                // Lógica para cambiar el título en la aplicación
                boolean exito = app.cambiarTituloLibro(nombreAutor, nuevoTitulo); // Cambia el título en la aplicación

                if (exito) {
                    JOptionPane.showMessageDialog(this, "El título se ha cambiado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cerrar solo la ventana de cambiar título
                    // Aquí puedes volver al menú del autor si es necesario
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cambiar el título.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mostrar un mensaje de error si el nuevo título está vacío
                JOptionPane.showMessageDialog(this, "El nuevo título no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCancelar) {
            // Cerrar la ventana y volver al menú del autor
            dispose();
        }
    }
}