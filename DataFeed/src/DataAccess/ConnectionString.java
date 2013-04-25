/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Security.Cryptography;
import java.io.FileInputStream;
import java.io.IOException;
import Config.ConfigManager;

/**
 *
 * @author Adi
 */
public class ConnectionString {
    //Get Username
    private static String GetDBUsername() {
        try {
            return ConfigManager.GetConfgElement("DBUsername");
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
        
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
