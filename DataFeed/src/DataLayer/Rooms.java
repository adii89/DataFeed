/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import java.sql.SQLException;
import Exceptions.ApplicationException;

/**
 *
 * @author hdflournoy
 * The objects pertaining to this class will be constructed with the input from the rooms.txt file
 * 
 * 
 * ///ADD FUNCTION TO DELETE, INSERT AND UPDATE IN EACH CLASS
 */
public class Rooms {
    ///ADD ROOM ID
    private int RoomId;
    private int RoomNumber;//room number
    //private String building;//building where the room is located at
    
    private int buildingId;
    private int Capacity;//room capacity 
    private String Campus; //campus is either north south east or west (maybe we call them 1/2/3/4)
    private boolean Media; //media room or not, 1 = yes, 0 = no 
   // private int scopeIdentity;
    
    
  public Rooms (int RooId, int rNum, int buildId, int cap, String camp, boolean med){
  RoomId=RooId;
  RoomNumber= rNum;
  buildingId= buildId;
  Capacity= cap;
  Campus = camp;
  Media= med;
      
  }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int RoomId) {
        this.RoomId = RoomId;
    }

//    public int getScopeIdentity() {
//        return scopeIdentity;
//    }
//
//    public void setScopeIdentity(int scopeIdentity) {
//        this.scopeIdentity = scopeIdentity;
//    }
  
  
  

    public int getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(int RoomNumber) {
        this.RoomNumber = RoomNumber;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String Campus) {
        this.Campus = Campus;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

   public boolean getMedia() {
        return Media;
    }
   
   private int getMediaDB() {
       return Media ? 1 : 0;
   }

    public void setMedia(boolean Media) {
        this.Media = Media;
    }
  
    public void Insert(){
    ///i added th throws statements...????
        String SQL;
<<<<<<< HEAD
        SQL = "INSERT INTO dbo.BuildingRoom (BuildingId, RoomNumber, RoomCapacity, MediaAvailable) VALUES("+ getBuildingId() + "," + getRoomNumber() + "," +  getCapacity() + "," + getMediaDB() + ")";
=======
        SQL = "INSERT INTO dbo.BuildingRoom (BuildingId, RoomNumber, RoomCapacity, MediaAvailable) "
                + "VALUES("+ getBuildingId()+","+ getRoomNumber() + ","+ getCapacity() +","+ getMedia() + ")";
>>>>>>> 07c633ec574952c90ae098cee8c7ff835a84ff32
        Database DB = new Database();
        try {
           RoomId = DB.InsertSQL(SQL);//scope identity  
        }//end try
        catch (SQLException ex) {
              ////
           ///ERROR HANDLING FUNCTION
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {
            ////
            Logger.ErrorLog.LogError(ex);
        }//end second catch

    
    }//end public void Insert
    @Override 
    public String toString(){
    
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Room number: " + getRoomNumber() + NEW_LINE);
        i.append("Building ID:    " + getBuildingId() + NEW_LINE);
        i.append("Capacity:    " + getCapacity() + NEW_LINE);
        i.append("Campus:      " + getCampus() + NEW_LINE);
        i.append("Media:       " + getMedia() + NEW_LINE);
        
        return i.toString();
    
    }//end toString
  
}
