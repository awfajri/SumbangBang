/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DonationDAO;
import model.FoodDonation;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class DetailDonasi extends javax.swing.JFrame {

    // Variabel global untuk menyimpan ID
    private String donationId;

    public DetailDonasi(String donationId) {
        this.donationId = donationId;
        
        initComponents();
        
        // Panggil method untuk mengisi data
        loadData(); 
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void loadData() {
        try {
            DonationDAO dao = new DonationDAO();
            FoodDonation d = dao.getDonationById(this.donationId);
            
            if (d != null) {
                // 1. Nama Makanan
                valNamaMakanan.setText(d.getFoodName());
                
                // 2. Nama Donatur
                String donaturName = d.getDonorName(); 
                if (donaturName == null) donaturName = "Donatur (Restoran)";
                valDonatur.setText("Oleh: " + donaturName);

                // 3. Status & Warna
                valStatus.setText(d.getStatus());
                if (d.getStatus().equalsIgnoreCase("AVAILABLE")) {
                    valStatus.setForeground(new Color(0, 175, 119)); 
                } else {
                    valStatus.setForeground(Color.ORANGE);
                }

                // 4. Jumlah Porsi
                valPorsi.setText(d.getQuantity() + " Porsi");

                // 5. Tanggal Kadaluarsa
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                if (d.getExpiryDate() != null) {
                    valTanggal.setText(sdf.format(d.getExpiryDate()));
                } else {
                    valTanggal.setText("-");
                }
                
                // 6. Lokasi Pengambilan
                String lokasi = d.getPickupLocation();
                valLokasi.setText("<html><div style='width:180px'>" + lokasi + "</div></html>");
        } else {
                JOptionPane.showMessageDialog(this, "Data donasi tidak ditemukan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data.");
        }
} 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        detail = new javax.swing.JLabel();
        headingDetail = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        dahboard = new java.awt.Panel();
        Heading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        food = new javax.swing.JLabel();
        valNamaMakanan = new javax.swing.JLabel();
        valDonatur = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        valStatus = new javax.swing.JLabel();
        valPorsi = new javax.swing.JLabel();
        valTanggal = new javax.swing.JLabel();
        valLokasi = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detail.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        detail.setForeground(new java.awt.Color(0, 0, 0));
        detail.setText("Detail makanan anda");
        getContentPane().add(detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 150, 20));

        headingDetail.setFont(new java.awt.Font("Lufga", 1, 20)); // NOI18N
        headingDetail.setForeground(new java.awt.Color(0, 175, 119));
        headingDetail.setText("Detail Donasi");
        getContentPane().add(headingDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 30));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnBack.setText("jLabel3");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 30, 30));

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

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        food.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/food.jpg"))); // NOI18N
        food.setText("jLabel5");

        valNamaMakanan.setFont(new java.awt.Font("Lufga", 1, 14)); // NOI18N
        valNamaMakanan.setForeground(new java.awt.Color(0, 175, 119));
        valNamaMakanan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valNamaMakanan.setText("...");

        valDonatur.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        valDonatur.setForeground(new java.awt.Color(0, 0, 0));
        valDonatur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valDonatur.setText("...");

        jLabel6.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel6.setText("Status");

        jLabel7.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel7.setText("Jumlah");

        jLabel8.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel8.setText("Tgl Kadaluarsa");

        jLabel9.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        jLabel9.setText("Lokasi Pengambilan");

        valStatus.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        valStatus.setForeground(new java.awt.Color(0, 175, 119));
        valStatus.setText("...");

        valPorsi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        valPorsi.setForeground(new java.awt.Color(0, 175, 119));
        valPorsi.setText("...");

        valTanggal.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        valTanggal.setForeground(new java.awt.Color(0, 175, 119));
        valTanggal.setText("...");

        valLokasi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        valLokasi.setForeground(new java.awt.Color(0, 175, 119));
        valLokasi.setText("...");

        javax.swing.GroupLayout cardPanelLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardPanelLayout);
        cardPanelLayout.setHorizontalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanelLayout.createSequentialGroup()
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(food, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardPanelLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(cardPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(valDonatur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(valNamaMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardPanelLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cardPanelLayout.createSequentialGroup()
                                    .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(valStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(valPorsi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(valTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                                .addGroup(cardPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(valLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(food, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valNamaMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valDonatur, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(valStatus))
                .addGap(18, 18, 18)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(valPorsi))
                .addGap(25, 25, 25)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(valTanggal))
                .addGap(25, 25, 25)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(valLokasi))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        getContentPane().add(cardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 280, 374));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel4");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 568));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JLabel btnBack;
    private javax.swing.JPanel cardPanel;
    private java.awt.Panel dahboard;
    private javax.swing.JLabel detail;
    private javax.swing.JLabel food;
    private javax.swing.JLabel headingDetail;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel valDonatur;
    private javax.swing.JLabel valLokasi;
    private javax.swing.JLabel valNamaMakanan;
    private javax.swing.JLabel valPorsi;
    private javax.swing.JLabel valStatus;
    private javax.swing.JLabel valTanggal;
    // End of variables declaration//GEN-END:variables
}