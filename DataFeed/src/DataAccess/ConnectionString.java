/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.io.IOException;
import Config.ConfigManager;
import Security.Cryptography;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class is responsible for connection sting handling.
 */
public class ConnectionString {
    //Get Username
    private static String GetDBUsername() {
        try {
            return Cryptography.Decrypt(ConfigManager.GetConfgElement("DBUsername"));
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        } catch (Exception ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
        
    }
    //get the password
    private static String GetDBPassword() {
        try {
            return Cryptography.Decrypt(ConfigManager.GetConfgElement("DBPassword"));
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        } catch (Exception ex) {
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
    //Get Test DB Server (SQLExpress)
    private static String GetTestDBServer() {
        try {
            return ConfigManager.GetConfgElement("TestDBServer");
        } catch (IOException ex){
            Logger.ErrorLog.LogError(ex);
            return null;
        }
    }
    //Get TestMode
    private static boolean GetTestMode() {
        try {
            String isTestMode = ConfigManager.GetConfgElement("TestMode");
            if (isTestMode.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex){
            Logger.ErrorLog.LogError(ex);
            return true;
        }
    }
    //Get Hostname
    private static String GetHostname(){
        try {
            InetAddress host;
            host = InetAddress.getLocalHost();
            return host.getHostName();
        } catch (UnknownHostException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
    }
    //Get Connection String
    public static String GetConnString() throws Exception {
        String ConnString;
        try {
            if (GetTestMode()){
                ConnString = ConfigManager.GetConfgElement("DBTestConnectionString");
            } else {
                ConnString = ConfigManager.GetConfgElement("DBConnectionString");
            }
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
            return null;
        }
        if (GetTestMode()) {
            String Server = GetTestDBServer().replace("\\\\", "\\");
            //return String.format(ConnString, Server, GetDBName(), GetDBUsername(), GetDBPassword());
            return Server;
        } else {
            return String.format(ConnString, GetDBServer(), GetDBName(), GetDBUsername(), GetDBPassword());
        }
        
    }
}
