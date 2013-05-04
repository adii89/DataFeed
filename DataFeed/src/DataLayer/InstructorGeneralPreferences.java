/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.util.ArrayList;

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
private static ArrayList<String> preferences = new ArrayList<String>();

public InstructorGeneralPreferences(String dept, String fName, String lName, char m, int numSec, int saturd, ArrayList<String> campusPreference) {
    
    Department=dept;
    InstructorFirstName=fName;
    InstructorLastName= lName;
    InstructorMiddleInt = m;
    NumberOfSectionsToTeach=numSec;
    Weekend=saturd;
    preferences=campusPreference;
      
}

    public static ArrayList<String> getPreferences() {
        return preferences;
    }

    public static void setPreferences(ArrayList<String> preferences) {
        InstructorGeneralPreferences.preferences = preferences;
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

@Override 
    public String toString(){
    
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Instructor's Name:           " + getInstructorFirstName() + " "+ 
                    getInstructorMiddleInt()+ " "+ getInstructorLastName() + NEW_LINE);
        i.append("Department:                  " + getDepartment() + NEW_LINE);
        i.append("Number of sections to teach: " + getNumberOfSectionsToTeach() + NEW_LINE);
        i.append("Campus preferences:          " + getPreferences() + NEW_LINE);
       
        
        return i.toString();
    
    }//end toString
    
    
}
