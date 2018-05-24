/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import database.DatabaseManager;
import model.User;
import persistence.UserPersistence;

/**
 *
 * @author Udo Krüger
 */
public class Main {
    public static void main(String[] args) {
        Main main  = new Main();
        main.start();
    }
    
    public void start() {
        System.out.println("HELLO WORLD!");
        
        User user = new User(); // Wir bauen das Object User.
        user.firstname = "Florian";
        user.lastname = "Brosig";
        user.email = "florian.brosig@bofestconsult.com";
        user.loginname = "florian.brosig";
        user.passwordHash = "13371337";
        user.isActive = true;
        user.isAdmin = true;
       
        
        UserPersistence up = new UserPersistence(); // Ich erzeuge eine Persistence mit dem Kontext "User".
        up.save(user); // save... 
    }
}
