/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorialandres;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
/**
 *
 * @author a13andresfp
 */
public class EjemploCardLayaout extends JFrame{
    public EjemploCardLayaout() {
        JFrame f=new JFrame("Ejemplo de carLayout");
        f.setLayout(new CardLayout());
        
        JButton b1 = new JButton("");
        f.add(b1);
        
        f.setVisible(true);
      
        
    }
    
    
}
         