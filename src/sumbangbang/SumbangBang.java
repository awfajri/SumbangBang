/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sumbangbang;

import model.User; // Pastikan import class User kamu
import view.SplashScreen; // Asumsi kamu memanggil SplashScreen dulu

public class SumbangBang {

    // VARIABEL PENTING: Untuk menyimpan sesi login
    public static User loggedInUser = null; 

    public static void main(String[] args) {
        // Kode main kamu, misal:
        new SplashScreen().setVisible(true);
    }
    
}
