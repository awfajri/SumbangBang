/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import dao.DonationDAO;
import model.FoodDonation; // Dari DonationDAO kamu
import model.User;
import sumbangbang.SumbangBang; // Untuk mengambil sesi login
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.Connection; // Diperlukan untuk statistik
import java.sql.PreparedStatement; // Diperlukan untuk statistik
import java.util.List; 
import javax.swing.JPanel; 
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import java.awt.FlowLayout; // Untuk layout card
import java.awt.Dimension; // Untuk ukuran card
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.Box;

public class DashboardDonatur extends javax.swing.JFrame {
    
    // Ambil data user yang sedang login dari variabel statis
    private User currentUser = SumbangBang.loggedInUser;
    private DonationDAO donationDAO;
        
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardDonatur.class.getName());

    public DashboardDonatur() {
        initComponents();

        this.donationDAO = new DonationDAO(); // Inisialisasi DAO

        // Cek jika user tidak ada (misal buka file ini langsung)
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Sesi tidak ditemukan. Harap login ulang.");

            // Tutup dashboard, buka login
            // DIPERBAIKI: new Login() (L besar)
            new login().setVisible(true);
            this.dispose();
            return; // Hentikan eksekusi konstruktor
        }

        // Jika user ada, muat semua data
        loadDashboardData();
    }

    DashboardDonatur(User currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void loadDashboardData() {
        // Set sapaan "Hai, [Nama]"
        labelGreeting.setText("Hai, " + currentUser.getName());
        
        // Muat data statistik
        loadStatistics();

        // Muat daftar donasi
        loadDonationList();
    }
    private void loadStatistics() {
        if (currentUser == null) return;
        
        // Kita gunakan DonationDAO yang sudah punya query lengkap
        String userId = currentUser.getUserId();
        
        try {
            // 1. Ambil data real dari Database via DAO
            int totalDonasi = donationDAO.getTotalDonationsByDonor(userId);
            int totalPorsi = donationDAO.getTotalPortionsByDonor(userId);
            int totalReservasi = donationDAO.getTotalReservationsReceived(userId); // <-- Ini method ajaibnya
            
            // 2. Tampilkan ke UI (Text Field)
            // Pastikan nama variabel (valueTotal...) sesuai dengan Design kamu
            if (valueTotalDonasi != null) valueTotalDonasi.setText(String.valueOf(totalDonasi));
            if (valueTotalPorsi != null) valueTotalPorsi.setText(String.valueOf(totalPorsi));
            if (valueTotalReservasi != null) valueTotalReservasi.setText(String.valueOf(totalReservasi));
            
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat statistik: " + e.getMessage());
        }
    }
    private void loadDonationList() {
    // 1. Bersihkan panel dulu (WAJIB!)
        donationListPanel.removeAll();

        try {
            // 2. Panggil method DAO kamu yang mengembalikan List
            List<FoodDonation> donationList = donationDAO.getDonationsByDonor(currentUser.getUserId());

            // 3. Cek jika daftar donasi KOSONG
            if (donationList.isEmpty()) {
                // Tampilkan pesan kosong
                donationListPanel.setLayout(new BorderLayout());
                JLabel emptyLabel = new JLabel("Kamu belum memiliki donasi.");
                emptyLabel.setFont(new Font("Lufga", Font.ITALIC, 12));
                emptyLabel.setForeground(Color.GRAY);
                emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
                donationListPanel.add(emptyLabel, BorderLayout.CENTER);

            } else {
                // JIKA ADA ISI: Tampilkan card-card
                donationListPanel.setLayout(new javax.swing.BoxLayout(donationListPanel, javax.swing.BoxLayout.Y_AXIS));

                for (FoodDonation donation : donationList) {
                    
                    String foodName = donation.getFoodName();
                    String quantity = donation.getQuantity() + " Porsi";
                    String status = "Status: " + donation.getStatus();
                    String donationId = donation.getDonationId(); 

                    // === 4. LAYOUT CARD (Tumpuk ke Bawah) ===
                    JPanel card = new JPanel();
                    card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
                    card.setBackground(Color.WHITE);
                    
                    // Border untuk padding (jarak di dalam card)
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY), 
                        BorderFactory.createEmptyBorder(10, 10, 5, 10) // Padding
                    ));
                    
                    // Atur ukuran card
                    int panelWidth = donationScrollPane.getWidth();
                    if (panelWidth <= 40) panelWidth = 400; 
                    card.setMaximumSize(new Dimension(panelWidth - 20, Short.MAX_VALUE));
                    card.setMinimumSize(new Dimension(panelWidth - 20, 60)); 

                    // 5. BUAT PANEL TEKS (Bagian ATAS)
                    JPanel infoPanel = new JPanel();
                    infoPanel.setOpaque(false); 
                    infoPanel.setLayout(new javax.swing.BoxLayout(infoPanel, javax.swing.BoxLayout.Y_AXIS));
                    infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 
                    
                    JLabel lblFoodName = new JLabel(foodName);
                    lblFoodName.setFont(new Font("Lufga", Font.BOLD, 14)); 
                    JLabel lblDetails = new JLabel(quantity + " | " + status);
                    lblDetails.setFont(new Font("Lufga", Font.PLAIN, 12));

                    infoPanel.add(lblFoodName);
                    infoPanel.add(Box.createVerticalStrut(5)); 
                    infoPanel.add(lblDetails);
                    
                    // 6. BUAT PANEL TOMBOL (Bagian BAWAH)
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setOpaque(false); 
                    // Rata kanan agar rapi
                    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 
                    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 

                    // --- TOMBOL-TOMBOL ---
                    JButton btnDetail = new JButton("Detail"); // TOMBOL BARU
                    JButton btnEdit = new JButton("Edit");
                    JButton btnHapus = new JButton("Hapus");

                    // 7. WARNA & STYLE TOMBOL
                    
                    // Style Tombol Detail (Biru Muda / Abu Terang)
                    btnDetail.setBackground(new Color(240, 240, 240)); 
                    btnDetail.setForeground(Color.BLACK);
                    btnDetail.setFont(new Font("Lufga", Font.BOLD, 11));
                    
                    // Style Tombol Edit (Hijau)
                    btnEdit.setBackground(new Color(0, 175, 119)); 
                    btnEdit.setForeground(Color.WHITE);
                    btnEdit.setFont(new Font("Lufga", Font.BOLD, 11));

                    // Style Tombol Hapus (Merah)
                    btnHapus.setBackground(new Color(220, 53, 69)); 
                    btnHapus.setForeground(Color.WHITE);
                    btnHapus.setFont(new Font("Lufga", Font.BOLD, 11));
                    
                    // --- LOGIKA TOMBOL ---

                    // 1. Logika Detail (Membuka Frame DetailDonasi)
                    btnDetail.addActionListener(e -> {
                        // Pastikan kamu sudah punya file DetailDonasi.java
                        new DetailDonasi(donationId).setVisible(true);
                    });

                    // 2. Logika Edit
                    btnEdit.addActionListener(e -> {
                        new FormDonatur(DashboardDonatur.this, donationId).setVisible(true);
                    });

                    // 3. Logika Hapus
                    btnHapus.addActionListener(e -> {
                        int Pilihan = JOptionPane.showConfirmDialog(this,
                                "Yakin ingin hapus donasi '" + foodName + "'?",
                                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                        if (Pilihan == JOptionPane.YES_OPTION) {
                            try {
                                boolean isSuccess = donationDAO.deleteDonation(donationId);
                                if (isSuccess) {
                                    JOptionPane.showMessageDialog(this, "Donasi berhasil dihapus.");
                                    loadDashboardData(); // Refresh
                                } else {
                                    JOptionPane.showMessageDialog(this, "Gagal menghapus donasi (Mungkin statusnya 'RESERVED'?).");
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                            }
                        }
                    });
                    
                    // Masukkan tombol ke panel (Urutan: Detail -> Edit -> Hapus)
                    buttonPanel.add(btnDetail);
                    buttonPanel.add(Box.createHorizontalStrut(5)); // Jarak antar tombol
                    buttonPanel.add(btnEdit);
                    buttonPanel.add(Box.createHorizontalStrut(5)); 
                    buttonPanel.add(btnHapus);

                    // 9. MASUKKAN PANEL-PANEL KE CARD
                    card.add(infoPanel); // Teks di atas
                    card.add(Box.createVerticalStrut(10)); // Jarak antara teks dan tombol
                    card.add(buttonPanel); // Tombol di bawah
                    
                    donationListPanel.add(card);
                    donationListPanel.add(Box.createVerticalStrut(8)); // Spasi antar card
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar donasi.");
        }

        // 10. REFRESH TAMPILAN (WAJIB!)
        donationListPanel.revalidate();
        donationListPanel.repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        pnlSideMenu = new java.awt.Panel();
        menuBeranda = new javax.swing.JButton();
        menuKomentar = new javax.swing.JButton();
        menuLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCloseMenu = new javax.swing.JLabel();
        dahboard = new java.awt.Panel();
        Heading = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        btnMenu = new javax.swing.JLabel();
        bg_dashboard = new javax.swing.JLabel();
        card_role = new java.awt.Panel();
        labelGreeting = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        panelPorsi = new java.awt.Panel();
        labelTextPorsi = new javax.swing.JLabel();
        valueTotalPorsi = new javax.swing.JTextField();
        panelReservasi = new java.awt.Panel();
        labelTextReservasi = new javax.swing.JLabel();
        valueTotalReservasi = new javax.swing.JTextField();
        panelDonasi = new java.awt.Panel();
        labelTextDonasi = new javax.swing.JLabel();
        valueTotalDonasi = new javax.swing.JTextField();
        labelRole2 = new javax.swing.JLabel();
        labelRole1 = new javax.swing.JLabel();
        btnTambahDonasi = new java.awt.Button();
        donationScrollPane = new javax.swing.JScrollPane();
        donationListPanel = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSideMenu.setBackground(new java.awt.Color(0, 153, 102));
        pnlSideMenu.setForeground(new java.awt.Color(255, 255, 255));
        pnlSideMenu.setPreferredSize(new java.awt.Dimension(131, 82));
        pnlSideMenu.setVisible(false);

        menuBeranda.setBackground(new java.awt.Color(255, 255, 255));
        menuBeranda.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        menuBeranda.setForeground(new java.awt.Color(0, 102, 102));
        menuBeranda.setText("Beranda");
        menuBeranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBerandaMouseClicked(evt);
            }
        });
        menuBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBerandaActionPerformed(evt);
            }
        });

        menuKomentar.setBackground(new java.awt.Color(255, 255, 255));
        menuKomentar.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        menuKomentar.setForeground(new java.awt.Color(0, 102, 102));
        menuKomentar.setText("Ulasan Masuk");
        menuKomentar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKomentarMouseClicked(evt);
            }
        });
        menuKomentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKomentarActionPerformed(evt);
            }
        });

        menuLogout.setBackground(new java.awt.Color(204, 0, 0));
        menuLogout.setFont(new java.awt.Font("Lufga", 0, 12)); // NOI18N
        menuLogout.setForeground(new java.awt.Color(255, 255, 255));
        menuLogout.setText("Keluar");
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lufga", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Menu");

        btnCloseMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Close.png"))); // NOI18N
        btnCloseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSideMenuLayout = new javax.swing.GroupLayout(pnlSideMenu);
        pnlSideMenu.setLayout(pnlSideMenuLayout);
        pnlSideMenuLayout.setHorizontalGroup(
            pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuKomentar, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(menuBeranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlSideMenuLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCloseMenu)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        pnlSideMenuLayout.setVerticalGroup(
            pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSideMenuLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCloseMenu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuBeranda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuKomentar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                .addComponent(menuLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlSideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 190, 570));

        dahboard.setPreferredSize(new java.awt.Dimension(320, 64));
        dahboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Heading.setFont(new java.awt.Font("Lufga", 1, 20)); // NOI18N
        Heading.setForeground(new java.awt.Color(255, 255, 255));
        Heading.setText("SumbangBang");
        dahboard.add(Heading, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Logo_kicik.png"))); // NOI18N
        dahboard.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Menu Hamburger.png"))); // NOI18N
        btnMenu.setText("jLabel1");
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });
        dahboard.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 30, 30));

        bg_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/bg_dashboard.jpg"))); // NOI18N
        dahboard.add(bg_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        getContentPane().add(dahboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 64));

        card_role.setBackground(new java.awt.Color(0, 175, 119));
        card_role.setName(""); // NOI18N
        card_role.setPreferredSize(new java.awt.Dimension(272, 87));

        labelGreeting.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        labelGreeting.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout card_roleLayout = new javax.swing.GroupLayout(card_role);
        card_role.setLayout(card_roleLayout);
        card_roleLayout.setHorizontalGroup(
            card_roleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_roleLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        card_roleLayout.setVerticalGroup(
            card_roleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_roleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(card_role, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 272, 40));

        panel1.setForeground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPorsi.setBackground(new java.awt.Color(0, 113, 77));
        panelPorsi.setForeground(new java.awt.Color(255, 255, 255));
        panelPorsi.setPreferredSize(new java.awt.Dimension(131, 82));

        labelTextPorsi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        labelTextPorsi.setForeground(new java.awt.Color(255, 255, 255));
        labelTextPorsi.setText("Total Porsi");

        valueTotalPorsi.setEditable(false);
        valueTotalPorsi.setBackground(new java.awt.Color(0, 113, 77));
        valueTotalPorsi.setFont(new java.awt.Font("Lufga", 1, 18)); // NOI18N
        valueTotalPorsi.setForeground(new java.awt.Color(255, 255, 255));
        valueTotalPorsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valueTotalPorsi.setBorder(null);
        valueTotalPorsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueTotalPorsiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPorsiLayout = new javax.swing.GroupLayout(panelPorsi);
        panelPorsi.setLayout(panelPorsiLayout);
        panelPorsiLayout.setHorizontalGroup(
            panelPorsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPorsiLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(panelPorsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPorsiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(valueTotalPorsi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTextPorsi))
                .addContainerGap())
        );
        panelPorsiLayout.setVerticalGroup(
            panelPorsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPorsiLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelTextPorsi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueTotalPorsi, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel1.add(panelPorsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 270, 80));

        panelReservasi.setBackground(new java.awt.Color(0, 113, 77));
        panelReservasi.setForeground(new java.awt.Color(255, 255, 255));
        panelReservasi.setPreferredSize(new java.awt.Dimension(131, 82));

        labelTextReservasi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        labelTextReservasi.setForeground(new java.awt.Color(255, 255, 255));
        labelTextReservasi.setText("Total Reservasi");

        valueTotalReservasi.setEditable(false);
        valueTotalReservasi.setBackground(new java.awt.Color(0, 113, 77));
        valueTotalReservasi.setFont(new java.awt.Font("Lufga", 1, 18)); // NOI18N
        valueTotalReservasi.setForeground(new java.awt.Color(255, 255, 255));
        valueTotalReservasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valueTotalReservasi.setBorder(null);

        javax.swing.GroupLayout panelReservasiLayout = new javax.swing.GroupLayout(panelReservasi);
        panelReservasi.setLayout(panelReservasiLayout);
        panelReservasiLayout.setHorizontalGroup(
            panelReservasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReservasiLayout.createSequentialGroup()
                .addGroup(panelReservasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReservasiLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(valueTotalReservasi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelReservasiLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(labelTextReservasi)))
                .addGap(76, 76, 76))
        );
        panelReservasiLayout.setVerticalGroup(
            panelReservasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReservasiLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(labelTextReservasi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(valueTotalReservasi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panel1.add(panelReservasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 131, -1));

        panelDonasi.setBackground(new java.awt.Color(0, 113, 77));
        panelDonasi.setForeground(new java.awt.Color(255, 255, 255));
        panelDonasi.setPreferredSize(new java.awt.Dimension(131, 82));
        panelDonasi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTextDonasi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        labelTextDonasi.setForeground(new java.awt.Color(255, 255, 255));
        labelTextDonasi.setText("Total Donasi");
        panelDonasi.add(labelTextDonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 5, -1, -1));

        valueTotalDonasi.setEditable(false);
        valueTotalDonasi.setBackground(new java.awt.Color(0, 113, 77));
        valueTotalDonasi.setFont(new java.awt.Font("Lufga", 1, 18)); // NOI18N
        valueTotalDonasi.setForeground(new java.awt.Color(255, 255, 255));
        valueTotalDonasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valueTotalDonasi.setBorder(null);
        panelDonasi.add(valueTotalDonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 30, 40));

        panel1.add(panelDonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 82));

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 272, 174));

        labelRole2.setBackground(new java.awt.Color(0, 113, 77));
        labelRole2.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        labelRole2.setForeground(new java.awt.Color(0, 113, 77));
        labelRole2.setText("Statistik");
        getContentPane().add(labelRole2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        labelRole1.setBackground(new java.awt.Color(0, 113, 77));
        labelRole1.setFont(new java.awt.Font("Lufga", 0, 14)); // NOI18N
        labelRole1.setForeground(new java.awt.Color(0, 113, 77));
        labelRole1.setText("Donasi Terbaru");
        getContentPane().add(labelRole1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        btnTambahDonasi.setBackground(new java.awt.Color(0, 175, 119));
        btnTambahDonasi.setFont(new java.awt.Font("Lufga", 1, 12)); // NOI18N
        btnTambahDonasi.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahDonasi.setLabel("Tambah Donasi");
        btnTambahDonasi.setName(""); // NOI18N
        btnTambahDonasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDonasiActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambahDonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        donationScrollPane.setBackground(new java.awt.Color(204, 204, 204));

        donationListPanel.setBackground(new java.awt.Color(255, 255, 255));
        donationListPanel.setLayout(new javax.swing.BoxLayout(donationListPanel, javax.swing.BoxLayout.Y_AXIS));
        donationScrollPane.setViewportView(donationListPanel);

        getContentPane().add(donationScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 280, 170));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/background.jpg"))); // NOI18N
        bg.setText("jLabel4");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 568));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahDonasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDonasiActionPerformed
        FormDonatur formDonasi = new FormDonatur(this);
        formDonasi.setVisible(true);
    }//GEN-LAST:event_btnTambahDonasiActionPerformed

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        pnlSideMenu.setVisible(true);
    }//GEN-LAST:event_btnMenuMouseClicked

    private void valueTotalPorsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueTotalPorsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valueTotalPorsiActionPerformed

    private void menuBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBerandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuBerandaActionPerformed

    private void menuBerandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBerandaMouseClicked
        // TODO add your handling code here:
        pnlSideMenu.setVisible(false);
        loadStatistics(); // Refresh data statistik
        
    }//GEN-LAST:event_menuBerandaMouseClicked

    private void menuKomentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKomentarActionPerformed
        new ListKomentarDonatur().setVisible(true);
    }//GEN-LAST:event_menuKomentarActionPerformed

    private void menuKomentarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKomentarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menuKomentarMouseClicked

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuLogoutActionPerformed

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "Yakin ingin keluar?", "Logout", javax.swing.JOptionPane.YES_NO_OPTION);
            
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            new login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void btnCloseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMenuMouseClicked
        // TODO add your handling code here:
        pnlSideMenu.setVisible(false);
    }//GEN-LAST:event_btnCloseMenuMouseClicked
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
        java.awt.EventQueue.invokeLater(() -> new DashboardDonatur().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_dashboard;
    private javax.swing.JLabel btnCloseMenu;
    private javax.swing.JLabel btnMenu;
    private java.awt.Button btnTambahDonasi;
    private java.awt.Panel card_role;
    private java.awt.Panel dahboard;
    private javax.swing.JPanel donationListPanel;
    private javax.swing.JScrollPane donationScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelGreeting;
    private javax.swing.JLabel labelRole1;
    private javax.swing.JLabel labelRole2;
    private javax.swing.JLabel labelTextDonasi;
    private javax.swing.JLabel labelTextPorsi;
    private javax.swing.JLabel labelTextReservasi;
    private javax.swing.JButton menuBeranda;
    public javax.swing.JButton menuKomentar;
    private javax.swing.JButton menuLogout;
    private java.awt.Panel panel1;
    private java.awt.Panel panelDonasi;
    private java.awt.Panel panelPorsi;
    private java.awt.Panel panelReservasi;
    private java.awt.Panel pnlSideMenu;
    private javax.swing.JTextField valueTotalDonasi;
    private javax.swing.JTextField valueTotalPorsi;
    private javax.swing.JTextField valueTotalReservasi;
    // End of variables declaration//GEN-END:variables
}