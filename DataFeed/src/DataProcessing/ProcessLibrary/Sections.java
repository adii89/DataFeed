/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataAccess.Database;
import DataAccess.DatabaseHelper;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This interface implementation is responsible for processing a given file that is selected
 * through the Graphical User Interface
 */
public class Sections implements ProcessingManager{
    private File _file;
    public Sections(File f) {
        _file = f;
    }
    @Override
    public void Process() throws ApplicationException {
        //"sections.txt"
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

            String[] section = line.split("\\|");
            String courseN= section[0].trim();
            String department = section[1].trim();
            String SQL = "SELECT DepartmentId FROM Department WHERE DepartmentName = " + DatabaseHelper.Quote(department);
            Database DB = new Database();
            int DepartmentId;
            try {
                ResultSet rs = DB.SelectSQL(SQL);
                if (rs.next()) {
                    DepartmentId = rs.getInt(1);
                    String cNumber =section[2].trim();
                    int CallNumber = Integer.parseInt(cNumber);
                    String days= section[3];
                    String time = section[4];
                    boolean media = true;
                    if(section[5].trim().equalsIgnoreCase("NO")){
                        media = false;
                    }

                    DataLayer.Sections s= new DataLayer.Sections(courseN, DepartmentId, CallNumber,days, time, media);
                    s.Insert();
                    System.out.println(s.toString());
                } else {
                    System.out.println("The Department For This Section Was not Found!!!");
                    System.out.println("Please Upload Instructors File");
                }
            } catch (SQLException ex) {
                Logger.ErrorLog.LogError(ex);
            } catch (ApplicationException ex) {
                Logger.ErrorLog.LogError(ex);
            }
        } catch (FileNotFoundException ex){
            Logger.ErrorLog.LogError(ex);
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        
    }
}
