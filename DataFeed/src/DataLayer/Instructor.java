/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Adi
 */
public class Instructor {
    private int _InstructorId;
    private String _InstructorFirstName;
    private String _InstructorLastName;
    private char _InstructorMiddleInt;
    private int NumberOfCoursesToTeach; //preference in number of courses to teach
    
            

public Instructor (int id, String fName, String lName, char mName, int numberCoursesTeach){

_InstructorId = id;
_InstructorFirstName = fName;
_InstructorLastName = lName;
_InstructorMiddleInt = mName;
NumberOfCoursesToTeach = numberCoursesTeach;
}

    public int getInstructorId() {
        return _InstructorId;
    }

    public void setInstructorId(int _InstructorId) {
        this._InstructorId = _InstructorId;
    }

    public String getInstructorFirstName() {
        return _InstructorFirstName;
    }

    public void setInstructorFirstName(String _InstructorFirstName) {
        this._InstructorFirstName = _InstructorFirstName;
    }

    public String getInstructorLastName() {
        return _InstructorLastName;
    }

    public void setInstructorLastName(String _InstructorLastName) {
        this._InstructorLastName = _InstructorLastName;
    }

    public char getInstructorMiddleInt() {
        return _InstructorMiddleInt;
    }

    public void setInstructorMiddleInt(char _InstructorMiddleInt) {
        this._InstructorMiddleInt = _InstructorMiddleInt;
    }

    public int getNumberOfCoursesToTeach() {
        return NumberOfCoursesToTeach;
    }

    public void setNumberOfCoursesToTeach(int NumberOfCoursesToTeach) {
        this.NumberOfCoursesToTeach = NumberOfCoursesToTeach;
    }



}