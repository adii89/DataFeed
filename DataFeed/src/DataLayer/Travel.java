/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author hdflournoy
 * Here the program will get the traveling distances from one campus to another.
 * The getTravelDistance function will take as its parameters the campuses the instructor is traveling from and to.
 * It was assumed that the parameters will be the different campusId: 1 for north, 2 for south, 3 for west, and 4 for east
 * These two values were concatenated in order to simplify the "if else if"
 */
public class Travel {
public static double getTravelDistance(int destinationCampus, int departCampus){

    
    int travelLocations = Integer.parseInt(Integer.toString(destinationCampus) + Integer.toString(departCampus));
    
    if(travelLocations == 11 || travelLocations==22){

        return 0;}
    else if(travelLocations== 33 || travelLocations==44){
        return 0;
        }
    else if(travelLocations== 12 || travelLocations== 21){
        return 1;
    }
      else if(travelLocations== 13 || travelLocations== 31){
        return 3;
    } 
    else if(travelLocations== 14 || travelLocations== 41){
        return 2;
    }
    
    else if(travelLocations== 23 || travelLocations== 32){
        return 2;
    }
    else if(travelLocations== 24 || travelLocations== 42){
        return 1.5;
    }
    
    else if(travelLocations== 34 || travelLocations== 43){
        return 2.5;
    }
        

    
    return 0;

}//end getTravelDistance
}//end Travel class
