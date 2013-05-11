package Config;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class will only hold static final variables as constants so that they can be referenced
 * in multiple places throughout the codebase.
 */
public class Constants {
    public static final int DB_TIMEOUT = 30;
    public static final String[] Filenames = {"enroll.txt", "instructors.txt", "preferences.txt", "rooms.txt", "sections.txt", "travel.txt"};
    public static final String U_PASSWORD = "password1";
    //UserTypeIds
    public class UserType {
        public static final int ADMINISTRATOR = 1;
        public static final int INSTRUCTOR = 2;
    }
    
}
