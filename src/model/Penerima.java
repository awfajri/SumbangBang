/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;

public class Penerima extends User {
    private String recipientType;
    private List<Reservation> myReservations;
    
    public Penerima() {
        super();
        this.role = "PENERIMA";
        this.myReservations = new ArrayList<>();
    }
    
    public Penerima(String userId, String name, String email, String password,
                    String phone, String address, String recipientType) {
        super(userId, name, email, password, phone, address, "PENERIMA");
        this.recipientType = recipientType;
        this.myReservations = new ArrayList<>();
    }
    
    @Override
    public boolean login() {
        System.out.println("Penerima " + name + " logged in.");
        return true;
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("=== RECIPIENT DASHBOARD ===");
        System.out.println("Welcome, " + name + "!");
        System.out.println("Type: " + recipientType);
        System.out.println("Active Reservations: " + myReservations.size());
    }
    
    // Business Methods
    public List<FoodDonation> searchDonations(String keyword) {
        // Implementation akan di DAO layer
        System.out.println("Searching donations with keyword: " + keyword);
        return new ArrayList<>();
    }
    
    public boolean bookDonation(Reservation reservation) {
        myReservations.add(reservation);
        System.out.println("✅ Donation booked successfully!");
        return true;
    }
    
    public List<Reservation> viewMyReservations() {
        return myReservations;
    }
    
    public boolean confirmPickup(String reservationId, String pickupCode) {
        for (Reservation r : myReservations) {
            if (r.getReservationId().equals(reservationId)) {
                if (r.getPickupCode().equals(pickupCode)) {
                    r.setStatus("COMPLETED");
                    System.out.println("✅ Pickup confirmed!");
                    return true;
                } else {
                    System.out.println("❌ Invalid pickup code.");
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean giveComment(Comment comment) {
        System.out.println("✅ Comment submitted: " + comment.getCommentText());
        return true;
    }
    
    // Getters and Setters
    public String getRecipientType() { return recipientType; }
    public void setRecipientType(String recipientType) { this.recipientType = recipientType; }
    
    public List<Reservation> getMyReservations() { return myReservations; }
    public void setMyReservations(List<Reservation> myReservations) { this.myReservations = myReservations; }
}
