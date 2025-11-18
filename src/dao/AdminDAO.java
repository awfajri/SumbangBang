package dao;

import config.DatabaseConfig;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AdminDAO {
    private Connection conn;

    public AdminDAO() {
        this.conn = DatabaseConfig.getInstance().getConnection();
    }

    // --- data 3 card di main page
    
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

    // --- aktivitas terbaru untuk tabel dashboardAdmin
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
    
    // --- page semua reservasi
    public DefaultTableModel getAllReservations() {
        String[] columns = {"ID", "Makanan", "Penerima", "Tgl Reservasi", "Status", "Kode Pickup"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Query Join: Reservations + Food Donations + Users (Penerima)
        String sql = "SELECT r.reservation_id, f.food_name, u.name AS recipient_name, " +
                     "r.reservation_time, r.status, r.pickup_code " +
                     "FROM reservations r " +
                     "JOIN food_donations f ON r.donation_id = f.donation_id " +
                     "JOIN users u ON r.recipient_id = u.user_id " +
                     "ORDER BY r.reservation_time DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("reservation_id"),
                    rs.getString("food_name"),
                    rs.getString("recipient_name"),
                    rs.getString("reservation_time"),
                    rs.getString("status"),
                    rs.getString("pickup_code")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all reservations: " + e.getMessage());
        }
        return model;
    }
    
    // --- page semua komen
    public DefaultTableModel getAllComments() {
        String[] columns = {"ID", "Penerima", "Donatur", "Rating", "Isi Komentar", "Tanggal"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Query JOIN tabel comments dengan users (untuk nama penerima & donatur)
        String sql = "SELECT c.comment_id, u_r.name AS recipient_name, u_d.name AS donor_name, " +
                     "c.rating, c.comment_text, c.comment_date " +
                     "FROM comments c " +
                     "JOIN users u_r ON c.recipient_id = u_r.user_id " +
                     "JOIN users u_d ON c.donor_id = u_d.user_id " +
                     "ORDER BY c.comment_date DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("comment_id"),
                    rs.getString("recipient_name"),
                    rs.getString("donor_name"),
                    rs.getInt("rating") + "/5", // Format rating misal "5/5"
                    rs.getString("comment_text"),
                    rs.getString("comment_date")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error fetching comments: " + e.getMessage());
        }
        return model;
    }
    
    // --- page semua donasi
    public DefaultTableModel getAllDonations() {
        String[] columns = {"ID Donasi", "Nama Makanan", "Donatur", "Jumlah", "Expired", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Query JOIN tabel food_donations dengan users (untuk nama donatur)
        String sql = "SELECT f.donation_id, f.food_name, u.name AS donor_name, " +
                     "f.quantity, f.expiry_date, f.status " +
                     "FROM food_donations f " +
                     "JOIN users u ON f.donor_id = u.user_id " +
                     "ORDER BY f.created_at DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("donation_id"),
                    rs.getString("food_name"),
                    rs.getString("donor_name"),
                    rs.getInt("quantity") + " Porsi",
                    rs.getDate("expiry_date"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all donations: " + e.getMessage());
        }
        return model;
    }
    
    // --- page semua user
    public DefaultTableModel getAllUsers() {
        String[] columns = {"ID User", "Nama", "Email", "No. HP", "Role", "Tipe/Kategori"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Menggunakan COALESCE untuk mengambil tipe bisnis ATAU tipe penerima (yang tidak null)
        String sql = "SELECT user_id, name, email, phone, role, " +
                     "COALESCE(business_type, recipient_type, '-') AS specific_type " +
                     "FROM users " +
                     "ORDER BY created_at DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("role"),
                    rs.getString("specific_type") // Akan berisi 'Restaurant', 'Panti Asuhan', atau '-'
                });
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all users: " + e.getMessage());
        }
        return model;
    }
    // --- BAGIAN CRUD: DELETE METHODS ---

    // 1. Hapus Reservasi
    public boolean deleteReservation(String id) {
        return executeDelete("DELETE FROM reservations WHERE reservation_id = ?", id);
    }

    // 2. Hapus Komentar
    public boolean deleteComment(String id) {
        return executeDelete("DELETE FROM comments WHERE comment_id = ?", id);
    }

    // 3. Hapus Donasi
    public boolean deleteDonation(String id) {
        return executeDelete("DELETE FROM food_donations WHERE donation_id = ?", id);
    }

    // 4. Hapus User
    public boolean deleteUser(String id) {
        return executeDelete("DELETE FROM users WHERE user_id = ?", id);
    }

    // Helper Method biar gak nulis try-catch berulang kali
    private boolean executeDelete(String query, String id) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting data: " + e.getMessage());
            return false;
        }
    }
    
    // --- BAGIAN CRUD: UPDATE METHODS ---
    // 1. Update Data Donasi (Nama Makanan & Jumlah)
    public boolean updateDonation(String id, String foodName, int quantity) {
        String sql = "UPDATE food_donations SET food_name = ?, quantity = ? WHERE donation_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodName);
            stmt.setInt(2, quantity);
            stmt.setString(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating donation: " + e.getMessage());
            return false;
        }
    }

    // 2. Update Data User (Nama & No HP)
    public boolean updateUser(String id, String name, String phone) {
        String sql = "UPDATE users SET name = ?, phone = ? WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }
    
    // 3. Update Status Reservasi (Opsional, jika admin mau ubah status manual)
    public boolean updateReservationStatus(String id, String status) {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
            return false;
        }
    }
  
    // 4. Update Komentar (Isi & Rating)
    public boolean updateComment(String id, String text, int rating) {
        String sql = "UPDATE comments SET comment_text = ?, rating = ? WHERE comment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, text);
            stmt.setInt(2, rating);
            stmt.setString(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating comment: " + e.getMessage());
            return false;
        }
    }
    // --- BAGIAN CRUD: CREATE METHODS ---

    // Helper untuk Generate ID Otomatis (USR004, DON005, dst)
    public String generateId(String prefix, String tableName, String colName) {
        String sql = "SELECT " + colName + " FROM " + tableName + " ORDER BY " + colName + " DESC LIMIT 1";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String lastId = rs.getString(1);
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("%s%03d", prefix, num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // Default jika kosong
    }

    // 1. Tambah User Baru
    public boolean createUser(String name, String email, String passHash, String phone, String role, String address) {
        String newId = generateId("USR", "users", "user_id");
        String sql = "INSERT INTO users (user_id, name, email, password, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, passHash);
            stmt.setString(5, phone);
            stmt.setString(6, address);
            stmt.setString(7, role);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    // 2. Tambah Donasi Baru
    public boolean createDonation(String donorId, String foodName, int qty, java.sql.Date expDate, String loc) {
        String newId = generateId("DON", "food_donations", "donation_id");
        String sql = "INSERT INTO food_donations (donation_id, donor_id, food_name, quantity, expiry_date, pickup_location, status) VALUES (?, ?, ?, ?, ?, ?, 'AVAILABLE')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newId);
            stmt.setString(2, donorId);
            stmt.setString(3, foodName);
            stmt.setInt(4, qty);
            stmt.setDate(5, expDate);
            stmt.setString(6, loc);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creating donation: " + e.getMessage());
            return false;
        }
    }

    // 3. Tambah Reservasi (Menggunakan Stored Procedure yang sudah kita buat di DB)
    public boolean createReservation(String donationId, String recipientId) {
        String sql = "{CALL sp_create_reservation(?, ?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, donationId);
            stmt.setString(2, recipientId);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
            return false;
        }
    }

    // 4. Tambah Komentar
    public boolean createComment(String resId, String recipientId, String donorId, String text, int rating) {
        String newId = generateId("COM", "comments", "comment_id");
        String sql = "INSERT INTO comments (comment_id, reservation_id, recipient_id, donor_id, comment_text, rating) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newId);
            stmt.setString(2, resId);
            stmt.setString(3, recipientId);
            stmt.setString(4, donorId);
            stmt.setString(5, text);
            stmt.setInt(6, rating);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creating comment: " + e.getMessage());
            return false;
        }
    }
}