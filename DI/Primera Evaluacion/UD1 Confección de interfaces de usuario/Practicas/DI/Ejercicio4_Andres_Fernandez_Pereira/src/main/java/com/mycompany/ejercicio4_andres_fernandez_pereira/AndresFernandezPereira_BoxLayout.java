/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ejercicio4_andres_fernandez_pereira;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
public class AndresFernandezPereira_BoxLayout extends javax.swing.JFrame {

    /**
     * Creates new form AndresFernandezPereira_BoxLayout
     */
    public AndresFernandezPereira_BoxLayout() {
         // Configurar la ventana principal
        setTitle("Formulario con BoxLayout");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Ajustar márgenes
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profesión
        JPanel professionPanel = new JPanel();
        professionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        professionPanel.add(new JLabel("Profesión"));
        JTextField professionField = new JTextField(10); // Ajustar el tamaño
        professionPanel.add(professionField);
        mainPanel.add(professionPanel);
        
        // Nº Hermanos
        JPanel siblingsPanel = new JPanel();
        siblingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        siblingsPanel.add(new JLabel("Nº Hermanos"));
        JSpinner siblingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        siblingsPanel.add(siblingSpinner);
        mainPanel.add(siblingsPanel);

        // Edad
        JPanel agePanel = new JPanel();
        agePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        agePanel.add(new JLabel("Edad"));
        String[] ageOptions = {"Entre 18 y 30", "Entre 30 y 60", "Entre 60 y 90"};
        JComboBox<String> ageComboBox = new JComboBox<>(ageOptions);
        agePanel.add(ageComboBox);
        mainPanel.add(agePanel);

        

        // Sexo
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        genderPanel.add(new JLabel("Sexo"));
        JRadioButton maleButton = new JRadioButton("Hombre");
        JRadioButton femaleButton = new JRadioButton("Mujer");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        mainPanel.add(genderPanel);

        // ¿Practica algún deporte?
        JPanel sportCheckPanel = new JPanel();
        sportCheckPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox sportCheckBox = new JCheckBox("¿Practica algún deporte?");
        sportCheckPanel.add(sportCheckBox);
        mainPanel.add(sportCheckPanel);

        // ¿Cuál?
        JPanel sportLabelPanel = new JPanel();
        sportLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sportLabelPanel.add(new JLabel("¿Cuál?"));
        mainPanel.add(sportLabelPanel);

        // Lista de deportes
        JPanel sportListPanel = new JPanel();
        sportListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] sports = {"Fútbol", "Tennis", "Tennis de Mesa", "Baloncesto"};
        JList<String> sportList = new JList<>(sports);
        sportList.setVisibleRowCount(3);
        JScrollPane sportScrollPane = new JScrollPane(sportList);
        sportScrollPane.setPreferredSize(new Dimension(150, 60)); // Ajustar tamaño de la lista
        sportListPanel.add(sportScrollPane);
        mainPanel.add(sportListPanel);

        // Borde debajo de deportes
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        borderPanel.setPreferredSize(new Dimension(500, 1));
        mainPanel.add(borderPanel);

        // Etiqueta para la pregunta de afición
        JPanel hobbyLabelPanel = new JPanel();
        hobbyLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hobbyLabelPanel.add(new JLabel("Marque de 1 a 10 su grado de afición a:"));
        mainPanel.add(hobbyLabelPanel);

        // Compras
        JPanel shoppingPanel = new JPanel();
        shoppingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        shoppingPanel.add(new JLabel("Compras"));
        JPanel shoppingSliderPanel = new JPanel();
        shoppingSliderPanel.setLayout(new BoxLayout(shoppingSliderPanel, BoxLayout.Y_AXIS));
        JLabel shoppingNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider shoppingSlider = new JSlider(1, 10);
        shoppingSlider.setMajorTickSpacing(1);
        shoppingSlider.setPaintTicks(true);
        shoppingSlider.setPaintLabels(false);
        shoppingSliderPanel.add(shoppingNumbers);
        shoppingSliderPanel.add(shoppingSlider);
        shoppingPanel.add(shoppingSliderPanel);
        mainPanel.add(shoppingPanel);

        // Ver la televisión
        JPanel tvPanel = new JPanel();
        tvPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tvPanel.add(new JLabel("Ver la televisión:"));
        JPanel tvSliderPanel = new JPanel();
        tvSliderPanel.setLayout(new BoxLayout(tvSliderPanel, BoxLayout.Y_AXIS));
        JLabel tvNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider tvSlider = new JSlider(1, 10);
        tvSlider.setMajorTickSpacing(1);
        tvSlider.setPaintTicks(true);
        tvSlider.setPaintLabels(false);
        tvSliderPanel.add(tvNumbers);
        tvSliderPanel.add(tvSlider);
        tvPanel.add(tvSliderPanel);
        mainPanel.add(tvPanel);

        // Ir al cine
        JPanel cinemaPanel = new JPanel();
        cinemaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        cinemaPanel.add(new JLabel("Ir al cine"));
        JPanel cinemaSliderPanel = new JPanel();
        cinemaSliderPanel.setLayout(new BoxLayout(cinemaSliderPanel, BoxLayout.Y_AXIS));
        JLabel cinemaNumbers = new JLabel("1 2 3 4 5 6 7 8 9 10");
        JSlider cinemaSlider = new JSlider(1, 10);
        cinemaSlider.setMajorTickSpacing(1);
        cinemaSlider.setPaintTicks(true);
        cinemaSlider.setPaintLabels(false);
        cinemaSliderPanel.add(cinemaNumbers);
        cinemaSliderPanel.add(cinemaSlider);
        cinemaPanel.add(cinemaSliderPanel);
        mainPanel.add(cinemaPanel);

        // Botones Aceptar y Cancelar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

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

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
