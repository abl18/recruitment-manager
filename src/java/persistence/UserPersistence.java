/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import database.DatabaseManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.User;

/**
 *
 * @author florian.brosig
 */
public class UserPersistence {
    
    DatabaseManager dbm = new DatabaseManager();
    
    public List<User> getUserList() {
        // get all User...
        return null;
    }
    
    public User getUserFromId(int id) {
        // get user from id... 
        
        return null;
    }
    
    public void save (User user) {
        try {
            PreparedStatement ps = dbm.getPPST("INSERT INTO USER (FIRSTNAME_ID, LASTNAME_ID, ISMALE, ISADMIN, ISACTIVE) VALUES (?,?,?,?,?)");
            
            // TODO!!! 
            // email speichern,
            // loginname speichern,
            // password hashen und dann speichern!!!
            
            ps.setInt(1, getNameId(user.firstname));
            ps.setInt(2, getNameId(user.lastname));
            ps.setBoolean(3, user.isMale);
            ps.setBoolean(4, user.isAdmin);
            ps.setBoolean(5, user.isActive);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Integer getNameId(String name) {
        try {
            PreparedStatement ps = dbm.getPPST("SELECT * FROM NAME WHERE NAME = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("ID");
                if (rs.wasNull()) {
                    id = null;
                } else {
                    return id;
                }
            }
            System.out.println("saving... " + name);
            return saveName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer saveName(String name) {
        try {
            PreparedStatement ps2 = dbm.getPPST("INSERT INTO NAME (NAME) VALUES (?)");
            ps2.setString(1, name);
            ps2.executeUpdate();
            ResultSet rs2 = ps2.getGeneratedKeys();
            while(rs2.next()) {
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
