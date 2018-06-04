package demo;

import crypt.CryptManager;
import database.DatabaseManager;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import model.User;
import persistence.UserPersistence;

/**
 *
 * @author Udo Krüger
 */

public class Main {
    public static void main(String[] args) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        Main main  = new Main();
        main.start();
    }
    
    public void start() throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("HELLO WORLD!");
        String password = new String("1234567");
        
    /** User user = new User(); // Wir bauen das Object User.
        user.firstname = "Florian";
        user.lastname = "Brosig";
        user.email = "florian.brosig@bofestconsult.com";
        user.loginname = "florian.brosig";
        user.passwordHash = CryptManager.createHash(password);
        user.isActive = true;
        user.isAdmin = true;
     **/
        
        UserPersistence up = new UserPersistence(); // Ich erzeuge eine Persistence mit dem Kontext "User".
        //up.save(user); // save... 
        up.getUserFromId(21);
        
        
    }
}
