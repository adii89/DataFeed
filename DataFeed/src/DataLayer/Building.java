///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package DataLayer;
//
import DataAccess.Database;
import Exceptions.ApplicationException;
import java.sql.SQLException;
//
///**
// *
// * @author Valerie
// */
public class Building {
    
    //private int BuildingId;//building where the room is located at
    private int Campus;
    private int buildingId;
    
    
    public Building(int campus, int buildID){
        //BuildingId= building;
        Campus=campus;
        buildingId=buildID;
}//edn Building

//    public int getBuildingId() {
//        return BuildingId;
//    }
//
//    public void setBuildingId(int BuildingId) {
//        this.BuildingId = BuildingId;
//    }

    public int getCampus() {
        return Campus;
    }

    public void setCampus(int Campus) {
        this.Campus = Campus;
    }

    
   public void Insert(){
 
       String SQL;
       SQL = "INSERT INTO dbo.CampusBuilding (CampusId) VALUES(" +  getCampus() + ")";
        Database DB = new Database();
        try {
           buildingId = DB.InsertSQL(SQL);//scope identity
        }//end try
        catch (SQLException ex) {
                    
            Logger.ErrorLog.LogError(ex);
        }//end first catch
        catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        }//end second catch

   
   }//end public void Insert 
   
   public int getBuildingID(){
      
       return buildingId;
      }//end getBuildingID
    
}//end class
