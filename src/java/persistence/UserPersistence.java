/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
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
        try {
            PreparedStatement ps = dbm.getPPST("SELECT * FROM USER ORDER BY ID");
            ResultSet rs = ps.executeQuery();
            ArrayList<User> userlist = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("ID"), getNameById(rs.getInt("LASTNAME_ID")), getNameById(rs.getInt("FIRSTNAME_ID")), getEmailById(rs.getInt("EMAIL_ID")), getLoginNameById(rs.getInt("LOGINNAME_ID")), getPasswordHashById(rs.getInt("PASSWORDHASH_ID")), rs.getBoolean("ISADMIN"), rs.getBoolean("ISACTIVE"));
                userlist.add(user);
            }
            for (int i = 1; i < userlist.size(); i++) {
                //System.out.println(userlist.get(i));
            }

            return userlist;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNameById(int id) {

        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT NAME FROM NAME WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("NAME");
                return name;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getEmailById(int id) {

        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT ID, NAME FROM EMAIL WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String email = rs.getString("NAME");
                return email;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getLoginNameById(int id) {

        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT ID, NAME FROM LOGINNAME WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String login = rs.getString("NAME");
                return login;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPasswordHashById(int id) {

        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT ID, NAME FROM PASSWORDHASH WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String pwHash = rs.getString("NAME");
                return pwHash;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(User user) {
        try {
            PreparedStatement ps = null;
            //dbm.getPPST("truncate table name");
            ps = dbm.getPPST("INSERT INTO USER (LASTNAME_ID, FIRSTNAME_ID, EMAIL_ID, LOGINNAME_ID, PASSWORDHASH_ID, ISADMIN, ISACTIVE,) VALUES (?,?,?,?,?,?,?)");

            ps.setInt(1, getNameId(user.lastname));
            ps.setInt(2, getNameId(user.firstname));
            ps.setInt(3, getEmailId(user.email));
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
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT * FROM NAME WHERE NAME = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            PreparedStatement ps = null;
            ps = dbm.getPPST("INSERT INTO NAME (NAME) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
            return getNameId(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getEmailId(String email) {
        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT * FROM EMAIL WHERE NAME = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            PreparedStatement ps = null;
            ps = dbm.getPPST("INSERT INTO EMAIL (NAME) VALUES (?)");
            ps.setString(1, email);
            ps.executeUpdate();
            return getEmailId(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getLoginNameId(String loginname) {
        try {
            PreparedStatement ps = null;
            ps = dbm.getPPST("SELECT * FROM LOGINNAME WHERE NAME = ?");
            ps.setString(1, loginname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public Integer saveLoginName(String loginname) {
        try {
            PreparedStatement ps = dbm.getPPST("INSERT INTO LOGINNAME (NAME) VALUES (?)");
            ps.setString(1, loginname);
            ps.executeUpdate();
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
            while (rs.next()) {
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

    public Integer savePasswordHash(String password) {
        try {
            PreparedStatement ps = dbm.getPPST("INSERT INTO PASSWORDHASH (NAME) VALUES (?)");
            ps.setString(1, password);
            ps.executeUpdate();
            return getPasswordHashId(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
