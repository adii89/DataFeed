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
 * The objects pertaining to this class will be constructed with the input from the enroll.txt file
 */
public class Courses {
private String CourseNumber;
private String Department;
private int NumberEnrolled;

public Courses (String courseNum, String depart, int numEnro){

    CourseNumber=courseNum;
    Department=depart;
    NumberEnrolled=numEnro;
    
    
}

    public String getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(String CourseNumber) {
        this.CourseNumber = CourseNumber;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public int getNumberEnrolled() {
        return NumberEnrolled;
    }

    public void setNumberEnrolled(int NumberEnrolled) {
        this.NumberEnrolled = NumberEnrolled;
    }
    
    public void Insert(){
    
        String SQL;
<<<<<<< HEAD
        SQL = "INSERT INTO dbo.PreviousEnrollment (CourseNumber, DepartmentId, PreviousEnrollmentNumber) VALUES(" +getCourseNumber()+ "," + getDepartment()+ getNumberEnrolled() + ")";
=======
        SQL = "INSERT INTO dbo.PreviousEnrollment (CourseNumber, DepartmentId, PreviousEnrollmentNumber) VALUES(" +getCourseNumber()+","+getDepartment()+","+ getNumberEnrolled() + ")";
>>>>>>> 07c633ec574952c90ae098cee8c7ff835a84ff32
        Database DB = new Database();
        try {
            DB.InsertSQL(SQL);
        }//end try
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {

        }//end second catch

    
    }//end public void Insert 
    
    
    @Override 
    public String toString(){
    
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Course number:   " + getCourseNumber() + NEW_LINE);
        i.append("Department:      " + getDepartment() + NEW_LINE);
        i.append("Number enrolled: " + getNumberEnrolled() + NEW_LINE);
       
        
        return i.toString();
    
    }//end toString
    
}
