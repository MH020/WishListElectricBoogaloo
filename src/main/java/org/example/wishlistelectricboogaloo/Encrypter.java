package org.example.wishlistelectricboogaloo;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
    public String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger bigInt = new BigInteger(1, messageDigest);
            String hashtext = bigInt.toString(16);

            while (hashtext.length() < 20) {
                hashtext = "0" + hashtext;
            }
            while (hashtext.length() > 20) {
                hashtext = hashtext.substring(0,20);
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
