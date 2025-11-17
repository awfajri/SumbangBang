/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author User
 */
import config.DatabaseConfig;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;
    
    public UserDAO() {
        this.conn = DatabaseConfig.getInstance().getConnection();
    }
    
    // CREATE - Register new user
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (user_id, name, email, password, phone, address, role, business_type, recipient_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getRole());
            
            if (user instanceof Donatur) {
                stmt.setString(8, ((Donatur) user).getBusinessType());
                stmt.setNull(9, Types.VARCHAR);
            } else if (user instanceof Penerima) {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setString(9, ((Penerima) user).getRecipientType());
            } else {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.VARCHAR);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // READ - Get user by email (for login)
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String role = rs.getString("role");
                
                if (role.equals("DONATUR")) {
                    Donatur donatur = new Donatur();
                    donatur.setUserId(rs.getString("user_id"));
                    donatur.setName(rs.getString("name"));
                    donatur.setEmail(rs.getString("email"));
                    donatur.setPassword(rs.getString("password"));
                    donatur.setPhone(rs.getString("phone"));
                    donatur.setAddress(rs.getString("address"));
                    donatur.setBusinessType(rs.getString("business_type"));
                    return donatur;
                } else if (role.equals("PENERIMA")) {
                    Penerima penerima = new Penerima();
                    penerima.setUserId(rs.getString("user_id"));
                    penerima.setName(rs.getString("name"));
                    penerima.setEmail(rs.getString("email"));
                    penerima.setPassword(rs.getString("password"));
                    penerima.setPhone(rs.getString("phone"));
                    penerima.setAddress(rs.getString("address"));
                    penerima.setRecipientType(rs.getString("recipient_type"));
                    return penerima;
                } else if (role.equals("ADMIN")) {
                    Admin admin = new Admin();
                    admin.setUserId(rs.getString("user_id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPassword(rs.getString("password"));
                    admin.setPhone(rs.getString("phone"));
                    admin.setAddress(rs.getString("address"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Generate new User ID
    public String generateUserId() {
        String sql = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1";
        
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String lastId = rs.getString("user_id");
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("USR%03d", num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "USR001";
    }
}
