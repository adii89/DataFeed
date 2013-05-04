/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Valerie
 * The objects pertaining to this class will be constructed with the input from the sections.txt file
 */
public class Sections{ 
    
    private String CourseNumber;
   
    private String Dept;
    private String CallNumber; 
    
    private String Days; 
    private String Time; //
    private boolean Media; //  
    
    public Sections (String courseNum, String departm, String callNum, String days, String time, boolean media){
    CourseNumber = courseNum;
    Dept= departm;
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

   public String getDept() {
        return Dept;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public String getCallNumber() {
        return CallNumber;
    }

    public void setCallNumber(String CallNumber) {
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

    public void setMedia(boolean Media) {
        this.Media = Media;
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
    
}
