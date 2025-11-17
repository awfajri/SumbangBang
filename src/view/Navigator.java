/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Lenovo LOQ
 */
public class Navigator {
    private Navigator() {}
    public static void toRegister(java.awt.Window from){
        new register().setVisible(true);
        if (from != null) from.dispose();
    }
    public static void toLogin(java.awt.Window from){
        new login().setVisible(true);
        if (from != null) from.dispose();
    }
    public static void toSplash(java.awt.Window from){
        new SplashScreen().setVisible(true);
        if (from != null) from.dispose();
    }
}
