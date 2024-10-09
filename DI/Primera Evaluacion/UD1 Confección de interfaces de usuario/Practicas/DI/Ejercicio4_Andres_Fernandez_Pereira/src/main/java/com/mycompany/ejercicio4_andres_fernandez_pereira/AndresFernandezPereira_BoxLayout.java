/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ejercicio4_andres_fernandez_pereira;

import java.awt.Color;
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
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear un panel principal con BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Etiqueta y campo de texto para la Profesión
        JPanel professionPanel = new JPanel();
        professionPanel.setLayout(new BoxLayout(professionPanel, BoxLayout.X_AXIS));
        JLabel professionLabel = new JLabel("Profesión:");
        JTextField professionField = new JTextField(15);
        professionPanel.add(professionLabel);
        professionPanel.add(Box.createHorizontalStrut(10));
        professionPanel.add(professionField);
        mainPanel.add(professionPanel);
        
        // ComboBox para Edad
        JPanel agePanel = new JPanel();
        agePanel.setLayout(new BoxLayout(agePanel, BoxLayout.X_AXIS));
        JLabel ageLabel = new JLabel("Edad:");
        String[] ageOptions = {"Entre 18 y 30", "Entre 30 y 60", "Entre 60 y 90"};
        JComboBox<String> ageComboBox = new JComboBox<>(ageOptions);
        agePanel.add(ageLabel);
        agePanel.add(Box.createHorizontalStrut(10));
        agePanel.add(ageComboBox);
        mainPanel.add(agePanel);
        
        // Spinner para Nº Hermanos
        JPanel siblingPanel = new JPanel();
        siblingPanel.setLayout(new BoxLayout(siblingPanel, BoxLayout.X_AXIS));
        JLabel siblingLabel = new JLabel("Nº Hermanos:");
        JSpinner siblingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        siblingPanel.add(siblingLabel);
        siblingPanel.add(Box.createHorizontalStrut(10));
        siblingPanel.add(siblingSpinner);
        mainPanel.add(siblingPanel);
        
        // Panel con borde negro para el sexo
        JPanel genderPanel = new JPanel();
        genderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
        JLabel genderLabel = new JLabel("Sexo:");
        JRadioButton maleButton = new JRadioButton("Hombre");
        JRadioButton femaleButton = new JRadioButton("Mujer");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(genderLabel);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        mainPanel.add(genderPanel);

        // Casilla y lista para deportes
        JPanel sportPanel = new JPanel();
        sportPanel.setLayout(new BoxLayout(sportPanel, BoxLayout.X_AXIS));
        JCheckBox sportCheckBox = new JCheckBox("¿Practica algún deporte?");
        String[] sports = {"Fútbol", "Tennis", "Tennis de Mesa", "Baloncesto"};
        JList<String> sportList = new JList<>(sports);
        sportPanel.add(sportCheckBox);
        sportPanel.add(Box.createHorizontalStrut(10));
        sportPanel.add(new JScrollPane(sportList));
        mainPanel.add(sportPanel);
        
        // Panel con deslizador para aficiones
        JPanel hobbyPanel = new JPanel();
        hobbyPanel.setLayout(new BoxLayout(hobbyPanel, BoxLayout.Y_AXIS));
        JLabel hobbyLabel = new JLabel("Marque de 1 a 10 su grado de afición a:");
        JLabel shoppingLabel = new JLabel("Compras:");
        JSlider shoppingSlider = new JSlider(1, 10);
        JLabel tvLabel = new JLabel("Ver la televisión:");
        JSlider tvSlider = new JSlider(1, 10);
        JLabel cinemaLabel = new JLabel("Ir al cine:");
        JSlider cinemaSlider = new JSlider(1, 10);
        
        hobbyPanel.add(hobbyLabel);
        hobbyPanel.add(shoppingLabel);
        hobbyPanel.add(shoppingSlider);
        hobbyPanel.add(tvLabel);
        hobbyPanel.add(tvSlider);
        hobbyPanel.add(cinemaLabel);
        hobbyPanel.add(cinemaSlider);
        mainPanel.add(hobbyPanel);
        
        // Botones Aceptar y Cancelar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
        buttonPanel.add(acceptButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AndresFernandezPereira_BoxLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AndresFernandezPereira_BoxLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AndresFernandezPereira_BoxLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AndresFernandezPereira_BoxLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AndresFernandezPereira_BoxLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
