/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;

/**
 *
 * @author david
 */
public class Frameppal extends javax.swing.JFrame {

    Calendar fechaActual = new GregorianCalendar();

    JToolBar barra= new JToolBar();
    JButton boton1= new JButton("verde");
    JButton boton2 = new JButton("rojo");
    
    /**
     * Creates new form Frameppal
     */
    public Frameppal() {
        initComponents();
        this.setLocationRelativeTo(null);

        barraHerra.setOrientation(0);
        this.lblFecha.setText(mostrarFecha());
        this.lblHora.setText(mostrarHora());
    }

    public String mostrarFecha() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    public String mostrarHora() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        return formatoHora.format(hora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHora = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        barraHerra = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        btnbarraFuente = new javax.swing.JButton();
        btnBarraFondo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuFileAbrir = new javax.swing.JMenuItem();
        menuFileGuardar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuColor = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuFecha = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu con hora del sistema");

        lblHora.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblHora.setToolTipText("Mostramos del formato de Date que queremos ");

        lblFecha.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblFecha.setToolTipText("Mostramos del formato de Date que queremos ");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("Hora actual");

        jScrollPane1.setEnabled(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Contiene opciones de:\nAbrir y guardar archivos \nElegir un color con el JColorChooser \nSeleccionar una fecha con el componente JCalendar");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        barraHerra.setRollover(true);

        jButton1.setText("caja");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerra.add(jButton1);

        btnbarraFuente.setText("Imagen");
        btnbarraFuente.setFocusable(false);
        btnbarraFuente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbarraFuente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnbarraFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbarraFuenteActionPerformed(evt);
            }
        });
        barraHerra.add(btnbarraFuente);

        btnBarraFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-google.png"))); // NOI18N
        btnBarraFondo.setFocusable(false);
        btnBarraFondo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraFondo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarraFondoActionPerformed(evt);
            }
        });
        barraHerra.add(btnBarraFondo);

        menuArchivo.setText("Archivo");
        menuArchivo.setToolTipText("Opciones sobre Archivos ");
        menuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArchivoActionPerformed(evt);
            }
        });

        menuFileAbrir.setText("Abrir Archivo");
        menuFileAbrir.setToolTipText("Abrir un Archivo de texto con modal:true ");
        menuFileAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuFileAbrir);

        menuFileGuardar.setText("Guardar Archivo");
        menuFileGuardar.setToolTipText("Guardar un archivo de texto con modal:false");
        menuFileGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuFileGuardar);

        jMenuBar1.add(menuArchivo);

        jMenu2.setText("colores");
        jMenu2.setToolTipText("Abrimos JColorChooser ");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        menuColor.setText("Elegir color");
        menuColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuColorActionPerformed(evt);
            }
        });
        jMenu2.add(menuColor);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Fechas");
        jMenu1.setToolTipText("Trabajar con fechas ");

        menuFecha.setText("Elegir fechas");
        menuFecha.setToolTipText("Trabajar con fechas ");
        menuFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFechaActionPerformed(evt);
            }
        });
        jMenu1.add(menuFecha);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addComponent(barraHerra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraHerra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void menuColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuColorActionPerformed
        VistaColores vc = new VistaColores(this, false);
        vc.setVisible(true);    }//GEN-LAST:event_menuColorActionPerformed

    private void menuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArchivoActionPerformed

    }//GEN-LAST:event_menuArchivoActionPerformed

    private void menuFileAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileAbrirActionPerformed
        //instanciamos VistaAbrirArchivo modal->true:no podemos volver a la ventana
        //ppal hasta cerrar esta.        
        VistaAbrirArchivo va=  new VistaAbrirArchivo(this, true);
        va.setVisible(true);
    }//GEN-LAST:event_menuFileAbrirActionPerformed

    private void menuFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFechaActionPerformed
        VistaFecha vf = new VistaFecha(this, true);
        vf.setLocationRelativeTo(null);
        vf.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_menuFechaActionPerformed

    private void menuFileGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileGuardarActionPerformed
        VistaArchivoGuardar vag = new VistaArchivoGuardar();
        vag.setVisible(true);
    }//GEN-LAST:event_menuFileGuardarActionPerformed

    private void btnBarraFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarraFondoActionPerformed
//        JColorChooser jccp= new JColorChooser();
DialogImagen di= new DialogImagen(this, rootPaneCheckingEnabled);
di.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btnBarraFondoActionPerformed

    private void btnbarraFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbarraFuenteActionPerformed
VistaImagen vi= new VistaImagen(this, true); 
vi.setVisible(true);



// TODO add your handling code here:
    }//GEN-LAST:event_btnbarraFuenteActionPerformed

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
            java.util.logging.Logger.getLogger(Frameppal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frameppal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frameppal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frameppal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frameppal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraHerra;
    private javax.swing.JButton btnBarraFondo;
    private javax.swing.JButton btnbarraFuente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuColor;
    private javax.swing.JMenuItem menuFecha;
    private javax.swing.JMenuItem menuFileAbrir;
    private javax.swing.JMenuItem menuFileGuardar;
    // End of variables declaration//GEN-END:variables

}
