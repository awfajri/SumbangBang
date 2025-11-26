/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.sql.Timestamp;

public class Comment {
    private String commentId;
    private String reservationId;
    private String recipientId;
    private String recipientName; // For display
    private String donorId;
    private String foodName;
    private String commentText;
    private int rating; // 1-5
    private Timestamp commentDate;
    
    // Constructors
    public Comment() {}
    
    public Comment(String commentId, String reservationId, String recipientId, 
                   String donorId, String commentText, int rating) {
        this.commentId = commentId;
        this.reservationId = reservationId;
        this.recipientId = recipientId;
        this.donorId = donorId;
        this.commentText = commentText;
        this.rating = rating;
        this.commentDate = new Timestamp(System.currentTimeMillis());
    }
    
    // Business Methods
    public boolean validate() {
        // Check if rating is between 1-5
        if (rating < 1 || rating > 5) {
            System.out.println("❌ Invalid rating! Must be between 1-5.");
            return false;
        }
        
        // Check if comment text is not empty
        if (commentText == null || commentText.trim().isEmpty()) {
            System.out.println("❌ Comment text cannot be empty!");
            return false;
        }
        
        return true;
    }
    
    public String getDetails() {
        return String.format("Comment by %s | Rating: %d/5 | Date: %s\n%s",
                recipientName, rating, commentDate, commentText);
    }
    
    public String getRatingStars() {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars.append("⭐");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }
    
    // Getters and Setters
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
    
    public Timestamp getCommentDate() { return commentDate; }
    public void setCommentDate(Timestamp commentDate) { this.commentDate = commentDate; }
    
    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", rating=" + rating +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
