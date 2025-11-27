package view;

import dao.CommentDAO;
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

public class ListKomentarDonatur extends javax.swing.JFrame {
    private User currentUser = SumbangBang.loggedInUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ListKomentarDonatur.class.getName());
    
    // Style Warna & Font
    private final Color PRIMARY_COLOR = new Color(0, 175, 119);
    private final Color TEXT_DARK = new Color(33, 37, 41);
    private final Color TEXT_GRAY = new Color(108, 117, 125);
    
    public ListKomentarDonatur() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        // Load Data Ulasan
        loadComments();
    }

    private void loadComments() {
        // 1. Bersihkan panel
        panelListUlasan.removeAll();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        CommentDAO dao = new CommentDAO();
        
        // Ambil data dari database
        List<Comment> list = dao.getCommentsByDonor(currentUser.getUserId());

        // 2. Cek jika kosong
        if (list.isEmpty()) {
            panelListUlasan.setLayout(new BorderLayout());
            JLabel emptyLabel = new JLabel("Belum ada ulasan masuk.");
            emptyLabel.setFont(new Font("Lufga", Font.ITALIC, 14));
            emptyLabel.setForeground(Color.GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panelListUlasan.add(emptyLabel, BorderLayout.CENTER);
            
        } else {
            // 3. Jika ada isi, gunakan BoxLayout Y
            panelListUlasan.setLayout(new javax.swing.BoxLayout(panelListUlasan, javax.swing.BoxLayout.Y_AXIS));

            for (Comment c : list) {
                
                // === SETUP DATA ===
                String recipient = c.getRecipientName();
                String food = "Menu: " + c.getFoodName();
                String date = dateFormat.format(c.getDate());
                String content = "<html><div style='width:220px'>" + c.getCommentText() + "</div></html>"; 
                String ratingStr = "⭐".repeat(c.getRating()); 

                    // === SETUP CARD ===
                    JPanel card = new JPanel(new BorderLayout()); 
                    card.setBackground(Color.WHITE);

                    // 1. HAPUS PADDING DI SINI (Cukup garis pinggir saja)
                    card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

                    // 2. BUAT STRIP HIJAU (KIRI)
                    JPanel greenStrip = new JPanel();
                    greenStrip.setBackground(PRIMARY_COLOR);
                    greenStrip.setPreferredSize(new Dimension(10, 0)); 

                    // 3. BUAT PANEL KONTEN 
                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.Y_AXIS));
                    contentPanel.setBackground(Color.WHITE);

                    // 4. PINDAHKAN PADDING KE SINI
                    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setBackground(Color.WHITE);
                
                JLabel lblName = new JLabel(recipient);
                lblName.setFont(new Font("Lufga", Font.BOLD, 14));
                lblName.setForeground(TEXT_DARK);
                
                JLabel lblDate = new JLabel(date);
                lblDate.setFont(new Font("Lufga", Font.PLAIN, 11));
                lblDate.setForeground(TEXT_GRAY);
                
                topPanel.add(lblName, BorderLayout.WEST);
                topPanel.add(lblDate, BorderLayout.EAST);

                JPanel midPanel = new JPanel(new GridLayout(2, 1));
                midPanel.setBackground(Color.WHITE);
                midPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0)); // Jarak atas bawah
                
                JLabel lblFood = new JLabel(food);
                lblFood.setFont(new Font("Lufga", Font.BOLD, 12));
                lblFood.setForeground(PRIMARY_COLOR);
                
                JLabel lblRating = new JLabel(ratingStr);
                lblRating.setForeground(new Color(255, 193, 7)); // Warna Kuning Emas
                
                midPanel.add(lblFood);
                midPanel.add(lblRating);

                JLabel lblContent = new JLabel(content);
                lblContent.setFont(new Font("Lufga", Font.PLAIN, 13));
                lblContent.setForeground(new Color(80, 80, 80));

                contentPanel.add(topPanel);
                contentPanel.add(midPanel);
                contentPanel.add(lblContent);

                card.add(greenStrip, BorderLayout.WEST);    
                card.add(contentPanel, BorderLayout.CENTER);

                panelListUlasan.add(card);
                panelListUlasan.add(Box.createVerticalStrut(15)); 
            }
        }
        
        panelListUlasan.revalidate();
        panelListUlasan.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Heading = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        scrollPaneUlasan = new javax.swing.JScrollPane();
        panelListUlasan = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heading.setFont(new java.awt.Font("Lufga", 1, 22)); // NOI18N
        Heading.setForeground(new java.awt.Color(0, 136, 113));
        Heading.setText("Ulasan Masuk");
        getContentPane().add(Heading, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnBack.setText("jLabel1");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 30, 30));

        scrollPaneUlasan.setBorder(null);
        scrollPaneUlasan.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneUlasan.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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

        scrollPaneUlasan.setViewportView(panelListUlasan);

        getContentPane().add(scrollPaneUlasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 280, 430));

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
        java.awt.EventQueue.invokeLater(() -> new ListKomentarDonatur().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel btnBack;
    private javax.swing.JPanel panelListUlasan;
    private javax.swing.JScrollPane scrollPaneUlasan;
    // End of variables declaration//GEN-END:variables
}
