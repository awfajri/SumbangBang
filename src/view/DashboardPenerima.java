/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import dao.DonationDAO;
import java.awt.FlowLayout;
import model.FoodDonation;
import model.User;
import sumbangbang.SumbangBang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardPenerima extends javax.swing.JFrame {
    // Ambil user yang sedang login
    private User currentUser = SumbangBang.loggedInUser;
    private DonationDAO donationDAO;
    
    // Style
    private final Color PRIMARY_COLOR = new Color(0, 175, 119);
    private final Font FONT_BOLD = new Font("Lufga", Font.BOLD, 14);
    private final Font FONT_PLAIN = new Font("Lufga", Font.PLAIN, 12);
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardPenerima.class.getName());

    /**
     * Creates new form NewJFrame
     */
    public DashboardPenerima() {
    initComponents();
    this.donationDAO = new DonationDAO();
        
    // Validasi Login
    if (currentUser == null) {
        JOptionPane.showMessageDialog(this, "Sesi habis. Silakan login kembali.");
        new login().setVisible(true);
        this.dispose();
        return;
    }
    setLocationRelativeTo(null);
    loadAvailableDonations();
}
    private void loadAvailableDonations() {
        List<FoodDonation> list = donationDAO.getAvailableDonations();
        
        // Tampilkan datanya
        displayDonationList(list);
    }
    
    private void searchDonations() {
        String keyword = inputCari.getText().trim();
        
        if (keyword.isEmpty() || keyword.equals("Cari makanan...")) {
            loadAvailableDonations(); // Balik ke semua data
        } else {
            // Panggil method search di DAO
            List<FoodDonation> list = donationDAO.searchDonations(keyword);
            displayDonationList(list);
        }
    }
    
    private void displayDonationList(List<FoodDonation> list) {
        donationListPanel.removeAll();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        if (list.isEmpty()) {
            donationListPanel.setLayout(new BorderLayout());
            JLabel emptyLabel = new JLabel("Tidak ada donasi yang cocok.");
            emptyLabel.setFont(new Font("Lufga", Font.ITALIC, 12));
            emptyLabel.setForeground(Color.GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            donationListPanel.add(emptyLabel, BorderLayout.CENTER);
            
        } else {
            donationListPanel.setLayout(new javax.swing.BoxLayout(donationListPanel, javax.swing.BoxLayout.Y_AXIS));

            for (FoodDonation donation : list) {
                
                String foodName = donation.getFoodName();
                String donorName = "Oleh: " + (donation.getDonorName() != null ? donation.getDonorName() : "Donatur");
                String quantity = donation.getQuantity() + " Porsi";
                
                // Potong Lokasi jika kepanjangan
                String locationRaw = donation.getPickupLocation();
                if (locationRaw.length() > 25) locationRaw = locationRaw.substring(0, 22) + "...";
                String location = "<html>Loc: " + locationRaw + "</html>";
                
                String expiry = "Exp: " + formatter.format(donation.getExpiryDate());
                String donationId = donation.getDonationId();

                // === CARD ===
                JPanel card = new JPanel(new BorderLayout(0, 10)); // Jarak vertikal 10
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1), 
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
                
                int panelWidth = donationScrollPane.getWidth();
                if (panelWidth <= 0) panelWidth = 400;
                
                // --- PERBAIKAN 1: Tinggi Card Ditambah jadi 150 ---
                Dimension cardSize = new Dimension(panelWidth - 40, 120);
                card.setPreferredSize(cardSize);
                card.setMaximumSize(cardSize);
                card.setMinimumSize(cardSize);

                // === PANEL ATAS ===
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setBackground(Color.WHITE);
                
                JPanel titleBox = new JPanel(new GridLayout(2, 1, 0, 5));
                titleBox.setBackground(Color.WHITE);
                
                JLabel lblFood = new JLabel(foodName);
                lblFood.setFont(new Font("Lufga", Font.BOLD, 15));
                lblFood.setForeground(new Color(33, 37, 41));
                
                JLabel lblDonor = new JLabel(donorName);
                lblDonor.setFont(new Font("Lufga", Font.PLAIN, 12));
                lblDonor.setForeground(Color.GRAY);
                
                titleBox.add(lblFood);
                titleBox.add(lblDonor);
                
                // --- PERBAIKAN 2: Ukuran Tombol Booking ---
                JButton btnBooking = new JButton("Booking");
                btnBooking.setBackground(PRIMARY_COLOR);
                btnBooking.setForeground(Color.WHITE);
                btnBooking.setFont(new Font("Lufga", Font.BOLD, 8));
                btnBooking.setFocusPainted(false);
                btnBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                
                btnBooking.addActionListener(e -> {
                    // JANGAN PANGGIL DATABASE DI SINI!
                    // Cukup buka Form Konfirmasi dan kirim datanya
                    new FormKonfirmasi(DashboardPenerima.this, donation).setVisible(true);
                });
                
                // Margin tipis (biar tidak gembung)
                btnBooking.setMargin(new java.awt.Insets(2, 5, 2, 5));
                
                // Ukuran Pas (Lebar 90, Tinggi 30) -> Biar tulisan "Booking" muat
                btnBooking.setPreferredSize(new Dimension(70, 30));
                
                btnBooking.addActionListener(e -> {
                    new FormKonfirmasi(DashboardPenerima.this, donation).setVisible(true);
                });
                
                // Wrapper agar ukuran tombol terjaga
                JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                btnWrapper.setOpaque(false);
                btnWrapper.add(btnBooking);
                
                topPanel.add(titleBox, BorderLayout.CENTER);
                topPanel.add(btnWrapper, BorderLayout.EAST);

                // === PANEL BAWAH (GRID) ===
                // Grid 2x2 dengan jarak vertikal (vgap) 8px
                JPanel detailPanel = new JPanel(new GridLayout(2, 2, 5, 8));
                detailPanel.setBackground(Color.WHITE);
                
                JLabel lblQty = new JLabel(quantity);
                lblQty.setFont(new Font("Lufga", Font.PLAIN, 12));
                lblQty.setForeground(new Color(50, 50, 50));
                
                JLabel lblStatus = new JLabel("Tersedia");
                lblStatus.setFont(new Font("Lufga", Font.BOLD, 11));
                lblStatus.setForeground(new Color(0, 150, 100));
                
                JLabel lblExp = new JLabel(expiry);
                lblExp.setFont(new Font("Lufga", Font.PLAIN, 11));
                lblExp.setForeground(Color.GRAY);
                
                JLabel lblLoc = new JLabel(location);
                lblLoc.setFont(new Font("Lufga", Font.PLAIN, 11));
                lblLoc.setForeground(Color.GRAY);
                
                detailPanel.add(lblQty);
                detailPanel.add(lblStatus);
                detailPanel.add(lblExp);
                detailPanel.add(lblLoc);

                // === GABUNGKAN ===
                card.add(topPanel, BorderLayout.NORTH);
                // Detail Panel di Center atau South
                card.add(detailPanel, BorderLayout.SOUTH);

                donationListPanel.add(card);
                donationListPanel.add(Box.createVerticalStrut(15)); 
            }
        }
        donationListPanel.revalidate();
        donationListPanel.repaint();
}
    public void refreshData() {
        loadAvailableDonations();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        dahboard = new java.awt.Panel();
        Heading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        labelRole1 = new javax.swing.JLabel();
        labelRole2 = new javax.swing.JLabel();
        btnCari = new java.awt.Button();
        inputCari = new javax.swing.JTextField();
        donationScrollPane = new javax.swing.JScrollPane();
        donationListPanel = new javax.swing.JPanel();
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

        labelRole1.setBackground(new java.awt.Color(0, 113, 77));
        labelRole1.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelRole1.setForeground(new java.awt.Color(0, 0, 0));
        labelRole1.setText("Temukan makanan yang tersedia");
        getContentPane().add(labelRole1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        labelRole2.setBackground(new java.awt.Color(0, 113, 77));
        labelRole2.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        labelRole2.setForeground(new java.awt.Color(0, 0, 0));
        labelRole2.setText("Donasi Tersedia");
        getContentPane().add(labelRole2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        btnCari.setBackground(new java.awt.Color(0, 175, 119));
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCari.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setLabel("Cari");
        btnCari.setName(""); // NOI18N
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        getContentPane().add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 70, 40));

        inputCari.setBackground(new java.awt.Color(255, 255, 255));
        inputCari.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        inputCari.setForeground(new java.awt.Color(0, 0, 0));
        inputCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        inputCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCariActionPerformed(evt);
            }
        });
        getContentPane().add(inputCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 200, 40));

        donationScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        donationScrollPane.setBorder(null);

        donationListPanel.setBackground(new java.awt.Color(255, 255, 255));
        donationListPanel.setPreferredSize(new java.awt.Dimension(250, 450));

        javax.swing.GroupLayout donationListPanelLayout = new javax.swing.GroupLayout(donationListPanel);
        donationListPanel.setLayout(donationListPanelLayout);
        donationListPanelLayout.setHorizontalGroup(
            donationListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        donationListPanelLayout.setVerticalGroup(
            donationListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        donationScrollPane.setViewportView(donationListPanel);

        getContentPane().add(donationScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 280, 340));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        bg.setPreferredSize(new java.awt.Dimension(272, 174));
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 568));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // 1. Ambil teks dari kotak pencarian
        String keyword = inputCari.getText().trim();
        
        // 2. Cek apakah kosong atau masih placeholder default
        if (keyword.isEmpty() || keyword.equalsIgnoreCase("Cari makanan...")) {
            // Jika kosong, tampilkan SEMUA data lagi
            loadAvailableDonations(); 
        } else {
            // 3. Jika ada teks, panggil method search di DAO
            List<FoodDonation> searchResult = donationDAO.searchDonations(keyword);
            
            // 4. Tampilkan hasil pencarian ke layar
            // (Kita pakai method displayDonationList yang sudah kita buat sebelumnya)
            displayDonationList(searchResult);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void inputCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCariActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DashboardPenerima().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private java.awt.Button btnCari;
    private java.awt.Panel dahboard;
    private javax.swing.JPanel donationListPanel;
    private javax.swing.JScrollPane donationScrollPane;
    private javax.swing.JTextField inputCari;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelRole1;
    private javax.swing.JLabel labelRole2;
    // End of variables declaration//GEN-END:variables
}
