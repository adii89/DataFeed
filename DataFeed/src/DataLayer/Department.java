/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Valerie
 */
public class Department {
    private int DepartmentId;
    private String DepartmentName;
    
    //public Department(String department){
       // DepartmentName = department;
      //}//end department
    
    public Department(){
        DepartmentId = 0;
        DepartmentName = null;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.DepartmentName = departmentName;
    }

   public int getDepartmentId(){
       return DepartmentId;
   }
   
    public void Insert() throws ApplicationException{
        if (DepartmentName == "") {
            throw new ApplicationException("Department Name was Not Specified");
        }
       String SQL = "INSERT INTO dbo.Department (DepartmentName) VALUES("+ DatabaseHelper.Quote(getDepartmentName()) + ")";
       Database DB = new Database();
       try {
          DepartmentId = DB.InsertSQL(SQL);//scope identity
       }//end try
       catch (SQLException ex) {
             Logger.ErrorLog.LogError(ex);
       }//end first catch
       catch (ApplicationException ex) {
           Logger.ErrorLog.LogError(ex);
       }//end second catch
   }//end public void Insert
    
    public void LoadById(int dId) {
        String SQL = "SELECT * FROM Department WHERE DepartmentId = " + dId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DepartmentId = rs.getInt(1);
                DepartmentName = rs.getString(2);
            } else {
                DepartmentId = 0;
                DepartmentName = null;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    public void LoadByDepartmentName(String dName) {
        String SQL = "SELECT * FROM Department WHERE DepartmentName = " + DatabaseHelper.Quote(dName);
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DepartmentId = rs.getInt(1);
                DepartmentName = rs.getString(2);
            } else {
                DepartmentId = 0;
                DepartmentName = null;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}//end class
