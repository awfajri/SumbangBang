package view;

import dao.DonationDAO;
import model.FoodDonation;
import model.User;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class KonfirmasiBooking extends javax.swing.JFrame{
    //variabel data
    private FoodDonation donation;
    private User recipient;
    private DashboardPenerima dashboard;
    private DonationDAO donationDAO;
    
    public KonfirmasiBooking(){
        initComponents();
    }
    /**
     * Constructor UTAMA 
     * @param dashboard DashboardPenerima 
     * @param recipient User 
     * @param donation Data 
     */
    public KonfirmasiBooking(DashboardPenerima dashboard, User recipient, FoodDonation donation){
        initComponents();
        this.dashboard = dashboard;
        this.recipient = recipient;
        this.donation = donation;
        this.donationDAO = new DonationDAO();
        
        // Setting Window
        this.setLocationRelativeTo(null); // Muncul di tengah layar
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Tutup window ini aja, jangan semua
        
        setupForm();
    }
    private void setupForm() {
        if (donation != null) {
            // 1. Isi Data ke TextField (Read-Only)
            textInfoName.setText(donation.getFoodName());
            txtInfoLocation.setText(donation.getPickupLocation());
            
            // Format Tanggal
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            // Cek null safety untuk tanggal (best practice)
            String expDate = (donation.getExpiryDate() != null) ? sdf.format(donation.getExpiryDate()) : "-";
            txtInfoExp.setText(expDate);
            
            // Tampilkan Sisa Stok di Label
            if (lblStok != null) {
                lblStok.setText("Tersedia: " + donation.getQuantity() + " Porsi");
            }
            
            // 2. Kunci TextField Info (Biar gak bisa diedit)
            textInfoName.setEditable(false);
            txtInfoLocation.setEditable(false);
            txtInfoExp.setEditable(false);
            
            // 3. Reset Input Jumlah (Ini yang wajib diisi user)
            txtInputQty.setText("");
            txtInputQty.setEditable(true); 
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
        textInfoName = new javax.swing.JTextField();
        txtInfoLocation = new javax.swing.JTextField();
        labelLokasi = new javax.swing.JLabel();
        labelExp = new javax.swing.JLabel();
        btnBatal = new javax.swing.JButton();
        txtInfoExp = new javax.swing.JTextField();
        labelQTY = new javax.swing.JLabel();
        txtInputQty = new javax.swing.JTextField();
        lblStok = new javax.swing.JLabel();
        btnKonfirmasi = new javax.swing.JButton();
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

        textInfoName.setEditable(false);
        textInfoName.setBackground(new java.awt.Color(255, 255, 255));
        textInfoName.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        textInfoName.setForeground(new java.awt.Color(0, 102, 102));
        textInfoName.setText("   ");
        textInfoName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        textInfoName.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(textInfoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtInfoLocation.setBackground(new java.awt.Color(255, 255, 255));
        txtInfoLocation.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        txtInfoLocation.setForeground(new java.awt.Color(0, 102, 102));
        txtInfoLocation.setText("   ");
        txtInfoLocation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 224)));
        txtInfoLocation.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(txtInfoLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        labelLokasi.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelLokasi.setForeground(new java.awt.Color(142, 142, 147));
        labelLokasi.setText("Lokasi Pengambilan");
        getContentPane().add(labelLokasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        labelExp.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelExp.setForeground(new java.awt.Color(142, 142, 147));
        labelExp.setText("Tanggal Kadaluarsa");
        getContentPane().add(labelExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        btnBatal.setBackground(new java.awt.Color(204, 204, 204));
        btnBatal.setFont(new java.awt.Font("Lufga", 1, 16)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(0, 102, 102));
        btnBatal.setText("Batal");
        btnBatal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 224, 224), 1, true));
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 272, 41));

        txtInfoExp.setBackground(new java.awt.Color(255, 255, 255));
        txtInfoExp.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        txtInfoExp.setForeground(new java.awt.Color(0, 102, 102));
        txtInfoExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtInfoExp.setCaretColor(new java.awt.Color(224, 224, 224));
        txtInfoExp.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(txtInfoExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        labelQTY.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        labelQTY.setForeground(new java.awt.Color(142, 142, 147));
        labelQTY.setText("Jumlah Porsi yang diambil");
        getContentPane().add(labelQTY, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        txtInputQty.setBackground(new java.awt.Color(255, 255, 255));
        txtInputQty.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        txtInputQty.setForeground(new java.awt.Color(0, 102, 102));
        txtInputQty.setText("   ");
        txtInputQty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtInputQty.setCaretColor(new java.awt.Color(224, 224, 224));
        txtInputQty.setPreferredSize(new java.awt.Dimension(272, 44));
        getContentPane().add(txtInputQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        lblStok.setFont(new java.awt.Font("Lufga", 1, 14)); // NOI18N
        lblStok.setForeground(new java.awt.Color(0, 102, 102));
        lblStok.setText("Sisa: ...");
        getContentPane().add(lblStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, -1, -1));

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

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonfirmasiActionPerformed
        // TODO add your handling code here:
        // 1. Ambil Inputan User
        String inputStr = txtInputQty.getText().trim();
        
        // 2. Validasi Dasar
        if (inputStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi jumlah porsi yang ingin diambil!");
            return;
        }
        
        try {
            int qtyTaken = Integer.parseInt(inputStr);
            int maxQty = donation.getQuantity();
            
            // 3. Validasi Logika Jumlah
            if (qtyTaken <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah minimal 1 porsi!");
                return;
            }
            
            if (qtyTaken > maxQty) {
                JOptionPane.showMessageDialog(this, "Stok tidak cukup! Hanya ada " + maxQty + " porsi.");
                return;
            }
            
            // 4. Eksekusi Booking ke Database
            // Di dalam btnKonfirmasiActionPerformed...

            // 4. Eksekusi Booking (Sekarang mengembalikan String)
            String pickupCode = donationDAO.bookDonation(
                donation.getDonationId(), 
                recipient.getUserId(), 
                qtyTaken
            );

            // Cek apakah kode berhasil didapat (tidak null)
            if (pickupCode != null) {
                // Tutup form konfirmasi & dashboard lama
                this.dispose();
                if (dashboard != null) dashboard.dispose(); 

                // Buka Halaman Sukses dengan Kode Pickup
                new BookingBerhasil(recipient, pickupCode).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Gagal Booking. Silakan coba lagi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        //</editor-fold>
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading1;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnKonfirmasi;
    private java.awt.Panel dahboard;
    private javax.swing.JLabel labelExp;
    private javax.swing.JLabel labelLokasi;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelQTY;
    private javax.swing.JLabel lblStok;
    private javax.swing.JTextField textInfoName;
    private javax.swing.JTextField txtInfoExp;
    private javax.swing.JTextField txtInfoLocation;
    private javax.swing.JTextField txtInputQty;
    // End of variables declaration//GEN-END:variables
}
