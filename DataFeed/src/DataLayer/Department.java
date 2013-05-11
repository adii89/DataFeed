/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import Exceptions.ApplicationException;
import java.sql.SQLException;

/**
 *
 * @author Valerie
 */
public class Department {
    
    private String departmentName;
    private int scopeIdentity;
    
    public Department(String department){
        departmentName=department;

      }//end department

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

   public int getDepartmentId(){
   
       return scopeIdentity;
   
   }
     public void Insert(){
    ///
        String SQL;
        SQL = "INSERT INTO dbo.Department (DepartmentName) VALUES("+ getDepartmentName() + ")";
        Database DB = new Database();
        try {
           scopeIdentity = DB.InsertSQL(SQL);//scope identity
        }//end try
        catch (SQLException ex) {
              ////      
           
        }//end first catch
        catch (ApplicationException ex) {
            ////
            
        }//end second catch

    
    }//end public void Insert
    
    
    
    
}//end class
