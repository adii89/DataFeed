/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataProcessing.ProcessLibrary.*;
import DataProcessing.ProcessLibrary.Preferences;
import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.File;

/**
 *
 * @author Adi
 */
public class ProcessingProvider implements ProcessingManager {
    private File _file;
    public ProcessingProvider(File fName) {
        _file = fName;
    }
    
    public void SetFile(File fName) {
        _file = fName;
    }
    @Override
    public void Process() {
        boolean bError = false;
        try {
            switch(_file.getName().toLowerCase()) {
                case "enroll.txt":
                    Enroll processEnroll = new Enroll(_file);
                    processEnroll.Process();
                case "preferences.txt":
                    Preferences processPreferences = new Preferences(_file);
                    processPreferences.Process();
                case "rooms.txt":
                    Rooms processRooms = new Rooms(_file);
                    processRooms.Process();
                case "sections.txt":
                    Sections processSections = new Sections(_file);
                    processSections.Process();
            }
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
             bError = true;
        } catch(Exception ex) {
            Logger.ErrorLog.LogError(ex);
             bError = true;
        }
    }
    
}
