/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florian.brosig
 */
public class DatabaseManager {

    Connection conn;
    
    public PreparedStatement getPPST(String sql) {
        try {
            if (conn == null) {
                connect();
            }
            
            return conn.prepareStatement(sql);
        } catch (SQLException el ) {
            el.printStackTrace();
        }
        return null;
    }
    
    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:h2:c:\\database\\/db", "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
