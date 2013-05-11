/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataLayer.Building;
import DataLayer.Campus;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

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
            BufferedReader br = new BufferedReader(new FileReader(_file.getPath()));
            //Get Count of lines
            double LineCount = 0;
            double ProcessedCount = 0;
            double Percent = 0;
            while(br.readLine() != null) LineCount++;
            LineCount -= 1;
            
            br = new BufferedReader(new FileReader(_file.getPath()));
            String line = br.readLine(); //reads column names
            line = br.readLine(); //reads first line of records
            String roomBuilding; 

            while(line!=null){
                String[] room = line.split("\\|");
                int roomNo =  Integer.parseInt(room[0].trim());
                roomBuilding = room[1].trim();
                int capacity =  Integer.parseInt(room[2].trim());
                int campusId = Campus.GetCampusId(room[3].trim());
                boolean Media = room[4].trim().equalsIgnoreCase("NO");
               
                Building Build = new Building();
                Build.LoadByCampusAndName(campusId, roomBuilding);
                
                if (Build.getBuildingID() == 0) {
                    Build.setCampus(campusId);
                    Build.setBuildingName(roomBuilding);
                    Build.Insert();
                }

                DataLayer.Rooms roomObj = new DataLayer.Rooms();
                roomObj.LoadByBuildingAndName(Build.getBuildingID(), roomNo);
                if(roomObj.getRoomId() == 0){
                    roomObj.setBuildingId(Build.getBuildingID());
                    roomObj.setRoomNumber(roomNo);
                    roomObj.setCapacity(capacity);
                    roomObj.setMedia(Media);
                    try {
                        roomObj.Insert();
                    } catch (ApplicationException ex){
                        Logger.ErrorLog.LogError(ex);
                    }
                }
                ProcessedCount++;
                Percent = ProcessedCount / LineCount;
                System.out.println(roomObj.toString() + "\n\n" + "Processed " + ProcessedCount + " Out Of " + LineCount);
                System.out.println(Percent * 100 + " % Completed");
                line= br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }  
}
