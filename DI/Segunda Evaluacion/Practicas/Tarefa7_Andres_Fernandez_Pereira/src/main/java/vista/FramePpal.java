/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorAlfombra;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import objeto.Alfombra;

/**
 *
 * @author PcVIP
 */
public class FramePpal extends javax.swing.JFrame {
     private ControladorAlfombra controlador; // Controlador de alfombras
    private DefaultListModel<String> modeloLista; // Modelo para actualizar la lista de alfombras

    /**
     * Creates new form FramePpal
     */
    public FramePpal() {
        initComponents();
        controlador = new ControladorAlfombra(); // Inicializar controlador
        modeloLista = new DefaultListModel<>();
        
       // Agregar elementos por defecto al ComboBox 
        /**Alfombra alfombra1 = new Alfombra("Río espumoso", "Tonos Blancos", 200, 300); 
        Alfombra alfombra2 = new Alfombra("Tejida Carmen", "Tonos Gris", 150, 250); 
        Alfombra alfombra3 = new Alfombra("Pelo Corto", "MultiColor", 180, 220); 
        Alfombra alfombra4 = new Alfombra("Home Essential", "Marrón", 210, 310); 
        
        controlador.agregarAlfombra(alfombra1); 
        controlador.agregarAlfombra(alfombra2); 
        controlador.agregarAlfombra(alfombra3); 
        controlador.agregarAlfombra(alfombra4); 
        
        modeloCombo.addElement(alfombra1.toString()); 
        modeloCombo.addElement(alfombra2.toString()); 
        modeloCombo.addElement(alfombra3.toString()); 
        modeloCombo.addElement(alfombra4.toString()); **/
        
        listaAlfombra.setModel(modeloLista); // Conectar el modelo con la lista visual
        configurarEventos(); // Configurar los eventos de los botones
    }
    
     private void configurarEventos() {
        btnAgregar.addActionListener((ActionEvent e) -> agregarAlfombra());
        btnEliminar.addActionListener((ActionEvent e) -> eliminarAlfombraSeleccionada());
        btnEliminarTodas.addActionListener((ActionEvent e) -> eliminarTodasLasAlfombras());
        mostrar.addActionListener((ActionEvent e) -> mostrarInfoAlfombraSeleccionada());
       
}
     
     
      private void agregarAlfombra() {
          // Obtener datos de los cambos de texto
        String modelo = jFModelo.getText();
        String color = jTCor.getText();
        double ancho;
        double alto;
        
        try {
            ancho = Double.parseDouble(jTAncho.getText());
            alto = Double.parseDouble(jTAlto.getText());
        }catch(NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Por favor, no dejes todos los datos vacios.", "Error de formato", JOptionPane.ERROR_MESSAGE);
           return;
        }
        
        // Crear y agregar alfombra al controlador 
        Alfombra alfombra = new Alfombra(modelo, color, ancho, alto); 
        controlador.agregarAlfombra(alfombra); 
        JOptionPane.showMessageDialog(this, "Se ha agregado la alfombra a la lista.", "Exito", JOptionPane.INFORMATION_MESSAGE);
       // Actualizar la lista visual 
       modeloLista.addElement(alfombra.toString()); 
       limpiarCampos();
       
    }
      
       private void limpiarCampos() { 
        jFModelo.setText("");
        jTCor.setText("");
        jTAncho.setText("");
        jTAlto.setText("");
    }
      
    private void mostrarInfoAlfombraSeleccionada() {
    int indiceSeleccionado = listaAlfombra.getSelectedIndex();
    if (indiceSeleccionado != -1) {
        Alfombra alfombra = controlador.obtenerAlfombra(indiceSeleccionado);
        String info = "Modelo: " + alfombra.getModelo() + "\n"
                    + "Color: " + alfombra.getColor() + "\n"
                    + "Ancho: " + alfombra.getAncho() + "\n" 
                    + "Alto: " + alfombra.getAlto();
                    
        JOptionPane.showMessageDialog(this, info, "Información de la Alfombra", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una alfombra de la lista.", "Sin selección", JOptionPane.WARNING_MESSAGE);
    }
}

    
   
    private void eliminarAlfombraSeleccionada() {
    int indiceSeleccionado = listaAlfombra.getSelectedIndex();
    if (indiceSeleccionado != -1) {
        controlador.eliminarAlfombra(indiceSeleccionado); // Eliminar del controlador
        modeloLista.removeElementAt(indiceSeleccionado); // Eliminar de la lista visual
        JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente.", "exito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una alfombra de la lista.", "Sin selección", JOptionPane.WARNING_MESSAGE);
    }
}
      

   private void eliminarTodasLasAlfombras() {
    controlador.eliminarTodas(); // Eliminar todas las alfombras del controlador
    modeloLista.removeAllElements();// Limpiar la lista visual
    JOptionPane.showMessageDialog(this, "Elimnado todas las alfombras exitosamente.", "exito", JOptionPane.INFORMATION_MESSAGE);
}


 


   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtModelo = new javax.swing.JLabel();
        jFModelo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        txtColor = new javax.swing.JLabel();
        jTCor = new javax.swing.JTextField();
        txtAncho = new javax.swing.JLabel();
        jTAncho = new javax.swing.JTextField();
        jcm = new javax.swing.JLabel();
        txtAlto = new javax.swing.JLabel();
        jTAlto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        mostrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEliminarTodas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAlfombra = new javax.swing.JList<>();
        jnovoAlfombra = new javax.swing.JLabel();
        jAlfombrasDisponibles = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtModelo.setText("Modelo");

        btnAgregar.setText("Engadir");

        txtColor.setText("Cor");

        txtAncho.setText("Ancho");

        jcm.setText("(cm)");

        txtAlto.setText("Alto");

        jLabel3.setText("(cm)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModelo)
                    .addComponent(txtColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTCor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAncho)
                        .addGap(12, 12, 12)
                        .addComponent(jTAncho, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAlto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jFModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModelo)
                    .addComponent(jFModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColor)
                    .addComponent(jTCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAncho)
                    .addComponent(jTAncho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcm)
                    .addComponent(txtAlto)
                    .addComponent(jTAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        mostrar.setText("Información da alfombra");

        btnEliminar.setText("Elimnar Alfombra");

        btnEliminarTodas.setText("Eliminar todas");

        listaAlfombra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Modelo Río espumoso - COR Tonos Blancos", "Modelo Tejida Carmen - COR Tonos Gris", "Modelo Pelo Corto - COR  MultiColor", "Modelo Home Essential - COR Marrón" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaAlfombra);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mostrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarTodas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mostrar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarTodas)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jnovoAlfombra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jnovoAlfombra.setText("Nova alfombra");

        jAlfombrasDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jAlfombrasDisponibles.setText("Alfombras disponibles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jnovoAlfombra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAlfombrasDisponibles))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jnovoAlfombra)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jAlfombrasDisponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(FramePpal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePpal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePpal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePpal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePpal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTodas;
    private javax.swing.JLabel jAlfombrasDisponibles;
    private javax.swing.JTextField jFModelo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTAlto;
    private javax.swing.JTextField jTAncho;
    private javax.swing.JTextField jTCor;
    private javax.swing.JLabel jcm;
    private javax.swing.JLabel jnovoAlfombra;
    private javax.swing.JList<String> listaAlfombra;
    private javax.swing.JButton mostrar;
    private javax.swing.JLabel txtAlto;
    private javax.swing.JLabel txtAncho;
    private javax.swing.JLabel txtColor;
    private javax.swing.JLabel txtModelo;
    // End of variables declaration//GEN-END:variables

   
}
