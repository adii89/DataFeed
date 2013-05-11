package DataLayer;

import DataAccess.Database;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class will hold the values for the campus preferences
 */
public class CampusPreferences {
    private int InstructorId;
    private ArrayList<String> CampusPreferred;
    
    public CampusPreferences() {
        InstructorId = 0;
        CampusPreferred = new ArrayList<String>();
    }
    
    public int getInstructorId() {
        return InstructorId;
    }
    
    public void setInstructorId(int iId) {
        InstructorId = iId;
    }
    
    public ArrayList<String> getCampusPreferred() {
        return CampusPreferred;
    }
    
    public void addCampusPreferred(String c){
        CampusPreferred.add(c);
    }
    
    public void Insert() throws ApplicationException {
        if (InstructorId == 0) {
            throw new ApplicationException("Instructor Was Not Specified");
        }
        if (CampusPreferred.size() == 0) {
            throw new ApplicationException("There Are No Campus Preferences Specified");
        }
        String BaseSQL = "INSERT INTO PfuUserCampusPreference (UserId, CampusId) VALUES (" + getInstructorId();
        String SQL = "";
        int CampusId = 0;
        Database DB = new Database();
        try {
            for (String campus : CampusPreferred) {
                CampusId = Campus.GetCampusId(campus);
                if (CampusId > 0) {
                    SQL = BaseSQL + "," + CampusId + ")";
                    DB.InsertSQL(SQL);
                }
            }
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    public void LoadByInstructorId(int iId) {
        String SQL = "SELECT * FROM PfuUserCampusPreference WHERE UserId = " + iId;
         Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                InstructorId = rs.getInt(1);
                addCampusPreferred(Campus.GetCampusName(rs.getInt(2)));
                while(rs.next()){
                    addCampusPreferred(Campus.GetCampusName(rs.getInt(2)));
                }
            } else {
                InstructorId = 0;
                CampusPreferred = new ArrayList<String>();
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}
