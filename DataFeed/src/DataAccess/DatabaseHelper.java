package DataAccess;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This is a helper class that will let us pass in single parameter and, for instance
 * enclose it in "'" for SQL server to accept a string value
 */
public class DatabaseHelper {
    public static String Quote(Object obj) {
        String retVal = obj.toString();
        retVal = "'" + retVal + "'";
        return retVal;
    }
}
