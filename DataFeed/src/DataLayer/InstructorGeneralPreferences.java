/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Erik
 */
public class InstructorGeneralPreferences {
    
private String Department;
private String InstructorFirstName;
private String InstructorLastName;
private char InstructorMiddleInt;
private int NumberOfSectionsToTeach;
private int Weekend;
private InstructorCampusPreference campusPref;


public InstructorGeneralPreferences(String dept, String fName, String lName, char m, int numSec, int saturd, InstructorCampusPreference campusP) {
    
    Department=dept;
    InstructorFirstName=fName;
    InstructorLastName= lName;
    InstructorMiddleInt = m;
    NumberOfSectionsToTeach=numSec;
    Weekend=saturd;
    campusPref=campusP;
}

    public InstructorCampusPreference getCampusPref() {
        return campusPref;
    }

    public void setCampusPref(InstructorCampusPreference campusPref) {
        this.campusPref = campusPref;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
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


}
