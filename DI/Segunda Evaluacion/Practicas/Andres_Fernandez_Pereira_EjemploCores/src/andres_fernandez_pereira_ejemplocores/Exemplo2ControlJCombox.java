/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package andres_fernandez_pereira_ejemplocores;

import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author a13andresfp
 */
public class Exemplo2ControlJCombox extends javax.swing.JFrame {
        String[]concelloPontevedra={"Vigo","Poneteareas","Porriño"};
        String[]concelloCataluna={"Barcelona", "Genora", "Llleida"};
        String[]concelloMadrid={"Alcorcón", "Fuenlabrada", "Leganés"};
    /**
     * Creates new form Exemplo2ControlJCombox
     */
    public Exemplo2ControlJCombox() {
        initComponents();    
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
        JlProvincia = new javax.swing.JLabel();
        jLConcello = new javax.swing.JLabel();
        ComboProvincia = new javax.swing.JComboBox<>();
        ComoConcello = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        JlProvincia.setText("Provincia");

        jLConcello.setText("Concello");

        ComboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pontevedra", "Madrid", "Cataluña" }));
        ComboProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboProvinciaItemStateChanged(evt);
            }
        });

        ComoConcello.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vigo", "Poneteareas", "Porriño", "Barcelona", "Genora", "Lleida", "Alcorcón", "Fuenlabrada", "Leganés" }));
        ComoConcello.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComoConcelloActionPerformed(evt);
            }
        });

        jLabel1.setText("Concello seleccionado:");

        jLabel2.setText("Ninguno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlProvincia)
                    .addComponent(jLConcello))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ComboProvincia, 0, 286, Short.MAX_VALUE)
                        .addComponent(ComoConcello, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlProvincia)
                    .addComponent(ComboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLConcello)
                    .addComponent(ComoConcello, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(176, Short.MAX_VALUE))
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

    private void ComboProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboProvinciaItemStateChanged
        
            // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) { 
            int posicionProvincia = ComboProvincia.getSelectedIndex(); 
            ComoConcello.removeAllItems();
            switch (posicionProvincia) { 
                case 0: 
                {
                    
                    ComoConcello.setModel(new DefaultComboBoxModel<>(concelloPontevedra));
                }
                break; 
 
                case 1: 
                    ComoConcello.setModel(new DefaultComboBoxModel<>(concelloMadrid)); 
                    break; 
                case 2: 
                    ComoConcello.setModel(new DefaultComboBoxModel<>(concelloCataluna)); 
                break; 
            } 
        }
    
    
    //j(posicionProvincia);
    
    
    }//GEN-LAST:event_ComboProvinciaItemStateChanged

    private void ComoConcelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComoConcelloActionPerformed
        // TODO add your handling code here:
        String concelloSeleccionado = (String) ComoConcello.getSelectedItem(); 
        jLabel2.setText(concelloSeleccionado);
    }//GEN-LAST:event_ComoConcelloActionPerformed

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
            java.util.logging.Logger.getLogger(Exemplo2ControlJCombox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exemplo2ControlJCombox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exemplo2ControlJCombox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exemplo2ControlJCombox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exemplo2ControlJCombox().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboProvincia;
    private javax.swing.JComboBox<String> ComoConcello;
    private javax.swing.JLabel JlProvincia;
    private javax.swing.JLabel jLConcello;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
