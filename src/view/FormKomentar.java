/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DonationDAO;
import model.User;
import javax.swing.JOptionPane;

public class FormKomentar extends javax.swing.JFrame {
    private User recipient;
    private String pickupCode;
    private DonationDAO donationDAO;
    
// Data yang diambil dari database
    private String reservationId;
    private String donorId;
    // Constructor Default (Hanya untuk preview)
    public FormKomentar() {
        initComponents();
    }
    // Constructor UTAMA (Menerima User & Kode Pickup)
    // Ganti parameter jadi 'pickupCode' biar jelas
    public FormKomentar(User user, String pickupCode) { 
        initComponents();
        this.recipient = user;
        this.pickupCode = pickupCode; // Simpan ke variabel global
        this.donationDAO = new dao.DonationDAO(); // (Jangan lupa package dao)
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        loadReservationData();
    }
    private void loadReservationData() {
        // Cari data reservasi berdasarkan kode pickup
        String[] data = donationDAO.getReservationDetailByCode(pickupCode);
        
        if (data != null) {
            this.reservationId = data[0];
            this.donorId = data[1];
            String foodName = data[2];
            
            // Tampilkan nama makanan
            lblFoodName.setText("Menu: " + foodName);
        } else {
            JOptionPane.showMessageDialog(this, "Data reservasi tidak ditemukan!");
            this.dispose();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        dahboard = new java.awt.Panel();
        Heading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        lblFoodName = new javax.swing.JLabel();
        headingDetail = new javax.swing.JLabel();
        lblFoodName1 = new javax.swing.JLabel();
        comboRating = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKomentar = new javax.swing.JTextArea();
        bg = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        btnCancel.setBackground(new java.awt.Color(234, 234, 234));
        btnCancel.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(0, 102, 102));
        btnCancel.setText("Nanti Saja");
        btnCancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 272, 41));

        btnSubmit.setBackground(new java.awt.Color(0, 175, 119));
        btnSubmit.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Kirim Ulasan");
        btnSubmit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 272, 41));

        lblFoodName.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        lblFoodName.setForeground(new java.awt.Color(0, 0, 0));
        lblFoodName.setText("Bagaimana rasa makanannya?");
        getContentPane().add(lblFoodName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 230, 20));

        headingDetail.setFont(new java.awt.Font("Lufga", 1, 20)); // NOI18N
        headingDetail.setForeground(new java.awt.Color(0, 175, 119));
        headingDetail.setText("Beri Ulasan & Rating");
        getContentPane().add(headingDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        lblFoodName1.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        lblFoodName1.setForeground(new java.awt.Color(0, 0, 0));
        lblFoodName1.setText(" ");
        getContentPane().add(lblFoodName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 20));

        comboRating.setBackground(new java.awt.Color(255, 255, 255));
        comboRating.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        comboRating.setForeground(new java.awt.Color(0, 102, 102));
        comboRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sangat Enak", "Enak", "Biasa saja", "Kurang enak", "Tidak enak" }));
        getContentPane().add(comboRating, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 40));

        txtKomentar.setBackground(new java.awt.Color(255, 255, 255));
        txtKomentar.setColumns(20);
        txtKomentar.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        txtKomentar.setForeground(new java.awt.Color(0, 102, 102));
        txtKomentar.setRows(5);
        jScrollPane1.setViewportView(txtKomentar);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 260, 130));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        bg.setPreferredSize(new java.awt.Dimension(272, 174));
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 568));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        new DashboardPenerima(recipient).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        String komentar = txtKomentar.getText().trim();
        
        // Ambil nilai rating dari Dropdown
        // Index 0 = "Sangat Enak (5)", dst.
        // Kita konversi index combobox jadi skor 5-1
        int selectedIndex = comboRating.getSelectedIndex(); 
        int rating = 5 - selectedIndex; // Index 0->5, Index 1->4, dst.
        
        if (komentar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tuliskan sedikit ulasanmu ya!");
            return;
        }
        
        // Simpan ke Database
        boolean success = donationDAO.insertComment(reservationId, recipient.getUserId(), donorId, komentar, rating);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Terima kasih atas ulasanmu!");
            
            // Balik ke Dashboard Penerima
            new DashboardPenerima(recipient).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal mengirim ulasan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormKomentar().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> comboRating;
    private java.awt.Panel dahboard;
    private javax.swing.JLabel headingDetail;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFoodName;
    private javax.swing.JLabel lblFoodName1;
    private javax.swing.JTextArea txtKomentar;
    // End of variables declaration//GEN-END:variables
}
