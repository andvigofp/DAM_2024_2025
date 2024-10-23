package Ej305;

import javax.swing.*;
import java.awt.*;

public class Vista extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private Controlador controlador;


    public Vista() {
    }
    
    public void arranca(){
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
        jButton1.addActionListener(controlador);
        jButton1.setActionCommand("SUMAR");
    }

    public String[] getCampos() {
        return new String[]{jTextField1.getText(), jTextField2.getText()};
    }

    public void escribirResultadoSuma(String resultado) {
        this.jTextPane1.setText(resultado);
    }

    @SuppressWarnings("uncheked")
    private void initComponents() {
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextPane1 = new JTextPane();
        jButton1 = new JButton("Sumar");

        jTextPane1.setEnabled(false); // El JTextPane es solo de lectura
        jTextPane1.setText(""); // Inicialmente vacío
        jTextPane1.setForeground(Color.BLACK); // Establecer texto en blanco

        // Configuración de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Usar BoxLayout

        // Ajustar el tamaño de la ventana
        setSize(200, 200); // Ajustar a un tamaño específico (ancho, alto)

        // Agregar componentes a la ventana
        add(jTextField1);
        add(jTextField2);
        add(jButton1);
        add(jTextPane1);

        //pack(); // Ajustar el tamaño de la ventana a los componentes
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jButton1.setActionCommand("SUMAR");
    }
    


}
