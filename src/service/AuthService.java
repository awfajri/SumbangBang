/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author User
 */
import dao.UserDAO;
import model.*;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private UserDAO userDAO;
    
    public AuthService() {
        this.userDAO = new UserDAO();
    }
    
    // Register
    public boolean register(User user, String plainPassword) {
        // Hash password
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        
        // Generate user ID
        user.setUserId(userDAO.generateUserId());
        
        // Save to database
        return userDAO.insertUser(user);
    }
    
    // Login
    public User login(String email, String plainPassword) {
        User user = userDAO.getUserByEmail(email);
        
        if (user != null) {
            // Verify password
            if (BCrypt.checkpw(plainPassword, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
