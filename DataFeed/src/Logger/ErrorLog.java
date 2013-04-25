/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

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
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(ConnectionString.GetConnString());
            Conn.setAutoCommit(false);
            PreparedStatement prepStmt = Conn.prepareStatement("INSERT INTO ErrorLog VALUES (\"?, ?, GETDATE())");
            prepStmt.setString(1, e.getMessage());
            prepStmt.setString(2, GetStackTrace(e.getStackTrace()));
            prepStmt.executeUpdate();
            Conn.commit();
            prepStmt.close();
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
