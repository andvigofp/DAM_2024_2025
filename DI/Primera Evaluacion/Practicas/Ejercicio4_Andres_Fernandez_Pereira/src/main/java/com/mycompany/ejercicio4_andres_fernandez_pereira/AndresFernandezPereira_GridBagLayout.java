/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.ejercicio4_andres_fernandez_pereira;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author andri
 */
public class AndresFernandezPereira_GridBagLayout extends JFrame {

    /**
     * Creates new form AndresFernandezPereira_GridBagLayout
     */
    public AndresFernandezPereira_GridBagLayout() {
       // Configurar la ventana principal
        setTitle("Formulario con GridBagLayout");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Ajustar márgenes entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Profesión
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth= 1;
        gbc.gridheight=1;
        mainPanel.add(new JLabel("Profesión"), gbc);

        gbc.gridx = 1;
        JTextField professionField = new JTextField(10); // Ajustar el tamaño
        mainPanel.add(professionField, gbc);

        // Edad
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Edad"), gbc);

        gbc.gridx = 3;
        String[] ageOptions = {"Entre 18 y 30", "Entre 30 y 60", "Entre 60 y 90"};
        JComboBox<String> ageComboBox = new JComboBox<>(ageOptions);
        mainPanel.add(ageComboBox, gbc);

        // Nº Hermanos
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Nº Hermanos"), gbc);

        gbc.gridx = 1;
        JSpinner siblingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0));
        mainPanel.add(siblingSpinner, gbc);

        // Sexo
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        genderPanel.add(new JLabel("Sexo"));
        JRadioButton maleButton = new JRadioButton("Hombre");
        JRadioButton femaleButton = new JRadioButton("Mujer");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        
        maleButton.setMargin(new Insets(0, 20, 0, 10)); 
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        mainPanel.add(genderPanel, gbc);

        // ¿Practica algún deporte?
        gbc.gridx = 0;
        gbc.gridy = 3;
        JCheckBox sportCheckBox = new JCheckBox("¿Practica algún deporte?");
        mainPanel.add(sportCheckBox, gbc);

        // ¿Cuál?
        gbc.gridx = 2;
        gbc.gridy = 3; // Cambiado a la fila 4 para mantener cerca de la lista
       
        mainPanel.add(new JLabel("¿Cuál?"), gbc);

        // Lista de deportes
        gbc.gridx = 3; // Asegúrate de que esté en la misma fila
        String[] sports = {"Fútbol", "Tennis", "Tennis de Mesa", "Baloncesto"};
        JList<String> sportList = new JList<>(sports);
        sportList.setVisibleRowCount(3);
        JScrollPane sportScrollPane = new JScrollPane(sportList);
        sportScrollPane.setPreferredSize(new Dimension(150, 60)); // Ajustar tamaño de la lista
        mainPanel.add(sportScrollPane, gbc);

        // Borde debajo de deportes
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        borderPanel.setPreferredSize(new Dimension(500, 1));
        mainPanel.add(borderPanel, gbc);

        // Etiqueta para la pregunta de afición
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        mainPanel.add(new JLabel("Marque de 1 a 10 su grado de afición a:"), gbc);

        // Compras
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Compras"), gbc);

        gbc.gridx = 1;
        JPanel shoppingSliderPanel = new JPanel();
        shoppingSliderPanel.setLayout(new BoxLayout(shoppingSliderPanel, BoxLayout.Y_AXIS));
        JLabel shoppingNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider shoppingSlider = new JSlider(1, 10);
        shoppingSlider.setMajorTickSpacing(1);
        shoppingSlider.setPaintTicks(true);
        shoppingSlider.setPaintLabels(false);
        shoppingSliderPanel.add(shoppingNumbers);
        shoppingSliderPanel.add(shoppingSlider);
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(shoppingSliderPanel, gbc);

        // Ver la televisión
        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(new JLabel("Ver la televisión:"), gbc);

        gbc.gridx = 1;
        JPanel tvSliderPanel = new JPanel();
        tvSliderPanel.setLayout(new BoxLayout(tvSliderPanel, BoxLayout.Y_AXIS));
        JLabel tvNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider tvSlider = new JSlider(1, 10);
        tvSlider.setMajorTickSpacing(1);
        tvSlider.setPaintTicks(true);
        tvSlider.setPaintLabels(false);
        tvSliderPanel.add(tvNumbers);
        tvSliderPanel.add(tvSlider);
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(tvSliderPanel, gbc);

        // Ir al cine
        gbc.gridx = 0;
        gbc.gridy = 9;
        mainPanel.add(new JLabel("Ir al cine"), gbc);

        gbc.gridx = 1;
        JPanel cinemaSliderPanel = new JPanel();
        cinemaSliderPanel.setLayout(new BoxLayout(cinemaSliderPanel, BoxLayout.Y_AXIS));
        JLabel cinemaNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider cinemaSlider = new JSlider(1, 10);
        cinemaSlider.setMajorTickSpacing(1);
        cinemaSlider.setPaintTicks(true);
        cinemaSlider.setPaintLabels(false);
        cinemaSliderPanel.add(cinemaNumbers);
        cinemaSliderPanel.add(cinemaSlider);
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(cinemaSliderPanel, gbc);

        // Botones Aceptar y Cancelar
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, gbc);

        // Agregar el panel principal a la ventana
        add(mainPanel);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
