/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Security.Cryptography;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Adi
 */
public class ConnectionString {
    private static Properties Prop = new Properties();
    //Get Username
    private static String GetDBUsername() throws Exception {
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Cryptography.Decrypt(Prop.getProperty("DBUsername"));
    }
    //get the password
    private static String GetDBPassword() throws Exception {
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Cryptography.Decrypt(Prop.getProperty("DBUsername"));
    }
    //get the server
    private static String GetDBServer() {
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Prop.getProperty("DBServerName");
    }
    //Get Database Name
    private static String GetDBName() {
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Prop.getProperty("DBName");
    }
    //Get Connection String
    public static String GetConnString() throws Exception {
        try {
            Prop.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return String.format(Prop.getProperty("DBConnectionString"), GetDBServer(), GetDBName(), GetDBUsername(), GetDBPassword());
    }
    //TODO: Encrypt Values
    //TODO: Database Open
    //TODO: Database Close
    //TODO: Database Begin Tran
    //TODO: Database Commit Tran
    //TODO: Database Rollback Tran
    //TODO: If DB Open
}
