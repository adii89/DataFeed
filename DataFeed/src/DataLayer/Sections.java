/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Valerie
 */
public class Sections{
    
    private int CourseNumber;
    private int NumberEnrolled;
    private String Dept;
    private String CallNumber; 
    
    private String Days; 
    private String Time; //is this an int? *It looks more like a string...we have to extract it
    private int Media; //does it require a media room, 1 = yes 0 = no  
    
    public Sections (int courseNum, int numEnro, String departm, String callNum, String days, String time, int media){
    CourseNumber = courseNum;
    NumberEnrolled=numEnro;
    Dept= departm;
    CallNumber= callNum;
    Days=days;
    Time=time;
    Media=media;
    
    }

    public int getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(int CourseNumber) {
        this.CourseNumber = CourseNumber;
    }

    public int getNumberEnrolled() {
        return NumberEnrolled;
    }

    public void setNumberEnrolled(int NumberEnrolled) {
        this.NumberEnrolled = NumberEnrolled;
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

    public int getMedia() {
        return Media;
    }

    public void setMedia(int Media) {
        this.Media = Media;
    }
    
    
}
