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
        
        User user = new User(1, "Udo", 
                "Kruegerzwei", 
                "blah@keks.de",
                "udo.krueger",
                CryptManager.createHash(password), 
                true, true);
     
        
        UserPersistence up = new UserPersistence(); // Ich erzeuge eine Persistence mit dem Kontext "User".
        //up.save(user); // save... 
        //up.getUserFromId(21);
        System.out.println(up.getUserList());
        
        
    }
}
