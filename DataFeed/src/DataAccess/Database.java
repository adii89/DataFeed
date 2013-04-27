package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import Config.Constants;
import Exceptions.*;
import java.sql.PreparedStatement;
/**
 *
 * @author Adi
 */
public class Database {
    private String ConnString;
    private Connection Conn;
    private Statement Stmt;
    private PreparedStatement prepStmt;
    private ResultSet resultSet = null;
    //Defalt Constructor
    public Database(){
        try {
             ConnString = ConnectionString.GetConnString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    };
    //OpenConnection
    private void OpenConnetion(){
        try {
            Conn = DriverManager.getConnection(ConnString);
            Conn.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
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
    
    //TODO: ExecuteSQL
    public void ExecuteSQL(String SQL) {
        if (Conn != null) {
            try {
                Stmt = Conn.createStatement();
                Stmt.setQueryTimeout(Constants.DB_TIMEOUT);
                
            } catch (SQLException ex) {
                
            }
            
            
        } else {
            
        }
    }
    
    //TODO: SelectSQL
    public ResultSet SelectSQL(String SQL) throws ApplicationException, SQLException{
        if (SQL.toUpperCase().startsWith("SELECT")) {
            if (Conn != null) {
                Stmt = Conn.createStatement();
                Stmt.setQueryTimeout(Constants.DB_TIMEOUT);
                resultSet = Stmt.executeQuery(SQL);
                return resultSet;
            } else {
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid SELECT statement");
        }
    }
    //InsertSQL
    public int InsertSQL(String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("INSERT")) {
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                int rowsAffected = 0;
                try {
                     rowsAffected = prepStmt.executeUpdate();
                } catch(SQLException ex) {
                    RollbackTransaction();
                    throw new SQLException(ex);               
                }           
                if (rowsAffected == 0) {
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
                return retVal;
            } else {
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a SELECT statement");
        }
    }
    //TODO: DeleteSQL
    public void DeleteSQL (String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("DELETE")) {
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.NO_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                try {
                    prepStmt.executeUpdate();
                    CommitTransaction();
                } catch (SQLException ex) {
                    RollbackTransaction();
                    throw new SQLException(ex);
                }
            } else {
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid DELETE statement");
        }
            
    }
    //TODO: UpdateSQL
    public void UpdateSQL (String SQL) throws ApplicationException, SQLException {
        if (SQL.toUpperCase().startsWith("UPDATE")) {
            if (Conn != null) {
                prepStmt = Conn.prepareStatement(SQL, PreparedStatement.NO_GENERATED_KEYS);
                prepStmt.setQueryTimeout(Constants.DB_TIMEOUT);
                try {
                    prepStmt.executeUpdate();
                    CommitTransaction();
                } catch (SQLException ex) {
                    RollbackTransaction();
                    throw new SQLException(ex);
                }
            } else {
                throw new ApplicationException("The Database is not open");
            }
        } else {
            throw new ApplicationException("The statement appears not to be a valid UPDATE statement");
        }
    }
}
