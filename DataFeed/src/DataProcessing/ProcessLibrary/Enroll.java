/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataLayer.Courses;
import DataLayer.Department;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Adi
 */
public class Enroll implements ProcessingManager  {
    private File _file;
    public Enroll(File f) {
        _file = f;
    }
    @Override
    public void Process() throws ApplicationException {
        try {
            BufferedReader br= new BufferedReader(new FileReader(_file.getPath()));
            double LineCount = 0;
            double ProcessedCount = 0;
            double Percent = 0;
            while(br.readLine() != null) LineCount++;
            LineCount -= 1;
            
            br = new BufferedReader(new FileReader(_file.getPath()));
            String line= br.readLine(); //reads column names
            String columns[] = line.split("\\|");//gets the name of the columns, this will be used when reading preferences.txt
            line= br.readLine(); //reads first line of records
            while (line != null) {
                String[] course = line.split("\\|");
                String courseNum = course[0].trim();
                String department = course[1].trim();
                int numEnrol = Integer.parseInt(course[2].trim());
                
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
                
                Courses c = new Courses();
                c.LoadByDepartmentAndCourseNumber(Dept.getDepartmentId(), courseNum);
                if (c.getPreviousEnrollmentId() == 0){
                    c.setCourseNumber(courseNum);
                    c.setDepartment(Dept.getDepartmentId());
                    c.setNumberEnrolled(numEnrol);
                    try {
                        c.Insert();
                    } catch (ApplicationException ex) {
                        Logger.ErrorLog.LogError(ex);
                        continue;
                    }
                }
                ProcessedCount++;
                Percent = ProcessedCount / LineCount;
                System.out.println(c.toString() + "\n\n" + "Processed " + ProcessedCount + " Out Of " + LineCount);
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
