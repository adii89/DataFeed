/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Erik
 */
public class Preferences {
    
private String Department;
private String InstructorName;
private int NumberOfSectionsToTeach;
private int NorthCampus; //either 0 or 1
private int SouthCampus;
private int WestCampus;
private int EastCampus;
private int Weekend;

public Preferences(String dept, String name, int numSec, int n, int s, int w, int e, int saturd) {
    
    Department=dept;
    InstructorName=name;
    NumberOfSectionsToTeach=numSec;
    NorthCampus=n;
    SouthCampus= s;
    WestCampus=w;
    EastCampus=e;       
    Weekend=saturd;
}

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String InstructorName) {
        this.InstructorName = InstructorName;
    }

    public int getNumberOfSectionsToTeach() {
        return NumberOfSectionsToTeach;
    }

    public void setNumberOfSectionsToTeach(int NumberOfSectionsToTeach) {
        this.NumberOfSectionsToTeach = NumberOfSectionsToTeach;
    }

    public int getNorthCampus() {
        return NorthCampus;
    }

    public void setNorthCampus(int NorthCampus) {
        this.NorthCampus = NorthCampus;
    }

    public int getSouthCampus() {
        return SouthCampus;
    }

    public void setSouthCampus(int SouthCampus) {
        this.SouthCampus = SouthCampus;
    }

    public int getWestCampus() {
        return WestCampus;
    }

    public void setWestCampus(int WestCampus) {
        this.WestCampus = WestCampus;
    }

    public int getEastCampus() {
        return EastCampus;
    }

    public void setEastCampus(int EastCampus) {
        this.EastCampus = EastCampus;
    }

    public int getWeekend() {
        return Weekend;
    }

    public void setWeekend(int Weekend) {
        this.Weekend = Weekend;
    }


}
