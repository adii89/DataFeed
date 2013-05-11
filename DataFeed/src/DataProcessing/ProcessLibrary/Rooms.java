/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataLayer.Building;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Adi
 */
public class Rooms implements ProcessingManager {
  private File _file;
    public Rooms(File f) {
        _file = f;
    }
    @Override
    public void Process() throws ApplicationException {
        //reads "rooms.txt"
        try {
            
            BufferedReader br= new BufferedReader(new FileReader(_file.getPath()));
            String line= br.readLine(); //reads column names
            String columns[] = line.split("\\|");//gets the name of the columns, this will be used when reading preferences.txt
            line= br.readLine(); //reads first line of records
            String delims = " "; //for parsing the Instructor's name
            
            String roomBuild; 
            String previousBuilding= "";
            String previousDepartment ="";
            int roomBuildingID=0;
            int departmentID= 0; 

            while(line!=null){


                String[] room = line.split("\\|");

                int roomN =  Integer.parseInt(room[0].trim());
                roomBuild= room[1].trim();

                int capacity =  Integer.parseInt(room[2].trim());
                String campus = room[3].trim();

                int campusId=0;//1-north, 2-south, 3-east, 4-west

                switch (campus){

                    case "NORTH":
                        campusId=1;
                        break;
                    case "SOUTH":
                        campusId=2;
                        break;
                    case "EAST":
                        campusId=3;
                        break;

                    case "WEST":
                        campusId=4;
                        break;
                }//end switch 

               // boolean m = room[4].trim().equalsIgnoreCase("NO");

                boolean media = true;
                if(room[4].trim().equalsIgnoreCase("NO")){
                    media = false;
                }//end if
                 //Building b = new Building(campusId); 


                 if(!roomBuild.equalsIgnoreCase(previousBuilding)){
                     int buildI=0;
                    Building b= new Building(campusId, buildI);                  
                    b.Insert();
                    roomBuildingID=b.getBuildingID();
                   }//end if


                ////FIRST ASSING THE VALUE OF 0 TO ROOM ID, INSTANTIATE THE OBJECT AND PUT 0 IN ROOM ID, BEFORE THE INSERT CALL A SET METHOD
                int roomId=0;
                DataLayer.Rooms r = new DataLayer.Rooms(roomId, roomN, roomBuildingID,capacity, campus, media);
                r.Insert();
                //r.setRoomId(roomId);

                ///r.insert...EXAMPLE
                System.out.println(r.toString());
                //rooms.add(r);

                    previousBuilding=roomBuild;


                    line= br.readLine();
                }
                //String[] filesArray = {"C:\\rooms.txt",  "C:\\preferences.txt", "C:\\sections.txt", "C:\\enroll.txt"};
               //String pathFile;
              // String changeBuilding;
        } catch (FileNotFoundException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        
    }  
}
