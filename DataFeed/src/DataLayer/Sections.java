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
    private int NumberEnrolled;
    private String Dept;
    private String CallNumber; 
    
    private String Days; 
    private String Time; //is this an int? *It looks more like a string...we have to extract it
    private int Media; //does it require a media room, 1 = yes 0 = no  
    
    public Sections (String courseNum, int numEnro, String departm, String callNum, String days, String time, int media){
    CourseNumber = courseNum;
    NumberEnrolled=numEnro;
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

    /*public float getDaysTime() {
        return DaysTime;
    }

    public void setDaysTime(float DaysTime) {
        this.DaysTime = DaysTime;
    }*/

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
