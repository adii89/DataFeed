/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import DataLayer.Department;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

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
            BufferedReader br = new BufferedReader(new FileReader(_file.getPath()));
            //Get Count of lines
            double LineCount = 0;
            double ProcessedCount = 0;
            double Percent = 0.0;
            while(br.readLine() != null) LineCount++;
            LineCount -= 1;
            
            br = new BufferedReader(new FileReader(_file.getPath()));
            String line= br.readLine(); //reads column names
            String columns[] = line.split("\\|");//gets the name of the columns, this will be used when reading preferences.txt
            
            
            
            line= br.readLine(); //reads first line of records
            
            

            while (line != null) {
                String[] section = line.split("\\|");
                String courseN= section[0].trim();
                String department = section[1].trim();
                int callNumber = Integer.parseInt(section[2].trim());
                String days= section[3];
                String time = section[4];
                boolean media = section[5].equalsIgnoreCase("no");

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
                
                DataLayer.Sections Sec = new DataLayer.Sections();
                Sec.LoadByCallNumber(callNumber);
                
                if (Sec.getSectionId() == 0) {
                    Sec.setDept(Dept.getDepartmentId());
                    Sec.setCourseNumber(courseN);
                    Sec.setCallNumber(callNumber);
                    Sec.setDays(days);
                    Sec.setTime(time);
                    Sec.setMedia(media);
                    try {
                        Sec.Insert();
                    } catch (ApplicationException ex){
                        Logger.ErrorLog.LogError(ex);
                        continue;
                    }
                }
                ProcessedCount++;
                Percent = ProcessedCount / LineCount;
                System.out.println(Sec.toString() + "\n\n" + "Processed " + ProcessedCount + " Out Of " + LineCount);
                System.out.println(Percent * 100 + " % Completed");
                line= br.readLine();
            }
        } catch (FileNotFoundException ex){
            Logger.ErrorLog.LogError(ex);
        } catch (IOException ex) {
            Logger.ErrorLog.LogError(ex);
        }
        
    }
}
