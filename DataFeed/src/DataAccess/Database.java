package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.*;
/**
 *
 * @author Adi
 */
public class Database {
    private static final int TIMEOUT = 30;
    private String ConnString;
    private Connection Conn;
    private Statement Stmt;
    private ResultSet resultSet = null;
    //Defalt Constructor
    public Database() throws SQLException{
        try {
             ConnString = ConnectionString.GetConnString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    };
    //OpenConnection
    private void OpenConnetion() throws SQLException{
        Conn = DriverManager.getConnection(ConnString);
        Conn.setAutoCommit(false);
    }
    
    //TODO: Close
    private void CloseConnection() throws SQLException{
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (Stmt != null) {
                Stmt.close();
            }
            if (Conn != null){
                Conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //TODO: BeginTransaction
    //TODO: CommitTransaction
    //TODO: RollbackTransaction
    
    //TODO: ExecuteSQL
    //TODO: SelectSQL
    //TODO: InsertSQL
    //TODO: DeleteSQL
    //TODO: UpdateSQL
}
