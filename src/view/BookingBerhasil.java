/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.User;

public class BookingBerhasil extends javax.swing.JFrame {
    private User currentUser;
    private String currentPickupCode;

    // Constructor Default (Hanya untuk preview)
    public BookingBerhasil() {
        initComponents();
    }
    // Constructor UTAMA (Menerima User & Kode Pickup)
    public BookingBerhasil(User user, String pickupCode) {
        initComponents();
        this.currentUser = user;
        this.currentPickupCode = pickupCode;
        
        // Tampilkan Kode Pickup yang didapat dari Database
        lblPickupCode.setText(pickupCode);
        
        // Setting Window
        this.setLocationRelativeTo(null); // Tengah layar
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        dahboard = new java.awt.Panel();
        Heading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        lblPickupCode = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnComment = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dahboard.setPreferredSize(new java.awt.Dimension(320, 64));
        dahboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heading.setFont(new java.awt.Font("Lufga", 1, 20)); // NOI18N
        Heading.setForeground(new java.awt.Color(255, 255, 255));
        Heading.setText("SumbangBang");
        dahboard.add(Heading, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Logo_kicik.png"))); // NOI18N
        dahboard.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        bg_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/bg_dashboard.jpg"))); // NOI18N
        dahboard.add(bg_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        getContentPane().add(dahboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/donee.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 40));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lufga", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Booking Berhasil!!");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        panel1.setForeground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPickupCode.setBackground(new java.awt.Color(255, 255, 255));
        lblPickupCode.setFont(new java.awt.Font("Lufga", 1, 24)); // NOI18N
        lblPickupCode.setForeground(new java.awt.Color(0, 102, 102));
        lblPickupCode.setText(" ");
        panel1.add(lblPickupCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 100, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Lufga", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Kode Pickup anda:");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("saat mengambil makanan");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, 20));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Simpan code ini dan tunjukan ");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 270, 180));

        btnComment.setBackground(new java.awt.Color(0, 175, 119));
        btnComment.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnComment.setForeground(new java.awt.Color(255, 255, 255));
        btnComment.setText("Beri Komentar");
        btnComment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommentActionPerformed(evt);
            }
        });
        getContentPane().add(btnComment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 272, 41));

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnHome.setForeground(new java.awt.Color(0, 102, 102));
        btnHome.setText("Ke Beranda");
        btnHome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 272, 41));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        bg.setPreferredSize(new java.awt.Dimension(272, 174));
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 568));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommentActionPerformed
        if (currentPickupCode != null && !currentPickupCode.isEmpty()) {
            new FormKomentar(currentUser, currentPickupCode).setVisible(true);
            this.dispose();
        } else {
            System.out.println("Error: Kode Pickup Kosong!");
        }
    }//GEN-LAST:event_btnCommentActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        new DashboardPenerima(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookingBerhasil().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JButton btnComment;
    private javax.swing.JButton btnHome;
    private java.awt.Panel dahboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel lblPickupCode;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
