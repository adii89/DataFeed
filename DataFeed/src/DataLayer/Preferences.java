package DataLayer;

import DataAccess.Database;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class will hold the values for each professors preferences
 */
public class Preferences {
    private int PreferenceId;
    private int InstructorId;
    private int NumberOfSections;
    private boolean WeekendAvailable;
    
    public Preferences() {
        PreferenceId = 0;
        InstructorId = 0;
        NumberOfSections = 0;
        WeekendAvailable = false;
    }
    
    public int getPreferenceId() {
        return PreferenceId;
    }
    
    public int getInstructorId() {
        return InstructorId;
    }
    
    public void setInstructorId(int iId) {
        InstructorId = iId;
    }
    
    public int getNumberOfSections() {
        return NumberOfSections;
    }
    
    public void setNumberOfSections(int nOfSec) {
        NumberOfSections = nOfSec;
    }
    
    public boolean getWeekendAvilable() {
        return WeekendAvailable;
    }
    
    public int getWeekendAvilableDB() {
        return WeekendAvailable ? 1 : 0;
    }
    
    public void setWeekendAvailable(boolean wAvail) {
        WeekendAvailable = wAvail;
    }
    
    public void Insert() throws ApplicationException {
        if (InstructorId == 0) {
            throw new ApplicationException("Instructor Was Not Specified");
        }
        String SQL = "INSERT INTO dbo.PfuUserPreference (UserId, NumberOfSections, WeekendAvailbility) " + " VALUES(" + getInstructorId() + "," + getNumberOfSections() + "," + getWeekendAvilableDB() + ")";
        Database DB = new Database();
        try {
           PreferenceId = DB.InsertSQL(SQL);
        }
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
    
    public void LoadByInstructorId(int iId){
        String SQL = "SELECT * FROM PfuUserPreference WHERE UserId = " + iId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                PreferenceId = rs.getInt(1);
                InstructorId = rs.getInt(2);
                NumberOfSections = rs.getInt(3);
                WeekendAvailable = rs.getBoolean(4);
            } else {
                PreferenceId = 0;
                InstructorId = 0;
                NumberOfSections = 0;
                WeekendAvailable = false;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}
