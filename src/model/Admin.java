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
        return null; // Implementation di DAO layer
    }
    
    public List<Reservation> viewAllReservations() {
        System.out.println("Fetching all reservations...");
        return null; // Implementation di DAO layer
    }
    
    public List<User> viewAllUsers() {
        System.out.println("Fetching all users...");
        return null; // Implementation di DAO layer
    }
    
    public boolean deleteData(String type, String id) {
        System.out.println("Deleting " + type + " with ID: " + id);
        return true; // Implementation di DAO layer
    }
    
    public String generateReport() {
        System.out.println("Generating system report...");
        return "Report generated successfully.";
    }
}
