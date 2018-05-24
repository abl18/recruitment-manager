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
 * @author Udo Krüger
 */
public class UserPersistence {
    
    DatabaseManager dbm = new DatabaseManager();
    
    public List<User> getUserList() {
        // get all User...
        // sortiert per nachnamen, Hinweis: SELECT * FROM USER ORDER BY NAME z.B.
        return null;
    }
    
    public User getUserFromId(int id) {
        // get user from id... 
        
        return null;
    }
    
    public void save (User user) {
        try {
            //dbm.getPPST("truncate table name");
            PreparedStatement ps = dbm.getPPST("INSERT INTO USER (LASTNAME_ID, FIRSTNAME_ID, EMAIL_ID, LOGINNAME_ID, PASSWORDHASH_ID, ISADMIN, ISACTIVE,) VALUES (?,?,?,?,?,?,?)");
            
            // TODO!!! 
            // email speichern, DONE
            // loginname speichern, DONE
            // password hashen und dann speichern!!!
            
            ps.setInt(1, getNameId(user.lastname));
            ps.setInt(2, getNameId(user.firstname));
            ps.setInt(3, getEmailId(user.email ));
            ps.setInt(4, getLoginNameId(user.loginname));
            ps.setInt(5, getPasswordHashId(user.passwordHash));
            ps.setBoolean(6, user.isAdmin);
            ps.setBoolean(7, user.isActive);
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
            return getNameId(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer getEmailId(String email) {
        try {
            PreparedStatement ps = dbm.getPPST("SELECT * FROM EMAIL WHERE NAME = ?");
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
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer saveEmail(String email) {
        try {
            PreparedStatement ps2 = dbm.getPPST("INSERT INTO EMAIL (NAME) VALUES (?)");
            ps2.setString(1, email);
            ps2.executeUpdate();
            return getEmailId(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer getLoginNameId(String loginname) {
        try {
            PreparedStatement ps = dbm.getPPST("SELECT * FROM LOGINNAME WHERE NAME = ?");
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
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer saveLoginName (String loginname) {
        try {
            PreparedStatement ps2 = dbm.getPPST("INSERT INTO LOGINNAME (NAME) VALUES (?)");
            ps2.setString(1, loginname);
            ps2.executeUpdate();
            return getLoginNameId(loginname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer getPasswordHashId(String password) {
        try {
            PreparedStatement ps = dbm.getPPST("SELECT * FROM PASSWORDHASH WHERE NAME = ?");
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
            e.printStackTrace();
        }
        return null;
    }
    
    public Integer savePasswordHash (String password) {
        try {
            PreparedStatement ps2 = dbm.getPPST("INSERT INTO PASSWORDHASH (NAME) VALUES (?)");
            ps2.setString(1, password);
            ps2.executeUpdate();
            return getPasswordHashId(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
