package model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Comment {
    private String commentId;
    private String reservationId;
    private String recipientId;
    private String recipientName; 
    private String donorId;
    private String foodName;
    private String commentText;
    private int rating; 
    
    
    private Date commentDate; 
    
    // Constructor 
    public Comment() {}
    
    // Constructor 
    public Comment(String commentId, String reservationId, String recipientId, 
                   String donorId, String commentText, int rating) {
        this.commentId = commentId;
        this.reservationId = reservationId;
        this.recipientId = recipientId;
        this.donorId = donorId;
        this.commentText = commentText;
        this.rating = rating;
        // Set default tanggal hari ini
        this.commentDate = new Date(System.currentTimeMillis());
    }
    
    // --- Business Methods ---
    public boolean validate() {
        // Cek rating 1-5
        if (rating < 1 || rating > 5) {
            System.out.println("❌ Invalid rating! Must be between 1-5.");
            return false;
        }
        // Cek komentar tidak kosong
        if (commentText == null || commentText.trim().isEmpty()) {
            System.out.println("❌ Comment text cannot be empty!");
            return false;
        }
        return true;
    }
    
    // --- GETTERS AND SETTERS ---
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    
    public String getCommentId() { return commentId; }
    public void setCommentId(String commentId) { this.commentId = commentId; }
    
    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public Date getDate() { 
        return commentDate; 
    }

    public void setDate(Date date) {
        this.commentDate = date;
    }
    
    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", rating=" + rating +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}