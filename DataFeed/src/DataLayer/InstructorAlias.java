/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Valerie  I didn't want to make a critical change in the Instructor class, hence I created this one.
 */
public class InstructorAlias {
    
    private String dept;
    private String name;
    private Preferences instrucPref;
    
public InstructorAlias (String depart, String n, Preferences instructorPref){
    
        dept= depart;
        name=n;
        instrucPref=instructorPref;
            
            }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Preferences getInstrucPref() {
        return instrucPref;
    }

    public void setInstrucPref(Preferences instrucPref) {
        this.instrucPref = instrucPref;
    }


    
}
