package crypt;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Udo Krüger
 */
public class CryptManager {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int SALT_BYTES = 24;
    public static final int HASH_BYTES = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    
    public static String createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray());
    }

    public static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
        
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
    }

    public static boolean validatePassword(String password, String goodHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), goodHash);
    }

    public static boolean validatePassword(char[] password, String goodHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] params = goodHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

   
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++) {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) 
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    public static void main(String[] args) {
        try {
            for(int i = 0; i < 10; i++)
                System.out.println(CryptManager.createHash("p\r\nassw0Rd!"));

           
            boolean failure = false;
            System.out.println("Running tests...");
            for (int i = 0; i < 100; i++) {
                String password = ""+i;
                String hash = createHash(password);
                String secondHash = createHash(password);
                if(hash.equals(secondHash)) {
                    System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
                    failure = true;
                }
                String wrongPassword = ""+(i+1);
                if(validatePassword(wrongPassword, hash)) {
                    System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
                    failure = true;
                }
                if(!validatePassword(password, hash)) {
                    System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
                    failure = true;
                }
            }
            if(failure)
                System.out.println("TESTS FAILED!");
            else
                System.out.println("TESTS PASSED!");
        } catch(Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }
}