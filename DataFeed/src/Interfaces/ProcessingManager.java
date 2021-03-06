package Interfaces;

import Exceptions.ApplicationException;
import javax.swing.JTextArea;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This interface has only one method to process a file
 */
public interface ProcessingManager {
    void Process() throws ApplicationException;
}
