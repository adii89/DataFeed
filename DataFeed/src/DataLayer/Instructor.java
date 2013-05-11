/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
import Security.Cryptography;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Erik
 */
public class Instructor {
 
private int InstructorId;    
private int UserTypeId;
private int DepartmentId;
private String InstructorFirstName;
private String InstructorLastName;
private char InstructorMiddleInt;
private String Username;
private String Password;
//Preferences
private Preferences Pref;
//Campus Preferences
private CampusPreferences CampusPref;

public Instructor() {
    InstructorId = 0;
    DepartmentId = 0;
    InstructorFirstName = "";
    InstructorLastName = "";
    InstructorMiddleInt = '\0';
    Username = "";
    Password = "";
    Pref = new Preferences();
    CampusPref = new CampusPreferences();
}

    public int getInstructorId() {
        return InstructorId;
    }
    
    public int getDepartment() {
        return DepartmentId;
    }

    public void setDepartment(int Department) {
        DepartmentId = Department;
    }
    
    public String getInstructorFirstName() {
        return InstructorFirstName;
    }

    public void setInstructorFirstName(String InstructorFirstName) {
        this.InstructorFirstName = InstructorFirstName;
    }

    public String getInstructorLastName() {
        return InstructorLastName;
    }

    public void setInstructorLastName(String InstructorLastName) {
        this.InstructorLastName = InstructorLastName;
    }

    public char getInstructorMiddleInt() {
        return InstructorMiddleInt;
    }

    public void setInstructorMiddleInt(char InstructorMiddleInt) {
        this.InstructorMiddleInt = InstructorMiddleInt;
    }
    
    public String getUsername() {
        return Username;
    }
    
    public String getPassword() {
        return Password;
    }
    
    private int getUserTypeId() {
        return UserTypeId;
    }
    
    private String getEncryptedPassword() {
        return Password;
    }
    
    public Preferences getPreferences() {
        return Pref;
    }
    
    public void setPreferences(Preferences p) {
        Pref = p;
    }
    
    public CampusPreferences getCampusPreferences() {
        return CampusPref;
    }
    
    public void setCampusPreferences(CampusPreferences cp) {
        CampusPref = cp;
    }
    
    private void CreateUserCredentials(){
     String uName;
     String emailPadding= "@pfu.edu";
       
     uName = getInstructorFirstName().trim() + "." + getInstructorLastName().trim();
     String SQL = "SELECT COUNT(UserId) FROM PfuUser WHERE Username LIKE " + DatabaseHelper.QuoteFilter(uName);
     Database DB = new Database();
     int UserCount = 0;
     try {
         ResultSet rs = DB.SelectSQL(SQL);
         if (rs.next()){
             UserCount = rs.getInt(1);
         }
         if(!rs.isClosed()) rs.close();
         if (UserCount == 0) {
             Username = uName;
         } else {
             Username = uName + String.valueOf(UserCount);
         }
         Username += emailPadding;
         try {
             Password = Cryptography.Encrypt(Config.Constants.U_PASSWORD);
         } catch (Exception ex) {
             Logger.ErrorLog.LogError(ex);
         }
         UserTypeId = Config.Constants.UserType.INSTRUCTOR;
     } catch (ApplicationException ex) {
         Logger.ErrorLog.LogError(ex);
         return;
     } catch (SQLException ex) {
         Logger.ErrorLog.LogError(ex);
         return;
     }
    }
    
     public void Insert() throws ApplicationException{
         if (InstructorFirstName == "") {
             throw new ApplicationException("Intructor First Name Was Not Specified");
         }
         if (InstructorLastName == "") {
             throw new ApplicationException("Intructor Last Name Was Not Specified");
         }
         if (DepartmentId == 0) {
             throw new ApplicationException("Intructor Department Was Not Specified");
         }
         CreateUserCredentials();
        String SQL = "INSERT INTO dbo.PfuUser (UserTypeId, DepartmentId, UserFirstName, "
                + "UserLastName, UserMiddleInit, Username, Password) " 
                    + " VALUES("+ getUserTypeId() + "," + getDepartment() + "," + DatabaseHelper.Quote(getInstructorFirstName()) + "," + DatabaseHelper.Quote(getInstructorLastName())
                        + "," + DatabaseHelper.Quote(getInstructorMiddleInt()) + "," + DatabaseHelper.Quote(getUsername()) + "," + DatabaseHelper.Quote(getEncryptedPassword()) +")";
        Database DB = new Database();
        try {
           InstructorId = DB.InsertSQL(SQL);
        }
        catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        }
     }

    @Override 
    public String toString(){
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Instructor's Name:           " + getInstructorFirstName() + " "+ 
                    getInstructorMiddleInt()+ " "+ getInstructorLastName() + NEW_LINE);
        i.append("Department ID:                  " + getDepartment() + NEW_LINE);
        i.append("Campus preferences:          " + getPreferences() + NEW_LINE);
        return i.toString();
    
    }
    
    public void LoadByFirstNameAndLastNameAndMiddleInitAndDepartmentId(String fName, String lName, char mInit, int dId){
        String SQL = "SELECT * FROM PfuUser WHERE UserFirstName = " + DatabaseHelper.Quote(fName) + " AND UserLastName = " + DatabaseHelper.Quote(lName) + " AND UserMiddleInit = " + DatabaseHelper.Quote(mInit) + " AND DepartmentId = " + dId;
        Database DB = new Database();
        try {
            ResultSet rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                InstructorId = rs.getInt(1);
                UserTypeId = rs.getInt(2);
                DepartmentId = rs.getInt(3);
                InstructorFirstName = rs.getString(4);
                InstructorLastName = rs.getString(5);
                InstructorMiddleInt = rs.getString(6).charAt(0);
                //We do need username and password stuff
                if (Pref == null) Pref = new Preferences();
                Pref.LoadByInstructorId(InstructorId);
                if (CampusPref == null) CampusPref = new CampusPreferences();
                CampusPref.LoadByInstructorId(InstructorId);
            } else {
                InstructorId = 0;
                DepartmentId = 0;
                InstructorFirstName = "";
                InstructorLastName = "";
                InstructorMiddleInt = '\0';
                Username = "";
                Password = "";
                Pref = null;
                CampusPref = null;
            }
            if (!rs.isClosed()) { rs.close();}
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
        }
    }
}
