package Test;

import DataAccess.Database;
import java.sql.*;
/**
 *
 * @author Adrian Krzeszkiewicz
 * This class is used only to test Database connection to the server
 * If it does not work for you, please make sure that JDBC 4 is installed
 * and that the CLASSPATH variable is configured properly.
 */
public class TestConnection {
    
    public static void main(String[] args) {
        boolean a = false;
        if (a) {
            try {
                Database DB = new Database();
                DB.ConnString = "jdbc:sqlserver://ADI-LAPTOP\\SQLEXPRESS;database=pfuenrolldb;integratedSecurity=true;";
                ResultSet rs = DB.SelectSQL("SELECT * FROM Test");
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                }
            } catch (Exception ex) {
                
            }
            
        } else {
            String connURL = "jdbc:sqlserver://akg0srei8q.database.windows.net;database=pfuenrolldb;user=pfuenroll_dbuser;password=$aggyBa11$!1&&;";
            //String connURL = "jdbc:sqlserver://akg0srei8q.database.windows.net;database=pfuenrolldb;user=adrian.krzeszkiewicz;password=3l3phant!!;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(connURL);

                String SQL = "SELECT * FROM Test";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " ..... " + rs.getString(3));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (rs != null) try { rs.close(); } catch (Exception e){}
                if (stmt != null) try { stmt.close(); } catch (Exception e){}
                if (conn != null) try { conn.close(); } catch (Exception e){}
            }
        }
        
    }
}
