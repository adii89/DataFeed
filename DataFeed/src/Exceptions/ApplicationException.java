package Exceptions;

/**
 *
 * @author Adrian Krzeszkiewicz
 * This class will allow us to throw our own exceptions with given message that
 * we will pass to it
 */
public class ApplicationException extends Exception {
    public ApplicationException(String Message){
        super(Message);
    }
}
