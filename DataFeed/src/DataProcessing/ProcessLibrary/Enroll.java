/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.ProcessLibrary;

import Exceptions.ApplicationException;
import Interfaces.ProcessingManager;
import java.io.File;

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
        
    }
}
