/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.util.*;

/**
 *
 * @author Valerie
 */
public class RoomComparator implements Comparator<Rooms> {
    
public int compare(Rooms r1, Rooms r2){

    int capacityCompare= r1.getCapacity() - r2.getCapacity();
    
    return capacityCompare;
    

}  //end public int  
    
}//end class
