/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import Exceptions.ApplicationException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class InstructorGeneralPreferences {
 
private int InstructorId;    
private int Department;
private String InstructorFirstName;
private String InstructorLastName;
private char InstructorMiddleInt;
private int NumberOfSectionsToTeach;
private int Weekend;
private static ArrayList<String> preferences = new ArrayList<String>();

public InstructorGeneralPreferences(int instructorId, int dept, String fName, String lName, char m, int numSec, int saturd, ArrayList<String> campusPreference) {
    InstructorId=instructorId;
    Department=dept;
    InstructorFirstName=fName;
    InstructorLastName= lName;
    InstructorMiddleInt = m;
    NumberOfSectionsToTeach=numSec;
    Weekend=saturd;
    preferences=campusPreference;
      
}

    public int getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(int InstructorId) {
        this.InstructorId = InstructorId;
    }


    public static ArrayList<String> getPreferences() {
        return preferences;
    }

    public static void setPreferences(ArrayList<String> preferences) {
        InstructorGeneralPreferences.preferences = preferences;
    }

    public int getDepartment() {
        return Department;
    }

    public void setDepartment(int Department) {
        this.Department = Department;
    }

  

    public int getNumberOfSectionsToTeach() {
        return NumberOfSectionsToTeach;
    }

    public void setNumberOfSectionsToTeach(int NumberOfSectionsToTeach) {
        this.NumberOfSectionsToTeach = NumberOfSectionsToTeach;
    }

   

    public String getInstructorFirstName() {
        return InstructorFirstName;
    }

    public void setInstructorFirstName(String InstructorFirstName) {
        this.InstructorFirstName = InstructorFirstName;
    }

    public String getInstructorLastName() {
        return InstructorLastName;
    }

    public void setInstructorLastName(String InstructorLastName) {
        this.InstructorLastName = InstructorLastName;
    }

    public char getInstructorMiddleInt() {
        return InstructorMiddleInt;
    }

    public void setInstructorMiddleInt(char InstructorMiddleInt) {
        this.InstructorMiddleInt = InstructorMiddleInt;
    }



    public int getWeekend() {
        return Weekend;
    }

    public void setWeekend(int Weekend) {
        this.Weekend = Weekend;
    }
    
    public String getUsername(){
        
     String username;
     String emailPadding= "@pfu.edu";
       
     username= getInstructorFirstName().substring(0, 0)+ "." + getInstructorLastName()+ emailPadding;
             
     return username;
    }
    
     public void InsertIntoPfuUser(){
    ///i added th throws statements...????
        String SQL;
        SQL = "INSERT INTO dbo.PfuUser (UserTypeId, DepartmentId, UserFirstName, "
                + "UserLastName, UserMiddleInit, Username, Password, CreateDate, ModifyDate) " 
                    + " VALUES("+ 2 + getDepartment() +  getInstructorFirstName() + getInstructorLastName() 
                        + getInstructorMiddleInt() + getUsername() + "pass123" +")";
        Database DB = new Database();
        try {
           InstructorId = DB.InsertSQL(SQL);//scope identity  
        }//end try
        catch (SQLException ex) {
              ////
           ///ERROR HANDLING FUNCTION
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {
            ////
            Logger.ErrorLog.LogError(ex);
        }//end second catch

    

@Override 
    public String toString(){
    
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Instructor's Name:           " + getInstructorFirstName() + " "+ 
                    getInstructorMiddleInt()+ " "+ getInstructorLastName() + NEW_LINE);
        i.append("Department ID:                  " + getDepartment() + NEW_LINE);
        i.append("Number of sections to teach: " + getNumberOfSectionsToTeach() + NEW_LINE);
        i.append("Campus preferences:          " + getPreferences() + NEW_LINE);
       
        
        return i.toString();
    
    }//end toString
    
    
}
