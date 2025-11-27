/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.util.List;

public class Admin extends User {
    
    public Admin() {
        super();
        this.role = "ADMIN";
    }
    
    public Admin(String userId, String name, String email, String password,
                 String phone, String address) {
        super(userId, name, email, password, phone, address, "ADMIN");
    }
    
    @Override
    public boolean login() {
        System.out.println("Admin " + name + " logged in.");
        return true;
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("=== ADMIN DASHBOARD ===");
        System.out.println("Welcome, Administrator " + name + "!");
        System.out.println("System Management Access");
    }
    
    // Admin Methods
    public List<FoodDonation> viewAllDonations() {
        System.out.println("Fetching all donations...");
        return null; 
    }
    
    public List<Reservation> viewAllReservations() {
        System.out.println("Fetching all reservations...");
        return null; 
    }
    
    public List<User> viewAllUsers() {
        System.out.println("Fetching all users...");
        return null; 
    }
    
    public boolean deleteData(String type, String id) {
        System.out.println("Deleting " + type + " with ID: " + id);
        return true; 
    }
    
    public String generateReport() {
        System.out.println("Generating system report...");
        return "Report generated successfully.";
    }
}
