/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import config.DatabaseConfig;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig db = DatabaseConfig.getInstance();
        Connection conn = db.getConnection();
        
        if (conn != null) {
            System.out.println("✅ Database connected!");
        } else {
            System.out.println("❌ Connection failed!");
        }
    }
}
