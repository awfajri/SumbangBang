/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DonationDAO;
import model.FoodDonation;
import sumbangbang.SumbangBang;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class FormKonfirmasi extends javax.swing.JFrame {
    private DashboardPenerima parentDashboard;
    private FoodDonation donationData;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormKonfirmasi.class.getName());

    public FormKonfirmasi(DashboardPenerima parent, FoodDonation donation) {
        this.parentDashboard = parent;
        this.donationData = donation;
        
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        // Isi data ke text field
        isiDataKeForm();
    }    
        private void isiDataKeForm() {
        if (donationData != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            
            // Set Text
            fieldNama.setText(donationData.getFoodName());
            fieldJumlah.setText(donationData.getQuantity() + " Porsi");
            fieldLokasi.setText(donationData.getPickupLocation());
            fieldTanggal.setText(sdf.format(donationData.getExpiryDate()));
            
            // Bikin Read-Only (Supaya tidak bisa diedit user)
            fieldNama.setEditable(false);
            fieldJumlah.setEditable(false);
            fieldLokasi.setEditable(false);
            fieldTanggal.setEditable(false);
        }
    }                                            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dahboard = new java.awt.Panel();
        Heading1 = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        fieldNama = new javax.swing.JTextField();
        fieldJumlah = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();
        fieldTanggal = new javax.swing.JTextField();
        labelPassword1 = new javax.swing.JLabel();
        fieldLokasi = new javax.swing.JTextField();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dahboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heading1.setFont(new java.awt.Font("Lufga", 1, 20)); // NOI18N
        Heading1.setForeground(new java.awt.Color(255, 255, 255));
        Heading1.setText("SumbangBang");
        dahboard.add(Heading1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Logo_kicik.png"))); // NOI18N
        dahboard.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        bg_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/bg_dashboard.jpg"))); // NOI18N
        dahboard.add(bg_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        getContentPane().add(dahboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        labelNama.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelNama.setForeground(new java.awt.Color(142, 142, 147));
        labelNama.setText("Nama Makanan");
        getContentPane().add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        fieldNama.setBackground(new java.awt.Color(255, 255, 255));
        fieldNama.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        fieldNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        fieldNama.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(fieldNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        fieldJumlah.setBackground(new java.awt.Color(255, 255, 255));
        fieldJumlah.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        fieldJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        fieldJumlah.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(fieldJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        labelEmail.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(142, 142, 147));
        labelEmail.setText("Jumlah (porsi)");
        getContentPane().add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        labelPassword.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelPassword.setForeground(new java.awt.Color(142, 142, 147));
        labelPassword.setText("Tanggal Kadaluarsa");
        getContentPane().add(labelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        btnKonfirmasi.setBackground(new java.awt.Color(0, 175, 119));
        btnKonfirmasi.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKonfirmasi.setText("Konfirmasi");
        btnKonfirmasi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonfirmasiActionPerformed(evt);
            }
        });
        getContentPane().add(btnKonfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 272, 41));

        fieldTanggal.setBackground(new java.awt.Color(255, 255, 255));
        fieldTanggal.setForeground(new java.awt.Color(0, 0, 0));
        fieldTanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fieldTanggal.setCaretColor(new java.awt.Color(224, 224, 224));
        fieldTanggal.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(fieldTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        labelPassword1.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelPassword1.setForeground(new java.awt.Color(142, 142, 147));
        labelPassword1.setText("Lokasi Pengambilan");
        getContentPane().add(labelPassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        fieldLokasi.setBackground(new java.awt.Color(255, 255, 255));
        fieldLokasi.setForeground(new java.awt.Color(0, 0, 0));
        fieldLokasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fieldLokasi.setCaretColor(new java.awt.Color(224, 224, 224));
        fieldLokasi.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(fieldLokasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed

    }//GEN-LAST:event_btnKonfirmasiActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading1;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JButton btnKonfirmasi;
    private java.awt.Panel dahboard;
    private javax.swing.JTextField fieldJumlah;
    private javax.swing.JTextField fieldLokasi;
    private javax.swing.JTextField fieldNama;
    private javax.swing.JTextField fieldTanggal;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelPassword1;
    // End of variables declaration//GEN-END:variables
}
