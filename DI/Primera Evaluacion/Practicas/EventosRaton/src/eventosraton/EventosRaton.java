/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eventosraton;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author a13andresfp
 */
public class EventosRaton extends JFrame{

  public EventosRaton() {
        setTitle("Eventos de Ratón");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear un panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // Crear una etiqueta para el texto inicial "EVENETOS DE RATÓN"
        JLabel jtext = new JLabel("EVENETOS DE RATÓN");
        jtext.setBounds(150, 20, 200, 20); // Posición original
        panel.add(jtext);

        // Crear una etiqueta para mostrar los mensajes
        JLabel jtext2 = new JLabel(""); // Inicia vacío, se actualizará con los mensajes
        jtext2.setBounds(20, 120, 200, 20); // Posicionar debajo de la primera etiqueta
        panel.add(jtext2);

        // Crear una etiqueta para mostrar el segundo mensaje
        JLabel jtext3 = new JLabel(""); // Inicia vacío, se actualizará con los mensajes
        jtext3.setBounds(20, 140, 200, 20); // Posicionar debajo de la segunda etiqueta
        panel.add(jtext3);

        // Crear un botón
        JButton button = new JButton("PRESIONA");
        button.setBounds(120, 80, 200, 30); // Posicionar el botón
        // Establecer un borde azul alrededor del botón
        button.setBorder(new LineBorder(java.awt.Color.BLUE, 2)); // Borde azul de grosor 2
        panel.add(button);

        // Añadir un MouseListener al botón
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Cuando se presiona el botón, se muestra el texto "Hizo clic en mi botón"
                jtext2.setText("Hizo clic en mi botón");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Cuando se suelta el botón del mouse, se muestra el texto "Liberaste el botón"
                jtext3.setText("Liberaste el botón");
            }
        });

        setVisible(true);
    }


     
    public static void main(String[] args) {
        new EventosRaton();
    }
    
}
