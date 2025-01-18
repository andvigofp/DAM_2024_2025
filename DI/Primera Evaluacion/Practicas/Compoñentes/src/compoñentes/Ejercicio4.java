/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compoñentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author andri
 */
public class Ejercicio4 extends javax.swing.JFrame{
    
    public Ejercicio4() {
        setTitle("COMPONENTES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Sin layout, utilizamos setBounds para posicionar los elementos
        panel.setBounds(0, 0, 700, 600);

        // Menú superior
        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");
        JMenu textoMenu = new JMenu("TEXTO");
        menuBar.add(opcionesMenu);
        menuBar.add(textoMenu);

        // Opciones en el menú desplegable
        JMenuItem ejercicio1Item = new JMenuItem("Registro Usuario");
        JMenuItem ejercicio2Item = new JMenuItem("Calculadora");
        JMenuItem ejercicio3Item = new JMenuItem("Biblioteca");
        opcionesMenu.add(ejercicio1Item);
        opcionesMenu.add(ejercicio2Item);
        opcionesMenu.add(ejercicio3Item);

        // Action Listeners para abrir las ventanas de cada ejercicio
        ejercicio1Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Ejercicio1(); // Asume que tienes una clase Ejercicio1 con su ventana
            }
        });

        ejercicio2Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Ejercicio2(); // Asume que tienes una clase Ejercicio2 con su ventana
            }
        });

        ejercicio3Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Ejercicio3(); // Asume que tienes una clase Ejercicio3 con su ventana
            }
        });

        setJMenuBar(menuBar);

        // Etiqueta amarilla de texto
        JLabel etiquetaTexto = new JLabel("EJERCICIO CON VARIOS COMPONENTES");
        etiquetaTexto.setBounds(200, 20, 300, 20); // Centramos el texto
        etiquetaTexto.setOpaque(true);
        etiquetaTexto.setBackground(new java.awt.Color(255, 255, 0));
        panel.add(etiquetaTexto);

        // Slider azul
        JSlider slider1 = new JSlider();
        slider1.setBounds(50, 60, 317, 40);
        slider1.setBackground(panel.getBackground());
        panel.add(slider1);
        
        // Etiquetas para el slider1
        for (int i = 0; i <= 10; i++) {
        // Número
        JLabel label = new JLabel(Integer.toString(i));
        label.setBounds(50 + (i * 30), 110, 20, 20); // Ajustamos la ubicación para que los números estén debajo de la barra
        label.setFont(label.getFont().deriveFont(12f)); // Reducimos el tamaño de los números
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);

        // Barra sobre cada número (|)
         JLabel barra = new JLabel("|");
         barra.setBounds(50 + (i * 30), 90, 20, 20); // Barra posicionada directamente encima del número
         barra.setHorizontalAlignment(JLabel.CENTER);
         panel.add(barra);

        }
        
          // Para el segundo slider (slider2)
        JSlider slider2 = new JSlider();
        slider2.setBounds(50, 170, 317, 40);  // Este slider tiene una posición diferente para no superponerse con el primero
        slider2.setBackground(panel.getBackground());
        panel.add(slider2);

        // Etiquetas para el slider2 (del 0 al 10)
        for (int i = 0; i <= 10; i++) {
        // Número
            JLabel label = new JLabel(Integer.toString(i));
            label.setBounds(50 + (i * 30), 220, 20, 20); // Ajustamos la ubicación para que los números estén debajo de la barra
            label.setFont(label.getFont().deriveFont(12f)); // Reducimos el tamaño de los números
            label.setHorizontalAlignment(JLabel.CENTER);
            panel.add(label);

           // Barra sobre cada número (|)
            JLabel barra = new JLabel("|");
            barra.setBounds(50 + (i * 30), 205, 20, 20); // Barra posicionada directamente encima del número
            barra.setHorizontalAlignment(JLabel.CENTER);
            panel.add(barra);
        }

        // Cuadro de texto al lado del slider
        JTextField textField = new JTextField();
        textField.setBounds(380, 60, 50, 30);
        panel.add(textField);

        // Grupo de género con botones
        ButtonGroup generoGroup = new ButtonGroup();
        JRadioButton hombreButton = new JRadioButton("HOMBRE");
        hombreButton.setBounds(450, 40, 100, 30);

        JRadioButton mujerButton = new JRadioButton("MUJER");
        mujerButton.setBounds(450, 70, 100, 30);
        generoGroup.add(hombreButton);
        generoGroup.add(mujerButton);

        mujerButton.setSelected(true);
        panel.add(hombreButton);
        panel.add(mujerButton);

      
        JComboBox nombreCombo = new JComboBox();
        nombreCombo.setBounds(200, 290, 100, 30);
        nombreCombo.addItem("Juan");
        nombreCombo.insertItemAt("Antonio",1);
        nombreCombo.insertItemAt("Miguel", 2);
        nombreCombo.insertItemAt("Adrian", 3);
        nombreCombo.setSelectedItem("Antonio");
        
        for(int i=0; i<nombreCombo.getItemCount(); i++) {
            System.out.println(nombreCombo.getItemAt(i));
        }
        
        panel.add(nombreCombo);

        // Cuadro de texto
        JTextArea textArea = new JTextArea("Área para escribir");
        textArea.setBounds(200,350, 300, 100);
        textArea.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        panel.add(textArea);
        
        // ActionListener para el JTextField
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar texto del JTextField al JTextArea cuando se presiona "Enter"
                textArea.append("\nTexto ingresado : " + textField.getText());
                textField.setText(""); // Limpiar el JTextField después de agregar
            }
        });
        
        // ActionListener para el JComboBox
        nombreCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar la opción seleccionada del JComboBox al JTextArea
                textArea.append("\nOpción seleccionada: " + nombreCombo.getSelectedItem().toString());
            }
        });
        
        // Menú TEXTO: Cambiar color, tamaño, estilo, etc.
        // Opción para cambiar el color del texto
        JMenuItem cambiarColorTextoItem = new JMenuItem("Cambiar color del texto");
        textoMenu.add(cambiarColorTextoItem);
        cambiarColorTextoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setForeground(java.awt.Color.RED); // Cambia el color del texto del JTextArea
            }
        });

        // Opción para cambiar el tamaño del texto
        JMenuItem cambiarTamanoTextoItem = new JMenuItem("Cambiar tamaño del texto");
        textoMenu.add(cambiarTamanoTextoItem);
        cambiarTamanoTextoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20)); // Cambia el tamaño del texto
            }
        });

        // Opción para poner el texto en negrita
        JMenuItem ponerNegritaItem = new JMenuItem("Negrita");
        textoMenu.add(ponerNegritaItem);
        ponerNegritaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setFont(textArea.getFont().deriveFont(java.awt.Font.BOLD)); // Aplica negrita
            }
        });

        // Opción para poner el texto en cursiva
        JMenuItem ponerCursivaItem = new JMenuItem("Cursiva");
        textoMenu.add(ponerCursivaItem);
        ponerCursivaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setFont(textArea.getFont().deriveFont(java.awt.Font.ITALIC)); // Aplica cursiva
            }
        });

        // Opción para alinear el texto a la izquierda
        JMenuItem alinearIzquierdaItem = new JMenuItem("Alinear a la izquierda");
        textoMenu.add(alinearIzquierdaItem);
        alinearIzquierdaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setAlignmentX(JTextArea.LEFT_ALIGNMENT); // Alinea a la izquierda
            }
        });

        // Opción para alinear el texto al centro
        JMenuItem alinearCentroItem = new JMenuItem("Alinear al centro");
        textoMenu.add(alinearCentroItem);
        alinearCentroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT); // Alinea al centro
            }
        });
        
        

        // Opción para alinear el texto a la derecha
        JMenuItem alinearDerechaItem = new JMenuItem("Alinear a la derecha");
        textoMenu.add(alinearDerechaItem);
        alinearDerechaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setAlignmentX(JTextArea.RIGHT_ALIGNMENT); // Alinea a la derecha
            }
        });

        // Opción para limpiar el texto
        JMenuItem limpiarTextoItem = new JMenuItem("Limpiar texto");
        textoMenu.add(limpiarTextoItem);
        limpiarTextoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(""); // Limpia el JTextArea
            }
        });
        
        
        

        // Agregamos el panel a la ventana principal
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ejercicio4();
    }
}