/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio6;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author a13andresfp
 */
public class PanelOpcion extends JFrame{
    
    public PanelOpcion() {
        setTitle("Selector de Ocpiones Si no cancelar con null");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300, 350);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 350);
        
        JButton  botonOptionPane = new JButton();
    }
    
}
