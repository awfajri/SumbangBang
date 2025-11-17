/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class Donatur extends User {
    private String businessType;
    private List<FoodDonation> myDonations;
    
    public Donatur() {
        super();
        this.role = "DONATUR";
        this.myDonations = new ArrayList<>();
    }
    
    public Donatur(String userId, String name, String email, String password,
                   String phone, String address, String businessType) {
        super(userId, name, email, password, phone, address, "DONATUR");
        this.businessType = businessType;
        this.myDonations = new ArrayList<>();
    }
    
    @Override
    public boolean login() {
        System.out.println("Donatur " + name + " logged in.");
        return true;
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("=== DONOR DASHBOARD ===");
        System.out.println("Welcome, " + name + "!");
        System.out.println("Business: " + businessType);
        System.out.println("Total Donations: " + myDonations.size());
    }
    
    // Business Methods
    public boolean postDonation(FoodDonation donation) {
        myDonations.add(donation);
        System.out.println("✅ Donation posted: " + donation.getFoodName());
        return true;
    }
    
    public List<FoodDonation> viewMyDonations() {
        return myDonations;
    }
    
    public boolean editDonation(String donationId, FoodDonation updatedDonation) {
        for (int i = 0; i < myDonations.size(); i++) {
            if (myDonations.get(i).getDonationId().equals(donationId)) {
                if (myDonations.get(i).getStatus().equals("AVAILABLE")) {
                    myDonations.set(i, updatedDonation);
                    System.out.println("✅ Donation updated successfully.");
                    return true;
                } else {
                    System.out.println("❌ Cannot edit: Donation already reserved/taken.");
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean deleteDonation(String donationId) {
        return myDonations.removeIf(d -> d.getDonationId().equals(donationId));
    }
    
    // Getters and Setters
    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
    
    public List<FoodDonation> getMyDonations() { return myDonations; }
    public void setMyDonations(List<FoodDonation> myDonations) { this.myDonations = myDonations; }
    
}
