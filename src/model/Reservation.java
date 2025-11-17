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
import java.util.Random;

public class Reservation {
    private String reservationId;
    private String donationId;
    private String recipientId;
    private String recipientName; // For display
    private String donorId; // For notification
    private Timestamp reservationTime;
    private String status; // PENDING, CONFIRMED, COMPLETED
    private String pickupCode;
    private Timestamp confirmedAt;
    private Timestamp completedAt;
    
    // For display purposes
    private FoodDonation donation;
    
    // Constructors
    public Reservation() {}
    
    public Reservation(String reservationId, String donationId, String recipientId) {
        this.reservationId = reservationId;
        this.donationId = donationId;
        this.recipientId = recipientId;
        this.status = "PENDING";
        this.pickupCode = generatePickupCode();
        this.reservationTime = new Timestamp(System.currentTimeMillis());
    }
    
    // Business Methods
    public boolean confirmPickup(String inputCode) {
        if (this.pickupCode.equals(inputCode)) {
            this.status = "COMPLETED";
            this.completedAt = new Timestamp(System.currentTimeMillis());
            System.out.println("✅ Pickup confirmed successfully!");
            return true;
        } else {
            System.out.println("❌ Invalid pickup code!");
            return false;
        }
    }
    
    public String generatePickupCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return code.toString();
    }
    
    public boolean cancel() {
        if (status.equals("PENDING")) {
            this.status = "CANCELLED";
            System.out.println("Reservation cancelled.");
            return true;
        }
        System.out.println("Cannot cancel: Reservation already " + status);
        return false;
    }
    
    public boolean complete() {
        this.status = "COMPLETED";
        this.completedAt = new Timestamp(System.currentTimeMillis());
        return true;
    }
    
    public String getDetails() {
        return String.format("Reservation: %s | Status: %s | Code: %s | Time: %s",
                reservationId, status, pickupCode, reservationTime);
    }
    
    public boolean isPending() {
        return status.equals("PENDING");
    }
    
    public boolean isCompleted() {
        return status.equals("COMPLETED");
    }
    
    // Getters and Setters
    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    
    public String getDonationId() { return donationId; }
    public void setDonationId(String donationId) { this.donationId = donationId; }
    
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    
    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }
    
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public Timestamp getReservationTime() { return reservationTime; }
    public void setReservationTime(Timestamp reservationTime) { this.reservationTime = reservationTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPickupCode() { return pickupCode; }
    public void setPickupCode(String pickupCode) { this.pickupCode = pickupCode; }
    
    public Timestamp getConfirmedAt() { return confirmedAt; }
    public void setConfirmedAt(Timestamp confirmedAt) { this.confirmedAt = confirmedAt; }
    
    public Timestamp getCompletedAt() { return completedAt; }
    public void setCompletedAt(Timestamp completedAt) { this.completedAt = completedAt; }
    
    public FoodDonation getDonation() { return donation; }
    public void setDonation(FoodDonation donation) { this.donation = donation; }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", donationId='" + donationId + '\'' +
                ", status='" + status + '\'' +
                ", pickupCode='" + pickupCode + '\'' +
                '}';
    }
}
