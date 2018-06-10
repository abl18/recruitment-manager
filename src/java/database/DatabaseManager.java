/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Udo Krüger
 */
public class DatabaseManager {

    Connection conn = null;
    
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
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9097/C://database//db", "sa", "");
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
