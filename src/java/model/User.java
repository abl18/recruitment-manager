/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Udo Krüger
 */
public class User {
    public int id;
    
    public String lastname;
    public String firstname;
    
    public String email;
    public String loginname;
    public String passwordHash;
    
    public boolean isAdmin;
    public boolean isActive;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email + ", loginname=" + loginname + ", passwordHash=" + passwordHash + ", isAdmin=" + isAdmin + ", isActive=" + isActive + '}';
    }

    public User() {
    }
    
    public User(int id, String lastname, String firstname, String email, String loginname, String passwordHash, boolean isAdmin, boolean isActive) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.loginname = loginname;
        this.passwordHash = passwordHash;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }
    
    
}
