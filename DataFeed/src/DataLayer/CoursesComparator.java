/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author Valerie
 */
public class CoursesComparator {
    
    public int compare(Courses c1, Courses c2){

    int numberEnrolledCompare= c1.getNumberEnrolled() - c2.getNumberEnrolled();
    
    return numberEnrolledCompare;
    }  //end public int  
}//end class
