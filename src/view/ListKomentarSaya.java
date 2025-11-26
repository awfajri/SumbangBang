package view;

import dao.SentCommentDAO;
import java.awt.Component;
import model.Comment;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ListKomentarSaya extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ListKomentarSaya.class.getName());
    
    private User currentUser = SumbangBang.loggedInUser;
    
    // Style Warna
    private final Color PRIMARY_COLOR = new Color(0, 175, 119);
    private final Color TEXT_DARK = new Color(33, 37, 41);
    private final Color TEXT_GRAY = new Color(108, 117, 125);

    public ListKomentarSaya() {
        initComponents();
        
        // Setup Layout Panel List agar bisa menumpuk ke bawah
        panelListUlasan.setLayout(new javax.swing.BoxLayout(panelListUlasan, javax.swing.BoxLayout.Y_AXIS));
        
        // Panggil logika untuk mengisi data
        loadMyComments(); 
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void loadMyComments() {
        // 1. Bersihkan panel
        panelListUlasan.removeAll(); // Sesuaikan nama variabel panel kamu (panelListUlasan)
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        dao.SentCommentDAO dao = new dao.SentCommentDAO();
        
        // Ambil data
        List<Comment> list = dao.getCommentsByRecipient(currentUser.getUserId());

        if (list.isEmpty()) {
            panelListUlasan.setLayout(new BorderLayout());
            JLabel emptyLabel = new JLabel("Belum ada ulasan yang dikirim.");
            emptyLabel.setFont(new Font("Lufga", Font.ITALIC, 14));
            emptyLabel.setForeground(Color.GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panelListUlasan.add(emptyLabel, BorderLayout.CENTER);
            
        } else {
            // Gunakan BoxLayout Y agar kartu menumpuk ke bawah
            panelListUlasan.setLayout(new javax.swing.BoxLayout(panelListUlasan, javax.swing.BoxLayout.Y_AXIS));

            for (Comment c : list) {
                // --- DATA ---
                String toDonatur = "Ke: " + c.getRecipientName(); 
                String food = "Menu: " + c.getFoodName();
                String date = (c.getDate() != null) ? dateFormat.format(c.getDate()) : "-";
                String content = "<html><div style='width:150px'>" + c.getCommentText() + "</div></html>"; 
                String ratingStr = "⭐".repeat(c.getRating()); 

                // === SETUP CARD ===
                JPanel card = new JPanel(new BorderLayout()); // Hapus gap
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
                
                int scrollWidth = scrollPane.getWidth();
                if (scrollWidth <= 0) scrollWidth = 280;
                
                // 2. BUAT STRIP HIJAU (KIRI)
                JPanel greenStrip = new JPanel();
                greenStrip.setBackground(PRIMARY_COLOR);
                greenStrip.setPreferredSize(new Dimension(10, 0)); // Lebar 10px

                // 3. BUAT PANEL KONTEN (TENGAH)
                // Panel ini yang akan menampung semua teks
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.Y_AXIS));
                contentPanel.setBackground(Color.WHITE);

                contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

                // -- Isi Konten --
                JPanel headerInfo = new JPanel(new BorderLayout());
                headerInfo.setBackground(Color.WHITE);
                headerInfo.setMaximumSize(new Dimension(1000, 20)); 
                
                JLabel lblTo = new JLabel(toDonatur);
                lblTo.setFont(new Font("Lufga", Font.BOLD, 13)); // Font agak diperkecil biar muat
                lblTo.setForeground(TEXT_DARK);
                
                JLabel lblDate = new JLabel(date);
                lblDate.setFont(new Font("Lufga", Font.PLAIN, 10));
                lblDate.setForeground(TEXT_GRAY);
                
                headerInfo.add(lblTo, BorderLayout.WEST);
                headerInfo.add(lblDate, BorderLayout.EAST);
                
                headerInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel lblFood = new JLabel(food);
                lblFood.setFont(new Font("Lufga", Font.BOLD, 12));
                lblFood.setForeground(PRIMARY_COLOR);
                
                JLabel lblRating = new JLabel(ratingStr);
                lblRating.setForeground(new Color(255, 193, 7)); 
                
                JLabel lblContent = new JLabel(content);
                lblContent.setFont(new Font("Lufga", Font.PLAIN, 12));
                lblContent.setForeground(new Color(80, 80, 80));
                lblContent.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                // Masukkan ke contentPanel
                contentPanel.add(headerInfo);
                contentPanel.add(Box.createVerticalStrut(5)); 
                contentPanel.add(lblFood);
                contentPanel.add(lblRating);
                contentPanel.add(Box.createVerticalStrut(8)); 
                contentPanel.add(lblContent);

                // === 4. GABUNGKAN (Card Utama) ===
                // Strip Hijau di Kiri (WEST), Konten di Tengah (CENTER)
                card.add(greenStrip, BorderLayout.WEST);
                card.add(contentPanel, BorderLayout.CENTER);

                // Masukkan ke List Panel Utama
                panelListUlasan.add(card);
                panelListUlasan.add(Box.createVerticalStrut(15)); // Jarak antar kartu
            }
        }
        
        // Wajib refresh UI
        panelListUlasan.revalidate();
        panelListUlasan.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Heading = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        panelListUlasan = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heading.setFont(new java.awt.Font("Lufga", 1, 22)); // NOI18N
        Heading.setForeground(new java.awt.Color(0, 136, 113));
        Heading.setText("Ulasan Terkirim");
        getContentPane().add(Heading, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnBack.setText("jLabel1");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 30, 30));

        scrollPane.setBorder(null);

        panelListUlasan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelListUlasanLayout = new javax.swing.GroupLayout(panelListUlasan);
        panelListUlasan.setLayout(panelListUlasanLayout);
        panelListUlasanLayout.setHorizontalGroup(
            panelListUlasanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelListUlasanLayout.setVerticalGroup(
            panelListUlasanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(panelListUlasan);

        getContentPane().add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, 430));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel2");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose(); // Tutup halaman ulasan
    }//GEN-LAST:event_btnBackMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new ListKomentarSaya().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel btnBack;
    private javax.swing.JPanel panelListUlasan;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
