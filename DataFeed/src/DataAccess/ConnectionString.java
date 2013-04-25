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
            return ConfigManager.GetConfgElement("DBPassword");
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
    }
    //get the server
    private static String GetDBServer() {
        try {
            return ConfigManager.GetConfgElement("DBServer");
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
    }
    //Get Database Name
    private static String GetDBName() {
        try {
            return ConfigManager.GetConfgElement("DBName");
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
    }
    //Get Connection String
    public static String GetConnString() throws Exception {
        String ConnString = null;
        try {
            ConnString = ConfigManager.GetConfgElement("DBUsername");
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
        return String.format(ConnString, GetDBServer(), GetDBName(), GetDBUsername(), GetDBPassword());
    }
}
