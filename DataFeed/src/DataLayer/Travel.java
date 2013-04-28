package DataLayer;

import Exceptions.ApplicationException;

/**
 *
 * @author hdflournoy
 * Here the program will get the traveling distances from one campus to another.
 * The getTravelDistance function will take as its parameters the campuses the instructor is traveling from and to.
 * It was assumed that the parameters will be the different campusId: 1 for north, 2 for south, 3 for west, and 4 for east
 * These two values were concatenated in order to simplify the "if else if"
 */
public class Travel {

    
    private static double[][] travelCampusDistance = {{0, 1, 2, 3}, {1, 0, 1.5, 2}, {2, 1.5, 0, 2.5}, {3, 2, 2.5,0}};


    
       
    
public static double getTravelDistance(int destinationCampus, int departCampus)throws ApplicationException{

    if(destinationCampus > 4 || destinationCampus<1){
    
        throw new ApplicationException("Invalid Destination Campus ID");
    }
    if(departCampus > 4 || departCampus < 1){
    
        throw new ApplicationException("Invalid Departure Campus ID");
    }
  
   return travelCampusDistance[destinationCampus-1][departCampus-1];
  

}//end getTravelDistance
}//end Travel class
