/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author a13andresfp
 */
public class Ejercicio6 extends JFrame implements ActionListener{
    
    private JLabel lsexo;
    private JRadioButton hombreButton, mujerButton;
    private JTextArea textArea;
    
    public Ejercicio6() {
        
        setTitle("COMPONENTES");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Sin layout, utilizamos setBounds para posicionar los elementos
        panel.setBounds(0, 0, 700, 700);

        // Menú superior
        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");
        JMenu textoMenu = new JMenu("TEXTO");
        menuBar.add(opcionesMenu);
        menuBar.add(textoMenu);
        
        

        // Opciones en el menú desplegable
        JMenuItem ejercicio1Item = new JMenuItem("Emjemplo Slider");
        JMenuItem ejercicio2Item = new JMenuItem("Registro Usuario");
        JMenuItem ejercicio3Item = new JMenuItem("Calculadora");
        JMenuItem ejercicio4Item = new JMenuItem("Biblioteca");
        opcionesMenu.add(ejercicio1Item);
        opcionesMenu.add(ejercicio2Item);
        opcionesMenu.add(ejercicio3Item);
        opcionesMenu.add(ejercicio4Item);
       

        // Action Listeners para abrir las ventanas de cada ejercicio
        ejercicio1Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EjemploSlider(); // Asume que tienes una clase Ejercicio1 con su ventana
            }
        });

        // Action Listeners para abrir las ventanas de cada ejercicio
        ejercicio2Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ejercicio1(); // Asume que tienes una clase Ejercicio1 con su ventana
            }
        });

        ejercicio3Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ejercicio2(); // Asume que tienes una clase Ejercicio2 con su ventana
            }
        });

        ejercicio4Item.addActionListener(new ActionListener() {
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
        JSlider slider1 = new JSlider(0, 100, 50);
        slider1.setBounds(50, 60, 317, 40);
        slider1.setBackground(panel.getBackground());
        slider1.setMajorTickSpacing(20);
        slider1.setMinorTickSpacing(5);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setForeground(Color.BLACK);
        panel.add(slider1);
        
       
        
          // Para el segundo slider (slider2)
        JSlider slider2 = new JSlider();
        slider2.setBounds(50, 170, 317, 40);  // Este slider tiene una posición diferente para no superponerse con el primero
        slider2.setBackground(panel.getBackground());
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(false);
        panel.add(slider2);

        // Sincronización de sliders
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slider2.setValue(slider1.getValue());
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                slider1.setValue(slider2.getValue());
            }
        });
        
         
        // Grupo de género con botones
        ButtonGroup generoGroup = new ButtonGroup();
        hombreButton = new JRadioButton("HOMBRE");
        hombreButton.setBounds(450, 150, 100, 30);

        mujerButton = new JRadioButton("MUJER");
        mujerButton.setBounds(450, 180, 100, 30);
        generoGroup.add(hombreButton);
        generoGroup.add(mujerButton);

        mujerButton.setSelected(true);
        panel.add(hombreButton);
        panel.add(mujerButton);
        
        lsexo= new JLabel("Has seleccionado: ");
        lsexo.setBounds(500, 210, 200, 50);
        panel.add(lsexo);
        hombreButton.addActionListener((ActionListener) this);
        mujerButton.addActionListener((ActionListener) this);
     
        JComboBox nombreCombo = new JComboBox();
        nombreCombo.setBounds(200, 270, 100, 30);
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
        textArea = new JTextArea("Área para escribir");
        textArea.setBounds(200,350, 300, 100);
        textArea.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        panel.add(textArea);
        
       // Configuración de botones y acciones
       hombreButton.addActionListener((ActionListener) this);
       mujerButton.addActionListener((ActionListener) this);
        
       
        JButton botonOptionPane = new JButton("Ejecutar OptionPane");
        botonOptionPane.setBounds(250, 520, 180, 30);
        botonOptionPane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        panel.add(botonOptionPane);
        
         // Acción del botón OptionPane
        botonOptionPane.addActionListener(e -> {
            String[] opciones = {"Opción 1", "Opción 2", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Selecciona una opción",
                    "Opciones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (seleccion == 2) {
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Opción seleccionada: " + opciones[seleccion]);
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
        
        cambiarColorTextoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Definir una lista de colores posibles
                Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN};
                
                // Crear un objeto Random para seleccionar un color aleatorio
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt(colores.length);
                
                // Cambiar el fondo de la etiqueta al color seleccionado aleatoriamente
                etiquetaTexto.setBackground(colores[indiceAleatorio]);
            }
        });
      
        // Opción para cambiar el color de fondo de la etiqueta
        cambiarColorTextoItem = new JMenuItem("Cambiar color de fondo");
        textoMenu.add(cambiarColorTextoItem);
        cambiarColorTextoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Definir una lista de colores posibles
                Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN};
                
                // Crear un objeto Random para seleccionar un color aleatorio
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt(colores.length);
                
                // Cambiar el fondo de la etiqueta al color seleccionado aleatoriamente
                etiquetaTexto.setBackground(colores[indiceAleatorio]);
            }
        });

        
        

        // Agregamos el panel a la ventana principal
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hombreButton) {
            lsexo.setText("Has seleccionado: HOMBRE");
            textArea.append("\nOpción seleccionada: HOMBRE");
        } else if (e.getSource() == mujerButton) {
            lsexo.setText("Has seleccionado: MUJER");
            textArea.append("\nOpción seleccionada: MUJER");
        }
    }

    
    public static void main(String[] args) {
        new Ejercicio6();
    }
    

    
}
