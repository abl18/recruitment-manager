/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import database.DatabaseManager;
import java.sql.*;
import java.util.List;
import model.User;

/**
 *
 * @author Udo Krüger
 */
public class UserPersistence {
    
    DatabaseManager dbm = new DatabaseManager();
    PreparedStatement ps = null;
    
    public List<User> getUserList() {
        // get all User...
        // sortiert per nachnamen, Hinweis: SELECT * FROM USER ORDER BY NAME z.B.
        try {
            ps = dbm.getPPST("SELECT * FROM USER ORDER BY ID");
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                int id = rs.getInt("ID");
                int lastnameId = rs.getInt("LASTNAME_ID");
                int firstnameId = rs.getInt("FIRSTNAME_ID");
                int emailId = rs.getInt("EMAIL_ID");
                int loginnameId = rs.getInt("LOGINNAME_ID");
                int passwordhashId = rs.getInt("PASSWORDHASH_ID");
                boolean isAdmin = rs.getBoolean("ISADMIN");
                boolean isActive = rs.getBoolean("ISACTIVE");
               
                System.out.println(getUserFromId(lastnameId));
                System.out.println(getUserFromId(firstnameId));
                System.out.println(getEmailFromId(emailId));
            }
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public String getUserFromId(int id){
        // get user from id        
        try {
            ps = dbm.getPPST("SELECT ID, NAME FROM NAME WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {                
                String name = rs.getString("NAME");
                return name;
            }
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    public String getEmailFromId(int id){
        // get email from id        
        try {
            ps = dbm.getPPST("SELECT ID, NAME FROM EMAIL WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {                
                String name = rs.getString("NAME");
                return name;
            }
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public void save (User user) {
        try {
            //dbm.getPPST("truncate table name");
            ps = dbm.getPPST("INSERT INTO USER (LASTNAME_ID, FIRSTNAME_ID, EMAIL_ID, LOGINNAME_ID, PASSWORDHASH_ID, ISADMIN, ISACTIVE,) VALUES (?,?,?,?,?,?,?)");
            
            ps.setInt(1, getNameId(user.lastname));
            ps.setInt(2, getNameId(user.firstname));
            ps.setInt(3, getEmailId(user.email ));
            ps.setInt(4, getLoginNameId(user.loginname));
            ps.setInt(5, getPasswordHashId(user.passwordHash));
            ps.setBoolean(6, user.isAdmin);
            ps.setBoolean(7, user.isActive);
            ps.executeUpdate();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
    }
    
    public Integer getNameId(String name) {
        try {
            ps = dbm.getPPST("SELECT * FROM NAME WHERE NAME = ?");
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
            System.out.println(e.getMessage());       
          }
        
        return null;
    }
    
    public Integer saveName(String name) {
        try {
            ps = dbm.getPPST("INSERT INTO NAME (NAME) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
            return getNameId(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public Integer getEmailId(String email) {
        try {
            ps = dbm.getPPST("SELECT * FROM EMAIL WHERE NAME = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("ID");
                if (rs.wasNull()) {
                    id = null;
                } else {
                    return id;
                }
            }
            System.out.println("saving... " + email);
            return saveEmail(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }

        return null;
    }
    
    public Integer saveEmail(String email) {
        try {
            ps = dbm.getPPST("INSERT INTO EMAIL (NAME) VALUES (?)");
            ps.setString(1, email);
            ps.executeUpdate();
            return getEmailId(email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public Integer getLoginNameId(String loginname) {
        try {
            ps = dbm.getPPST("SELECT * FROM LOGINNAME WHERE NAME = ?");
            ps.setString(1, loginname);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("ID");
                if (rs.wasNull()) {
                    id = null;
                } else {
                    return id;
                }
            }
            System.out.println("saving... " + loginname);
            return saveLoginName(loginname);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public Integer saveLoginName (String loginname) {
        try {
            ps = dbm.getPPST("INSERT INTO LOGINNAME (NAME) VALUES (?)");
            ps.setString(1, loginname);
            ps.executeUpdate();
            return getLoginNameId(loginname);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public Integer getPasswordHashId(String password) {
        try {
            ps = dbm.getPPST("SELECT * FROM PASSWORDHASH WHERE NAME = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("ID");
                if (rs.wasNull()) {
                    id = null;
                } else {
                    return id;
                }
            }
            System.out.println("saving... " + password);
            return savePasswordHash(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
    
    public Integer savePasswordHash (String password) {
        try {
            ps = dbm.getPPST("INSERT INTO PASSWORDHASH (NAME) VALUES (?)");
            ps.setString(1, password);
            ps.executeUpdate();
            return getPasswordHashId(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        
        return null;
    }
}
