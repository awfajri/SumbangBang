/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class FoodDonation {
    private String donationId;
    private String donorId;
    private String donorName; 
    private String foodName;
    private int quantity;
    private Date expiryDate;
    private String status; 
    private String pickupLocation;
    private String description;
    private Timestamp createdAt;
    
    // Constructors
    public FoodDonation() {}
    
    public FoodDonation(String donationId, String donorId, String foodName, 
                       int quantity, Date expiryDate, String pickupLocation) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.status = "AVAILABLE";
        this.pickupLocation = pickupLocation;
    }
    
    // Business Methods
    public void updateStatus(String newStatus) {
        if (newStatus.equals("AVAILABLE") || newStatus.equals("RESERVED") || newStatus.equals("TAKEN")) {
            this.status = newStatus;
            System.out.println("Status updated to: " + newStatus);
        } else {
            System.out.println("Invalid status!");
        }
    }
    
    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        LocalDate expiry = expiryDate.toLocalDate();
        return expiry.isBefore(today);
    }
    
    public boolean isAvailable() {
        return status.equals("AVAILABLE") && !isExpired();
    }
    
    public String getDetails() {
        return String.format("Donation: %s | Qty: %d | Exp: %s | Status: %s | Location: %s",
                foodName, quantity, expiryDate, status, pickupLocation);
    }
    
    public int getDaysUntilExpiry() {
        LocalDate today = LocalDate.now();
        LocalDate expiry = expiryDate.toLocalDate();
        return (int) java.time.temporal.ChronoUnit.DAYS.between(today, expiry);
    }
    
    // Getters and Setters
    public String getDonationId() { return donationId; }
    public void setDonationId(String donationId) { this.donationId = donationId; }
    
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }
    
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    
    @Override
    public String toString() {
        return "FoodDonation{" +
                "donationId='" + donationId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
