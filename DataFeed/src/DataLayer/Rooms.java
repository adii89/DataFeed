/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import java.sql.SQLException;
import Exceptions.ApplicationException;
import java.sql.ResultSet;

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
    private int BuildingId;
    private int RoomNumber;//room number
    private int Capacity;//room capacity 
    private boolean MediaAvailable; //media room or not, 1 = yes, 0 = no 
    
  public Rooms (){
    RoomId = 0;
    RoomNumber = 0;
    BuildingId = 0;
    Capacity = 0;
    MediaAvailable = false;
  }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int RoomId) {
        this.RoomId = RoomId;
    }

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

    public int getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(int buildingId) {
        this.BuildingId = buildingId;
    }

   public boolean getMedia() {
        return MediaAvailable;
    }
   
   private int getMediaDB() {
       return MediaAvailable ? 1 : 0;
   }

    public void setMedia(boolean Media) {
        this.MediaAvailable = Media;
    }
  
    public void Insert() throws ApplicationException{
        if (BuildingId == 0) {
            throw new ApplicationException("No Building Assigned To The Room!");
        }
        if (RoomNumber == 0) {
            throw new ApplicationException("No Room Number Specified!");
        }
        if (Capacity == 0) {
            throw new ApplicationException("No Capacity Sepcified!");
        }
        String SQL = "INSERT INTO dbo.BuildingRoom (BuildingId, RoomNumber, RoomCapacity, MediaAvailable) VALUES("+ getBuildingId() + "," + getRoomNumber() + "," +  getCapacity() + "," + getMediaDB() + ")";
        Database DB = new Database();
        try {
           RoomId = DB.InsertSQL(SQL);//scope identity  
        }//end try
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {
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
        i.append("Media:       " + getMedia() + NEW_LINE);
        return i.toString();
    }//end toString
  
    public void LoadById(int rId) {
       String SQL = "SELECT * FROM BuildingRoom WHERE RoomId = " + rId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                RoomId = rs.getInt(1);
                BuildingId = rs.getInt(2);
                RoomNumber = rs.getInt(3);
                Capacity = rs.getInt(4);
                MediaAvailable = rs.getBoolean(5);
            } else {
                RoomId = 0;
                BuildingId = 0;
                RoomNumber = 0;
                Capacity = 0;
                MediaAvailable = false;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
   }
   
   public void LoadByBuildingAndName(int bId, int rNumber) {
       String SQL = "SELECT * FROM BuildingRoom WHERE BuildingId = " + bId + " AND RoomNumber = " + getRoomNumber();
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                RoomId = rs.getInt(1);
                BuildingId = rs.getInt(2);
                RoomNumber = rs.getInt(3);
                Capacity = rs.getInt(4);
                MediaAvailable = rs.getBoolean(5);
            } else {
                RoomId = 0;
                BuildingId = 0;
                RoomNumber = 0;
                Capacity = 0;
                MediaAvailable = false;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
   }
}
