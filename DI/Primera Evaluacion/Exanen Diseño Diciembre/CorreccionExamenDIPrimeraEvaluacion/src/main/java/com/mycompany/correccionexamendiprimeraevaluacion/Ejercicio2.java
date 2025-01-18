/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.correccionexamendiprimeraevaluacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author PcVIP
 */
public class Ejercicio2 extends JFrame implements ActionListener{
   private JLabel lsexo;
    private JRadioButton hombreButton, mujerButton;
    private JTextArea textArea;
    private JTextField jnombre, jApellido, jEmail, jFechaEntrada, jSalida, jPernonas, jHbaitaciones;
    private JComboBox<String> combox;

    public Ejercicio2() {
        setTitle("GESTIÓN DE RESERVAS HOTEL");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 700);
        panel.setLayout(null);

        JLabel jtitulo = new JLabel("GESTIÓN DE RESERVAS HOTEL");
        jtitulo.setOpaque(true);
        jtitulo.setBackground(Color.YELLOW);
        jtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jtitulo.setBounds(150, 20, 400, 30);
        panel.add(jtitulo);

        // Campos de entrada
        JLabel jlnombre = new JLabel("Nombre:");
        jlnombre.setBounds(20, 80, 120, 25);
        panel.add(jlnombre);

        jnombre = new JTextField();
        jnombre.setBounds(150, 80, 200, 25);
        panel.add(jnombre);

        JLabel jlapellido = new JLabel("Apellido:");
        jlapellido.setBounds(20, 120, 120, 25);
        panel.add(jlapellido);

        jApellido = new JTextField();
        jApellido.setBounds(150, 120, 200, 25);
        panel.add(jApellido);

        JLabel jlemail = new JLabel("Email:");
        jlemail.setBounds(20, 160, 120, 25);
        panel.add(jlemail);

        jEmail = new JTextField();
        jEmail.setBounds(150, 160, 200, 25);
        panel.add(jEmail);

        JLabel jlFechaEntrada = new JLabel("Fecha de Entrada:");
        jlFechaEntrada.setBounds(20, 200, 120, 25);
        panel.add(jlFechaEntrada);

        jFechaEntrada = new JTextField();
        jFechaEntrada.setBounds(150, 200, 200, 25);
        panel.add(jFechaEntrada);

        JLabel jlFechaSalida = new JLabel("Fecha de Salida:");
        jlFechaSalida.setBounds(20, 240, 120, 25);
        panel.add(jlFechaSalida);

        jSalida = new JTextField();
        jSalida.setBounds(150, 240, 200, 25);
        panel.add(jSalida);

        JLabel jlNPersonas = new JLabel("Nº de Personas:");
        jlNPersonas.setBounds(20, 280, 120, 25);
        panel.add(jlNPersonas);

        jPernonas = new JTextField();
        jPernonas.setBounds(150, 280, 200, 25);
        panel.add(jPernonas);

        JLabel jlNHabitaciones = new JLabel("Nº de Habitaciones:");
        jlNHabitaciones.setBounds(20, 320, 120, 25);
        panel.add(jlNHabitaciones);

        jHbaitaciones = new JTextField();
        jHbaitaciones.setBounds(150, 320, 200, 25);
        panel.add(jHbaitaciones);

        // Selección de ciudad
        JLabel jlCiudad = new JLabel("Ciudad de destino:");
        jlCiudad.setBounds(20, 360, 120, 25);
        panel.add(jlCiudad);

        combox = new JComboBox<>(new String[]{"Vigo", "Madrid", "Barcelona", "Santiago"});
        combox.setBounds(150, 360, 200, 25);
        panel.add(combox);

        // Género
        ButtonGroup generoGroup = new ButtonGroup();
        hombreButton = new JRadioButton("Hombre");
        hombreButton.setBounds(400, 80, 100, 30);
        mujerButton = new JRadioButton("Mujer");
        mujerButton.setBounds(400, 120, 100, 30);
        generoGroup.add(hombreButton);
        generoGroup.add(mujerButton);
        panel.add(hombreButton);
        panel.add(mujerButton);

        lsexo = new JLabel("Género seleccionado:");
        lsexo.setBounds(400, 160, 200, 30);
        panel.add(lsexo);

        // Área de texto
        textArea = new JTextArea();
        textArea.setBounds(150, 400, 400, 100);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(textArea);
        
        // Configuración de botones y acciones
       hombreButton.addActionListener((ActionListener) this);
       mujerButton.addActionListener((ActionListener) this);

        // Botones
        JButton buttonReserva = new JButton("Reserva");
        buttonReserva.setBounds(50, 550, 150, 30);
        panel.add(buttonReserva);

        JButton buttonLimpiar = new JButton("Limpiar");
        buttonLimpiar.setBounds(250, 550, 150, 30);
        panel.add(buttonLimpiar);

        // Acción del botón Reserva
        buttonReserva.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Validar que todos los campos estén llenos
        if (jnombre.getText().isEmpty() || 
            jApellido.getText().isEmpty() || 
            jEmail.getText().isEmpty() || 
            jFechaEntrada.getText().isEmpty() || 
            jSalida.getText().isEmpty() || 
            jPernonas.getText().isEmpty() || 
            jHbaitaciones.getText().isEmpty() || 
            (!hombreButton.isSelected() && !mujerButton.isSelected())) {
            
            // Mostrar mensaje de error si falta algún dato
            JOptionPane.showMessageDialog(
                Ejercicio2.this,
                "Por favor, rellena todos los campos y selecciona un género.",
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            // Mostrar los datos en el JTextArea y JOptionPane si todo está completo
            String datos = String.format(
                "Reserva realizada con éxito:\n" +
                "Nombre: %s\n" +
                "Apellido: %s\n" +
                "Email: %s\n" +
                "Fecha de Entrada: %s\n" +
                "Fecha de Salida: %s\n" +
                "Nº Personas: %s\n" +
                "Nº Habitaciones: %s\n" +
                "Género: %s\n" +
                "Ciudad: %s",
                jnombre.getText(),
                jApellido.getText(),
                jEmail.getText(),
                jFechaEntrada.getText(),
                jSalida.getText(),
                jPernonas.getText(),
                jHbaitaciones.getText(),
                hombreButton.isSelected() ? "HOMBRE" : "MUJER",
                combox.getSelectedItem().toString()
            );

            // Mostrar los datos en el JTextArea
            textArea.setText(datos);

            // Mostrar los datos en un JOptionPane
            JOptionPane.showMessageDialog(
                Ejercicio2.this,
                datos,
                "Confirmación de Reserva",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
});


        // Acción del botón Limpiar
        buttonLimpiar.addActionListener(e -> {
            jnombre.setText("");
            jApellido.setText("");
            jEmail.setText("");
            jFechaEntrada.setText("");
            jSalida.setText("");
            jPernonas.setText("");
            jHbaitaciones.setText("");
            combox.setSelectedIndex(0);
            textArea.setText("");
        });

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (hombreButton.isSelected()) {
            lsexo.setText("Género seleccionado: Hombre");
        } else if (mujerButton.isSelected()) {
            lsexo.setText("Género seleccionado: Mujer");
        }
    }

    public static void main(String[] args) {
        new Ejercicio2();
    }
}