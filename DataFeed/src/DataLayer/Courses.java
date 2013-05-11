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
 * The objects pertaining to this class will be constructed with the input from the enroll.txt file
 */
public class Courses {
    private int PreviousEnrollmentId;
    private String CourseNumber;
    private int DepartmentId;
    private int NumberEnrolled;

    public Courses (){
        PreviousEnrollmentId = 0;
        CourseNumber = "";
        DepartmentId = 0;
        NumberEnrolled = 0;
    }
    
    public int getPreviousEnrollmentId() {
        return PreviousEnrollmentId;
    }

    public String getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(String CourseNumber) {
        this.CourseNumber = CourseNumber;
    }

    public int getDepartment() {
        return DepartmentId;
    }

    public void setDepartment(int Department) {
        this.DepartmentId = Department;
    }

    public int getNumberEnrolled() {
        return NumberEnrolled;
    }

    public void setNumberEnrolled(int NumberEnrolled) {
        this.NumberEnrolled = NumberEnrolled;
    }
    
    public void Insert() throws ApplicationException{
        if (CourseNumber == "") {
            throw new ApplicationException ("No Course Number Specified for Previous Enrollment");
        }
        if (DepartmentId == 0) {
            throw new ApplicationException ("No Department Specified for Previous Enrollment");
        }
        String SQL;
        SQL = "INSERT INTO dbo.PreviousEnrollment (CourseNumber, DepartmentId, PreviousEnrollmentNumber) VALUES(" + DatabaseHelper.Quote(getCourseNumber()) + "," + getDepartment()+ "," + getNumberEnrolled() + ")";
        //SQL = "INSERT INTO dbo.PreviousEnrollment (CourseNumber, DepartmentId, PreviousEnrollmentNumber) VALUES(" +getCourseNumber()+","+getDepartment()+","+ getNumberEnrolled() + ")";
        Database DB = new Database();
        try {
            PreviousEnrollmentId = DB.InsertSQL(SQL);
        }
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    
    @Override 
    public String toString(){
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Course number:   " + getCourseNumber() + NEW_LINE);
        i.append("Department:      " + getDepartment() + NEW_LINE);
        i.append("Number enrolled: " + getNumberEnrolled() + NEW_LINE);
        return i.toString();
    }
    
    public void LoadById(int peId) {
        String SQL = "SELECT * FROM PreviousEnrollment WHERE PreviousEnrollmentId = " + peId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                PreviousEnrollmentId = rs.getInt(1);
                CourseNumber = rs.getString(2);
                DepartmentId = rs.getInt(3);
                NumberEnrolled = rs.getInt(4);
            } else {
                PreviousEnrollmentId = 0;
                CourseNumber = "";
                DepartmentId = 0;
                NumberEnrolled = 0;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    public void LoadByDepartmentAndCourseNumber(int dId, String cNumber) {
        String SQL = "SELECT * FROM PreviousEnrollment WHERE DepartmentId = " + dId + " AND CourseNumber = " + DatabaseHelper.Quote(cNumber);
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                PreviousEnrollmentId = rs.getInt(1);
                CourseNumber = rs.getString(2);
                DepartmentId = rs.getInt(3);
                NumberEnrolled = rs.getInt(4);
            } else {
                PreviousEnrollmentId = 0;
                CourseNumber = "";
                DepartmentId = 0;
                NumberEnrolled = 0;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}
