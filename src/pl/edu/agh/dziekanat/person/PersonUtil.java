package pl.edu.agh.dziekanat.person;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PersonUtil {

    public void setPassword(Person person, String password) {

    }

    public static String getHashedPassword(String password) {

        MessageDigest md;
        String sha1HashPassword = "";
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(password.getBytes("UTF-8"));

            sha1HashPassword = new BigInteger(1, md.digest()).toString(16);

            //System.out.println(sha1HashPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sha1HashPassword;
    }

}
