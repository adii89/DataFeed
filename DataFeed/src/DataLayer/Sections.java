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
 * The objects pertaining to this class will be constructed with the input from the sections.txt file
 */
public class Sections{ 
    
    private int SectionId;
    private String CourseNumber;
    private int DepartmentId;
    private int CallNumber; 
    private String Days; 
    private String Time; //
    private boolean Media; //  
    
    public Sections (){
        SectionId = 0;
        CourseNumber = "";
        DepartmentId = 0;
        CallNumber = 0;
        Days = "";
        Time = "";
        Media = false;
    }
    
    public int getSectionId() {
        return SectionId;
    }

    public String getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(String CourseNumber) {
        this.CourseNumber = CourseNumber;
    }

   public int getDept() {
        return DepartmentId;
    }

    public void setDept(int Dept) {
        this.DepartmentId = Dept;
    }

    public int getCallNumber() {
        return CallNumber;
    }

    public void setCallNumber(int CallNumber) {
        this.CallNumber = CallNumber;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String Days) {
        this.Days = Days;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String t) {
        if (t.length() == 7) {
            t = "0" + t;
        }
        Time = t;
    }

    public boolean getMedia() {
        return Media;
    }
    
    private int getMediaDB() {
        return Media ? 1 : 0;
    }

    public void setMedia(boolean Media) {
        this.Media = Media;
    }
    
     public void Insert() throws ApplicationException{
         if (DepartmentId == 0) {
             throw new ApplicationException("Department Was Not Specified!");
         }
         if (CourseNumber == "") {
             throw new ApplicationException("Course Number Was Not Specified!");
         }
         if (CallNumber == 0) {
             throw new ApplicationException("Call Number Was Not Specified!");
         }
         if (Days == "") {
             throw new ApplicationException("Meeting Days Were Not Specified!");
         }
         if (Time == "") {
             throw new ApplicationException("Meeting Times Were Not Specified!");
         }
        String SQL = "INSERT INTO dbo.Section (DepartmentId, CourseNumber, CallNumber, MeetingDays, MeetingTimes, MediaRequired) VALUES(" + getDept() + "," + DatabaseHelper.Quote(getCourseNumber()) + "," + getCallNumber() + "," + DatabaseHelper.Quote(getDays()) + "," + DatabaseHelper.Quote(getTime()) + "," + getMediaDB() + ")";
        Database DB = new Database();
        try {
            SectionId = DB.InsertSQL(SQL);
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
        i.append("Course number: " + getCourseNumber() + NEW_LINE);
        i.append("Department:    " + getDept() + NEW_LINE);
        i.append("Call number:   " + getCallNumber() + NEW_LINE);
        i.append("Meeting Days:  " + getDays() + NEW_LINE);
        i.append("Meeting Time:  " + getTime() + NEW_LINE);
        i.append("Media:         " + getMedia() + NEW_LINE);
        
        return i.toString();
    }
    
    public void LoadById(int sId) {
        String SQL = "SELECT * FROM Section WHERE SectionId = " + sId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                SectionId = rs.getInt(1);
                DepartmentId = rs.getInt(2);
                CourseNumber = rs.getString(3);
                CallNumber = rs.getInt(4);
                Days = rs.getString(5);
                Time = rs.getString(6);
                Media = rs.getBoolean(7);
            } else {
                SectionId = 0;
                DepartmentId = 0;
                CourseNumber = "";
                CallNumber = 0;
                Days = "";
                Time = "";
                Media = false;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    public void LoadByCallNumber(int cNumber) {
        String SQL = "SELECT * FROM Section WHERE CallNumber = " + cNumber;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                SectionId = rs.getInt(1);
                DepartmentId = rs.getInt(2);
                CourseNumber = rs.getString(3);
                CallNumber = rs.getInt(4);
                Days = rs.getString(5);
                Time = rs.getString(6);
                Media = rs.getBoolean(7);
            } else {
                SectionId = 0;
                DepartmentId = 0;
                CourseNumber = "";
                CallNumber = 0;
                Days = "";
                Time = "";
                Media = false;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}
