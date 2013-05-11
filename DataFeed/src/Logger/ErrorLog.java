package Logger;

import Config.ConfigManager;
import DataAccess.ConnectionString;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Adrian krzeszkiewicz
 * This class is responsible for logging errors in the database (if they were to happen)
 * Therefore, it is important to wrap critical pieces of code inside a try catch blocks
 */
public class ErrorLog {
    
    public static void LogError(Exception e) {
        
        try {
            Connection Conn;
            String ConnStr;
            if (ConfigManager.GetConfgElement("TestMode").equalsIgnoreCase("true")) {
                ConnStr =ConfigManager.GetConfgElement("TestDBServer");
            } else {
                ConnStr = ConnectionString.GetConnString();
            }
            System.out.println(ConnStr);
            Conn = DriverManager.getConnection(ConnStr);
            Conn.setAutoCommit(false);
            String SQL = "INSERT INTO ErrorLog VALUES ('" + e.getMessage() + "', '" + GetStackTrace(e.getStackTrace()) + "', GETDATE())";
            PreparedStatement Stmt = Conn.prepareStatement(SQL);
            Stmt.setQueryTimeout(Config.Constants.DB_TIMEOUT);
            Stmt.executeUpdate();
            Conn.commit();
            Conn.close();
            Conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String GetStackTrace(StackTraceElement[] stElem) {
        String retString = "";
        for (StackTraceElement Elem : stElem) {
            retString += "Class Name: "  + Elem.getClassName() + "||| Method Name: " + Elem.getMethodName() + "||| Filename: " + Elem.getFileName() + "||| Line No.: " + Elem.getLineNumber() + "\n";
        }
        return retString;
    }
}
