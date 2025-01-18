/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio6;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicMenuUI;

/**
 *
 * @author a13andresfp
 */
public class EjemploSlider extends JFrame{
    public static void main(String[] args) {
        EjemploSlider ejemploSlider = new EjemploSlider();
    }
    
 public EjemploSlider() {
        setTitle("Ejemplo de JSlider");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 450);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,  0, 450, 450);
        
        
        JSlider slider = new JSlider();
        slider.setBounds(150, 150, 200, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
       
        
        panel.add(slider);
        
        add(panel);
        setVisible(true);
    }
    
    
}