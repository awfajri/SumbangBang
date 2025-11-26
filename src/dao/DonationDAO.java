/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author User
 */
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import config.DatabaseConfig;
import model.FoodDonation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDAO {
    private Connection conn;
    
    public DonationDAO() {
        this.conn = DatabaseConfig.getInstance().getConnection();
    }
    
    // CREATE - Post donation
    public boolean insertDonation(FoodDonation donation) {
        String sql = "INSERT INTO food_donations (donation_id, donor_id, food_name, quantity, expiry_date, status, pickup_location, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donation.getDonationId());
            stmt.setString(2, donation.getDonorId());
            stmt.setString(3, donation.getFoodName());
            stmt.setInt(4, donation.getQuantity());
            stmt.setDate(5, donation.getExpiryDate());
            stmt.setString(6, donation.getStatus());
            stmt.setString(7, donation.getPickupLocation());
            stmt.setString(8, donation.getDescription());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // READ - Get all available donations
    public List<FoodDonation> getAvailableDonations() {
        List<FoodDonation> donations = new ArrayList<>();
        String sql = "SELECT fd.*, u.name as donor_name FROM food_donations fd " +
                     "JOIN users u ON fd.donor_id = u.user_id " +
                     "WHERE fd.status = 'AVAILABLE' AND fd.expiry_date >= CURDATE() " +
                     "ORDER BY fd.created_at DESC";
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FoodDonation donation = mapResultSetToDonation(rs);
                donations.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donations;
    }
    
    // READ - Get donations by donor
    public List<FoodDonation> getDonationsByDonor(String donorId) {
        List<FoodDonation> donations = new ArrayList<>();
        String sql = "SELECT * FROM food_donations WHERE donor_id = ? ORDER BY created_at DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donorId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                FoodDonation donation = mapResultSetToDonation(rs);
                donations.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donations;
    }
    
    // UPDATE - Update donation
    public boolean updateDonation(FoodDonation donation) {
        String sql = "UPDATE food_donations SET food_name = ?, quantity = ?, expiry_date = ?, pickup_location = ?, description = ? WHERE donation_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donation.getFoodName());
            stmt.setInt(2, donation.getQuantity());
            stmt.setDate(3, donation.getExpiryDate());
            stmt.setString(4, donation.getPickupLocation());
            stmt.setString(5, donation.getDescription());
            stmt.setString(6, donation.getDonationId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // UPDATE - Update status
    public boolean updateStatus(String donationId, String status) {
        String sql = "UPDATE food_donations SET status = ? WHERE donation_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, donationId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // DELETE
    public boolean deleteDonation(String donationId) {
        String sql = "DELETE FROM food_donations WHERE donation_id = ? AND status = 'AVAILABLE'";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Helper method
    private FoodDonation mapResultSetToDonation(ResultSet rs) throws SQLException {
        FoodDonation donation = new FoodDonation();
        donation.setDonationId(rs.getString("donation_id"));
        donation.setDonorId(rs.getString("donor_id"));
        donation.setFoodName(rs.getString("food_name"));
        donation.setQuantity(rs.getInt("quantity"));
        donation.setExpiryDate(rs.getDate("expiry_date"));
        donation.setStatus(rs.getString("status"));
        donation.setPickupLocation(rs.getString("pickup_location"));
        donation.setDescription(rs.getString("description"));
        donation.setCreatedAt(rs.getTimestamp("created_at"));
        
        try {
            donation.setDonorName(rs.getString("donor_name"));
        } catch (SQLException e) {
            // donor_name not in query
        }
        
        return donation;
    }
    
    // Generate new Donation ID
    public String generateDonationId() {
        String sql = "SELECT donation_id FROM food_donations ORDER BY donation_id DESC LIMIT 1";
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String lastId = rs.getString("donation_id");
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("DON%03d", num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "DON001";
    }
    
    public FoodDonation getDonationById(String donationId) {
    String sql = "SELECT * FROM food_donations WHERE donation_id = ?";
    FoodDonation donation = null;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, donationId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Kita pakai helper method yang sudah kamu punya
            donation = mapResultSetToDonation(rs); 
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return donation;
}
    
    public List<FoodDonation> searchDonations(String keyword) {
        List<FoodDonation> list = new ArrayList<>();
        
        // Query SQL menggunakan LIKE %...% untuk pencarian mirip
        String sql = "SELECT fd.*, u.name as donor_name FROM food_donations fd " +
                     "JOIN users u ON fd.donor_id = u.user_id " +
                     "WHERE fd.status = 'AVAILABLE' AND fd.expiry_date >= CURDATE() " +
                     "AND (fd.food_name LIKE ? OR u.name LIKE ?) " + 
                     "ORDER BY fd.created_at DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchKey = "%" + keyword + "%"; // Tambahkan % di kiri kanan
            stmt.setString(1, searchKey);
            stmt.setString(2, searchKey);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToDonation(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Return: Kode Pickup (jika sukses) atau NULL (jika gagal)
    public String bookDonation(String donationId, String recipientId, int quantityTaken) {
        String sql = "{CALL sp_create_reservation(?, ?, ?, ?, ?, ?)}";
        
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            // Input
            stmt.setString(1, donationId);
            stmt.setString(2, recipientId);
            stmt.setInt(3, quantityTaken);
            
            // Output
            stmt.registerOutParameter(4, Types.VARCHAR); // p_reservation_id
            stmt.registerOutParameter(5, Types.VARCHAR); // p_pickup_code
            stmt.registerOutParameter(6, Types.VARCHAR); // p_status_message
            
            stmt.execute();
            
            String statusMessage = stmt.getString(6);
            
            if ("SUCCESS".equalsIgnoreCase(statusMessage)) {
                String pickupCode = stmt.getString(5);
                System.out.println("✅ Booking Success! Code: " + pickupCode);
                return pickupCode; // KEMBALIKAN KODE UNIK
            } else {
                System.err.println("❌ Booking Failed: " + statusMessage);
                return null; // Gagal
            }
            
        } catch (SQLException e) {
            System.err.println("Error booking donation: " + e.getMessage());
            return null;
        }
    }
    // --- BAGIAN KOMENTAR ---

    // 1. Ambil Data Reservasi berdasarkan Kode Pickup (Untuk form komentar)
    // Return array string: [reservation_id, donor_id, food_name]
    public String[] getReservationDetailByCode(String pickupCode) {
        String sql = "SELECT r.reservation_id, fd.donor_id, fd.food_name " +
                     "FROM reservations r " +
                     "JOIN food_donations fd ON r.donation_id = fd.donation_id " +
                     "WHERE r.pickup_code = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pickupCode);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new String[] {
                    rs.getString("reservation_id"),
                    rs.getString("donor_id"),
                    rs.getString("food_name")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Tidak ketemu
    }

    // 2. Simpan Komentar Baru
    public boolean insertComment(String resId, String recipientId, String donorId, String text, int rating) {
        // Generate ID Komentar (COMxxx)
        String commentId = "COM" + System.currentTimeMillis(); // Cara cepat generate ID unik
        
        // Atau gunakan logic generateId() kalau mau urut (bisa copas dari AdminDAO kalau perlu)
        // Disini kita pakai timestamp biar praktis & unik
        
        String sql = "INSERT INTO comments (comment_id, reservation_id, recipient_id, donor_id, comment_text, rating) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, commentId);
            stmt.setString(2, resId);
            stmt.setString(3, recipientId);
            stmt.setString(4, donorId);
            stmt.setString(5, text);
            stmt.setInt(6, rating);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting comment: " + e.getMessage());
            return false;
        }
    }
    // ==========================================
    // BAGIAN STATISTIK DONATUR (Hilang saat Merge)
    // ==========================================

    // 1. Hitung Total Donasi Saya
    public int getTotalDonationsByDonor(String donorId) {
        String sql = "SELECT COUNT(*) AS total FROM food_donations WHERE donor_id = ?";
        return getCountByDonor(sql, donorId);
    }

    // 2. Hitung Total Porsi Saya
    public int getTotalPortionsByDonor(String donorId) {
        String sql = "SELECT SUM(quantity) AS total FROM food_donations WHERE donor_id = ?";
        return getCountByDonor(sql, donorId);
    }

    // 3. Hitung Total Reservasi Masuk (Donasi saya yang dibooking orang)
    public int getTotalReservationsReceived(String donorId) {
        String sql = "SELECT COUNT(*) AS total FROM reservations r " +
                     "JOIN food_donations fd ON r.donation_id = fd.donation_id " +
                     "WHERE fd.donor_id = ?";
        return getCountByDonor(sql, donorId);
    }

    // Helper Method untuk Hitung
    private int getCountByDonor(String sql, String donorId) {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
