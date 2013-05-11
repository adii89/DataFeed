package DataLayer;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class is used to get data from the static table Campus
 */
public class Campus {
    public static int GetCampusId(String cName) {
        String SQL = "SELECT CampusId FROM Campus WHERE CampusName = " + DatabaseHelper.Quote(cName);
        int CampusId = 0;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()){
                CampusId = rs.getInt(1);
            }
            return CampusId;
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
            return 0;
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
            return 0;
        }
    }
    
    public static String GetCampusName(int cId) {
        String SQL = "SELECT CampusName FROM Campus WHERE CampusId = " + cId;
        String CampusName = "";
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()){
                CampusName = rs.getString(1);
            }
            return CampusName;
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
            return "";
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
            return "";
        }
    }
}
