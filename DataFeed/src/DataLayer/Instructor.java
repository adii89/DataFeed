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
    private String _Department;
    private Preferences _InstrucPref; //
    // private Preferences instrucPref;
            

public Instructor (int id, String fName, String lName, char mName, String dep, Preferences instPref){

_InstructorId = id;
_InstructorFirstName = fName;
_InstructorLastName = lName;
_InstructorMiddleInt = mName;
_Department= dep;
_InstrucPref=instPref;


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

    public String getDepartment() {
        return _Department;
    }

    public void setDepartment(String _Department) {
        this._Department = _Department;
    }

    public Preferences getInstrucPref() {
        return _InstrucPref;
    }

    public void setInstrucPref(Preferences _InstrucPref) {
        this._InstrucPref = _InstrucPref;
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



}