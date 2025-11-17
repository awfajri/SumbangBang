package dao;

import config.DatabaseConfig;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AdminDAO {
    private Connection conn;

    public AdminDAO() {
        this.conn = DatabaseConfig.getInstance().getConnection();
    }

    // --- BAGIAN 1: MENGHITUNG TOTAL UNTUK KARTU STATISTIK ---
    
    public int getTotalDonations() {
        return getCount("SELECT COUNT(*) AS total FROM food_donations");
    }

    public int getTotalReservations() {
        return getCount("SELECT COUNT(*) AS total FROM reservations");
    }

    public int getTotalUsers() {
        return getCount("SELECT COUNT(*) AS total FROM users");
    }

    private int getCount(String query) {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error counting data: " + e.getMessage());
        }
        return 0;
    }

    // --- BAGIAN 2: MENGAMBIL AKTIVITAS TERBARU UNTUK TABEL ---
    // Kita ambil data 10 donasi terbaru yang diposting
    
    public DefaultTableModel getRecentActivities() {
        // Kolom sesuai desain tabel kamu
        String[] columns = {"Waktu", "Aktivitas", "User", "Status", "Aksi"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT f.created_at, f.food_name, u.name AS donor_name, f.status " +
                     "FROM food_donations f " +
                     "JOIN users u ON f.donor_id = u.user_id " +
                     "ORDER BY f.created_at DESC LIMIT 10";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Ambil data per baris
                String rawTime = rs.getString("created_at");
                String time = rawTime.substring(11, 16); // Ambil jam:menit saja (YYYY-MM-DD HH:MM:SS)
                String activity = "Donasi " + rs.getString("food_name");
                String user = rs.getString("donor_name");
                String status = rs.getString("status");
                
                // Masukkan ke model tabel
                model.addRow(new Object[]{time, activity, user, status, "Lihat"});
            }
        } catch (SQLException e) {
            System.err.println("Error fetching activities: " + e.getMessage());
        }
        return model;
    }
}