/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 *
 * @author a13andresfp
 */
public class Ejercicio1 extends javax.swing.JFrame{
    private final ArrayList<String> registro; // Simulación de base de datos
    
    public Ejercicio1() {
        registro = new ArrayList<>(); // Inicilaizar lista de registros
        
        setTitle("Compoñentes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 500);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 650, 500);
        
        
        JLabel textNombre = new JLabel("Ingrese su nombre");
        textNombre.setBounds(20, 20, 120, 25);
        panel.add(textNombre);
        
        JTextField nombre = new JTextField();
        nombre.setText("");
        nombre.setBounds(150, 20, 125, 25);
        panel.add(nombre);
        
        JLabel textCorreo = new JLabel("Ingrese su correo");
        textCorreo.setBounds(20, 70, 120, 25);
        panel.add(textCorreo);
        
        JTextField correo = new JTextField();
        correo.setText("");
        correo.setBounds(150, 70, 125, 25);
        panel.add(correo);
        
       ButtonGroup genero = new ButtonGroup();

        // Ajustamos "Masculino" para que esté alineado a la izquierda y separado uniformemente
        JRadioButton masculino = new JRadioButton("Masculino");
        masculino.setBounds(100, 130, 125, 25); // Más cerca del borde izquierdo
        genero.add(masculino);
        panel.add(masculino);

        // Ajustamos "Femenino" para que esté alineado a la derecha del anterior con margen uniforme
        JRadioButton femenino = new JRadioButton("Femenino");
        femenino.setBounds(250, 130, 125, 25); // Ajustado para alineación horizontal coherente
        genero.add(femenino);
        panel.add(femenino);

        // Ajustamos el JCheckBox para que esté centrado y bien espaciado verticalmente
        JCheckBox check = new JCheckBox("Acepta los términos y condiciones");
        check.setBounds(120, 200, 300, 25); // Más ancho para centrar mejor el texto
        panel.add(check);

        // Botón "Registrar", centrado y con margen inferior amplio
        JButton registrar = new JButton("Registrar");
        registrar.setBounds(100, 290, 125, 30); // Centrado respecto al panel
        panel.add(registrar);
        
         // Crear botón "Limpiar Campos"
        JButton limpButton = new JButton("Limpiar");
        limpButton.setBounds(250, 290, 125, 30); // Ubicación del botón
        panel.add(limpButton);
        
        limpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               nombre.setText("");
               correo.setText("");
               genero.clearSelection();
               check.setSelected(false);
            }
        });
        
        
        
           // Acción del botón
           registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar los datos
                String nombreText = nombre.getText().trim();
                String correoText = correo.getText().trim();
                boolean generoSeleccionado = masculino.isSelected() || femenino.isSelected();
                boolean aceptaTerminos = check.isSelected();
                
                if (nombreText.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "El campo 'Nombre' no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (correoText.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "El campo 'Correo' no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!generoSeleccionado) {
                    JOptionPane.showMessageDialog(panel, "Debe seleccionar un género.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!aceptaTerminos) {
                    JOptionPane.showMessageDialog(panel, "Debe aceptar los términos y condiciones.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulación de registro
                    String generoText = masculino.isSelected() ? "Masculino" : "Femenino";
                    String registro1 = "Nombre: " + nombreText + ", Correo: " + correoText + ", Género: " + generoText;
                    registro.add(registro1);
                    
                    JOptionPane.showMessageDialog(panel, "Registro exitoso. ¡Bienvenido!\n" + registro1, "Registro Completo", JOptionPane.INFORMATION_MESSAGE);
                    // Limpiar campos
                    nombre.setText("");
                    correo.setText("");
                    genero.clearSelection();
                    check.setSelected(false);
                }
            }
        });
           
      

        add(panel);
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Ejercicio1();
    }
    
}
