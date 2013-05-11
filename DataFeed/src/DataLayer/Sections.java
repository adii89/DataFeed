/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
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
    
    public Sections (String courseNum, int departm, int callNum, String days, String time, boolean media){
    CourseNumber = courseNum;
    DepartmentId= departm;
    CallNumber= callNum;
    Days=days;
    Time=time;
    Media=media;
    
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

    public void setTime(String Time) {
        this.Time = Time;
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
    
     public void Insert(){
    
        String SQL;
        SQL = "INSERT INTO dbo.Section (CourseNumber, CallNumber, MeetingDays, MeetingTimes, MediaRequired) VALUES(" +getCourseNumber()+ "," + getCallNumber() + "," + DatabaseHelper.Quote(getDays()) + "," + DatabaseHelper.Quote(getTime()) + "," + getMediaDB() + ")";
        Database DB = new Database();
        try {
            SectionId = DB.InsertSQL(SQL);
        }//end try
        catch (SQLException ex) {

        }//end first catch
        catch (ApplicationException ex) {

            
            
        }//end second catch

    
    }//end public void Insert 
    
    
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
    
}
