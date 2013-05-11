///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package DataLayer;
//
import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;
//
///**
// *
// * @author Valerie
// */
public class Building {
    
    //private int BuildingId;//building where the room is located at
    private int BuildingId;
    private int CampusId;
    private String BuildingName;
    
    public Building() {
        CampusId = 0;
        BuildingId = 0;
        BuildingName = null;
    }

    public int getCampus() {
        return CampusId;
    }

    public void setCampus(int Campus) {
        this.CampusId = Campus;
    }
    
    public int getBuildingID(){
        return BuildingId;
    }//end getBuildingID
    
    public String getBuldingName(){
        return BuildingName;
    }
    
    public void setBuildingName(String bName) {
        BuildingName = bName;
    }
    
   public void Insert() throws ApplicationException{
        if (CampusId == 0) {
            throw new ApplicationException("No Campus Assigned to the Building!");
        }
        if (BuildingName == "") {
            throw new ApplicationException("Building Has No Name Assigned!");
        }
        String SQL =  "INSERT INTO dbo.CampusBuilding (CampusId, BuildingName) VALUES(" +  getCampus() + "," + DatabaseHelper.Quote(getBuldingName()) + ")";
        Database DB = new Database();
        try {
           BuildingId = DB.InsertSQL(SQL);//scope identity
        }//end try
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        }//end second catch
   }//end public void Insert 
   
   public void LoadById(int bId) {
       String SQL = "SELECT * FROM CampusBuilding WHERE BuildingId = " + bId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                BuildingId = rs.getInt(1);
                CampusId = rs.getInt(2);
                BuildingName = rs.getString(3);
            } else {
                BuildingId = 0;
                CampusId = 0;
                BuildingName = null;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
   }
   
   public void LoadByCampusAndName(int cId, String bName) {
       String SQL = "SELECT * FROM CampusBuilding WHERE CampusId = " + cId + " AND BuildingName = " + DatabaseHelper.Quote(bName);
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                BuildingId = rs.getInt(1);
                CampusId = rs.getInt(2);
                BuildingName = rs.getString(3);
            } else {
                BuildingId = 0;
                CampusId = 0;
                BuildingName = null;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
   }
    
}//end class
