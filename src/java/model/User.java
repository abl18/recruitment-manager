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
}
