/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConfig;
import model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    
    private Connection conn;

    public CommentDAO() {
        this.conn = DatabaseConfig.getInstance().getConnection();
    }

    public List<Comment> getCommentsByDonor(String donorId) {
        List<Comment> list = new ArrayList<>();
        
        // Query JOIN untuk mendapatkan Nama Penerima dan Nama Makanan
        String sql = "SELECT c.comment_text, c.rating, c.comment_date, " +
                     "u.name AS recipient_name, fd.food_name " +
                     "FROM comments c " +
                     "JOIN users u ON c.recipient_id = u.user_id " +
                     "JOIN reservations r ON c.reservation_id = r.reservation_id " +
                     "JOIN food_donations fd ON r.donation_id = fd.donation_id " +
                     "WHERE c.donor_id = ? " +
                     "ORDER BY c.comment_date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, donorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentText(rs.getString("comment_text"));
                c.setRating(rs.getInt("rating"));
                c.setDate(rs.getDate("comment_date"));
                c.setRecipientName(rs.getString("recipient_name"));
                c.setFoodName(rs.getString("food_name"));
                
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
