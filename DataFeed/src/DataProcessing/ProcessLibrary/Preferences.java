/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataLayer.CampusPreferences;
import DataLayer.Department;
import DataLayer.Instructor;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Adi
 */
public class Preferences implements ProcessingManager {
    private File _file;
    public Preferences(File f) {
        _file = f;
    }
    @Override
    public void Process() throws ApplicationException {
        //reads "C:\\preferences.txt"
        try {
            BufferedReader br= new BufferedReader(new FileReader(_file.getPath()));
            //Get Count of lines
            double LineCount = 0;
            double ProcessedCount = 0;
            double Percent = 0;
            while(br.readLine() != null) LineCount++;
            LineCount -= 1;
            
            br = new BufferedReader(new FileReader(_file.getPath()));
            String line= br.readLine(); //reads column names
            String columns[] = line.split("\\|");//gets the name of the columns, this will be used when reading preferences.txt
            line= br.readLine(); //reads first line of records
            String delims = " "; //for parsing the Instructor's name
            
            while(line!=null){
                String[] preference = line.split("\\|");
                String department = preference[0].trim();
                Department Dept = new Department();
                Dept.LoadByDepartmentName(department);
                
                if (Dept.getDepartmentId() == 0){
                    Dept.setDepartmentName(department);
                    try {
                        Dept.Insert();
                    } catch (ApplicationException ex) {
                        Logger.ErrorLog.LogError(ex);
                        continue;
                    }
                }
                
                String fullName = preference[1].trim();//reads full name
                String[] name = fullName.split(delims);//parse the instructor's name using as a delimiter the space
                String lName = name[0].replace(",", "");
                String fName = name[1].replace(",", "");
                char mInitial = name[2].trim().replace(".", "").charAt(0); // s.charAt(0);
                int numSecToTeach = Integer.parseInt(preference[2].trim());
                int v = 3;

                String campusP = columns[3].trim();
                String preferenceCampus;
                ArrayList<String> p = new ArrayList<String>();
                    for(int j = 0; j < 4; j++){
                        preferenceCampus = preference[v].trim();
                        if(preferenceCampus.equalsIgnoreCase("yes")){
                            campusP = columns[v].substring(0, 5).trim();
                            p.add(campusP.trim());                                                          
                        }
                        v++;
                    } // end for checking campus preference

                boolean weekend= false;
                String preferenceW = preference[7].trim();                                                             
                if(preferenceW.equalsIgnoreCase("no")){
                        weekend= true;
                }
                
                Instructor Inst = new Instructor();
                Inst.LoadByFirstNameAndLastNameAndMiddleInitAndDepartmentId(fName, lName, mInitial, Dept.getDepartmentId());
                if (Inst.getInstructorId() == 0) {
                    Inst.setDepartment(Dept.getDepartmentId());
                    Inst.setInstructorFirstName(fName);
                    Inst.setInstructorLastName(lName);
                    Inst.setInstructorMiddleInt(mInitial);
                    try {
                        Inst.Insert();
                    } catch (ApplicationException ex) {
                        Logger.ErrorLog.LogError(ex);
                        continue;
                    }
                }
                
                DataLayer.Preferences P = new DataLayer.Preferences();
                P.LoadByInstructorId(Inst.getInstructorId());
                if(P.getInstructorId() == 0) {
                    P.setInstructorId(Inst.getInstructorId());
                    P.setNumberOfSections(numSecToTeach);
                    P.setWeekendAvailable(weekend);
                    try {
                        P.Insert();
                    } catch (ApplicationException ex) {
                        Logger.ErrorLog.LogError(ex);
                    }
                }
                
                CampusPreferences CampPref = new CampusPreferences();
                CampPref.LoadByInstructorId(Inst.getInstructorId());
                if (CampPref.getInstructorId() == 0) {
                    CampPref.setInstructorId(Inst.getInstructorId());
                    for (int x = 0; x < p.size(); x++){
                        CampPref.addCampusPreferred(p.get(x));
                    }
                    try {
                        CampPref.Insert();
                    } catch (ApplicationException ex) {
                        Logger.ErrorLog.LogError(ex);
                    }
                }
               
                ProcessedCount++;
                Percent = ProcessedCount / LineCount;
                System.out.println(Inst.toString() + "\n\n" + "Processed " + ProcessedCount + " Out Of " + LineCount);
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
