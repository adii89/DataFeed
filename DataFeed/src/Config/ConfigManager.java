package Config;

import Security.Cryptography;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Adrian Krzeszkiewicz
 * this class is used as a helper class which is responsible
 * for extracting the configuration settings from config.properties file
 */
public class ConfigManager {
    private static Properties Prop = new Properties();
    
    //Get Username
    public static String GetConfgElement(String elem) throws IOException {
        try {
            Prop.load(new FileInputStream("C:\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Prop.containsKey(elem) ? Prop.getProperty(elem) : null;
    }
}
