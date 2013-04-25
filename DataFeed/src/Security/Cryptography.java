package Security;

import java.io.FileInputStream;
import java.security.*;
import java.io.IOException;
import java.util.Properties;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
/**
 *
 * @author Adrian Krzeszkiewicz
 * This class is responsible for Encryption and Decryption of values to
 * and from the database. The Key is located in the config.properties file
 * and has to be placed at the same directory that the application is
 * being run from.
 */
public class Cryptography {
    
    private static Key GetKey() throws Exception {
        Properties Prop = new Properties();
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String keyString = Prop.getProperty("ivKey");
        return new SecretKeySpec(keyString.getBytes(), "AES");
    }
    
    //Encrypt Function
    public static String Encrypt(String Data) throws Exception{
        Key k = GetKey();
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, k);
        byte[] encryptedVal = aesCipher.doFinal(Data.getBytes());
        return new BASE64Encoder().encode(encryptedVal);
    }
    
    //Decrypt Function
    public static String Decrypt(String encryptedData) throws Exception {
        Key k = GetKey();
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, k);
        byte[] decryptedVal = new BASE64Decoder().decodeBuffer(encryptedData);
        return new String(aesCipher.doFinal(decryptedVal));
    }
}
