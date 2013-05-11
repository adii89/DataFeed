package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import Config.Constants;
import Exceptions.*;
import java.sql.PreparedStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
/**
 *
 * @author Adi
 */
public class Database {
    public String ConnString;
    private Connection Conn;
    private Statement Stmt;
    private PreparedStatement prepStmt;
    private ResultSet resultSet = null;
    //Defalt Constructor
    public Database(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             ConnString = ConnectionString.GetConnString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //OpenConnection
    public void OpenConnection(){
        try {
            Conn = DriverManager.getConnection(ConnString);
            Conn.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    //TODO: Close
    public void CloseConnection() throws SQLException{
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (Stmt != null) {
                Stmt.close();
            }
            if (prepStmt != null) {
                prepStmt.close();
            }
            if (Conn != null){
                Conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //TODO: CommitTransaction
    private void CommitTransaction() {
        if (Conn != null){
            try {
                Conn.commit();
                CloseConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //TODO: RollbackTransaction
    private void RollbackTransaction(){
        if (Conn != null){
            try {
                Conn.rollback();
                CloseConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } 
    //TODO: SelectSQL
    public ResultSet SelectSQL(String SQL) throws ApplicationException, SQLException{
        if (SQL.toUpperCase().startsWith("SELECT")) {
            OpenConnection();
            if (Conn != null) {
                Stmt = Conn.createStatement();
                Stmt.setQueryTimeout(Constants.DB_TIMEOUT);
                resultSet = Stmt.executeQuery(SQL);
                return resultSet;
            } else {
                CloseConnection();
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid SELECT statement");
        }
    }
    //InsertSQL
    public int InsertSQL(String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("INSERT")) {
            OpenConnection();
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                int rowsAffected = 0;
                try {
                     rowsAffected = prepStmt.executeUpdate();
                } catch(SQLException ex) {
                    RollbackTransaction();
                    CloseConnection();
                    throw new SQLException(ex);               
                }           
                if (rowsAffected == 0) {
                    RollbackTransaction();
                    CloseConnection();
                    throw new ApplicationException("INSERT Statement Failed");
                }
                int retVal = 0;
                resultSet = prepStmt.getGeneratedKeys();
                if (resultSet.next()) {
                    retVal = resultSet.getInt(1);
                } else {
                    throw new ApplicationException("INSERT Statement Failed");
                }
                CommitTransaction();
                CloseConnection();
                return retVal;
            } else {
                CloseConnection();
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a INSERT statement");
        }
    }
    //TODO: DeleteSQL
    public void DeleteSQL (String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("DELETE")) {
            OpenConnection();
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.NO_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                try {
                    prepStmt.executeUpdate();
                    CommitTransaction();
                    CloseConnection();
                } catch (SQLException ex) {
                    RollbackTransaction();
                    CloseConnection();
                    throw new SQLException(ex);
                }
            } else {
                CloseConnection();
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid DELETE statement");
        }
            
    }
    //TODO: UpdateSQL
    public void UpdateSQL (String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("UPDATE")) {
            OpenConnection();
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.NO_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                try {
                    prepStmt.executeUpdate();
                    CommitTransaction();
                    CloseConnection();
                } catch (SQLException ex) {
                    RollbackTransaction();
                    CloseConnection();
                    throw new SQLException(ex);
                }
            } else {
                CloseConnection();
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid UPDATE statement");
        }
    }
    
   
}
