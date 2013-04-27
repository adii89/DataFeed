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
    
    private static double[][] travelCampusDistance = {{0, 1, 2, 3}, {1, 0, 1.5, 2}, {2, 1.5, 0, 2.5}, {3, 2, 2.5,0}};

//     travelCampusDistance[1][1]=0;
//     travelCampusDistance[2][2]=0;
//     travelCampusDistance[3][3]=0;
//     travelCampusDistance[4][4]=0;
//     travelCampusDistance[1][2]=1;
//     travelCampusDistance[2][1]=1;
//     travelCampusDistance[1][3]=3;
//     travelCampusDistance[3][1]=3;
//     travelCampusDistance[1][4]=2;
//     travelCampusDistance[4][1]=0;
//     travelCampusDistance[2][3]=2;
//     travelCampusDistance[3][2]=0;
//     travelCampusDistance[2][4]=1.5;
//     travelCampusDistance[4][2]=1.5;
//     travelCampusDistance[3][4]=2.5;
//     travelCampusDistance[4][3]=2.5;
    
       
    
public static double getTravelDistance(int destinationCampus, int departCampus){

  
   return travelCampusDistance[destinationCampus][departCampus];
  
//        int travelLocations = Integer.parseInt(Integer.toString(destinationCampus) + Integer.toString(departCampus));
//
//        if(travelLocations == 11 || travelLocations==22){
//
//            return 0;}
//        else if(travelLocations== 33 || travelLocations==44){
//            return 0;
//            }
//        else if(travelLocations== 12 || travelLocations== 21){
//            return 1;
//        }
//          else if(travelLocations== 13 || travelLocations== 31){
//            return 3;
//        } 
//        else if(travelLocations== 14 || travelLocations== 41){
//            return 2;
//        }
//
//        else if(travelLocations== 23 || travelLocations== 32){
//            return 2;
//        }
//        else if(travelLocations== 24 || travelLocations== 42){
//            return 1.5;
//        }
//
//        else if(travelLocations== 34 || travelLocations== 43){
//            return 2.5;
//        }
//
//
//
//        return 0;

}//end getTravelDistance
}//end Travel class
