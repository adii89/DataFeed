
package Test;

import javax.swing.SwingUtilities;
import UserInterface.*;

/**
 *
 * @author Test class to test GUI
 */
public class TestGUI {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new BaseUI());
    }
}
