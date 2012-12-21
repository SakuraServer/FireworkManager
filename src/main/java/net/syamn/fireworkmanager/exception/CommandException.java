/**
 * FireworkManager - Package: net.syamn.fireworkmanager.exception
 * Created: 2012/12/21 17:54:36
 */
package net.syamn.fireworkmanager.exception;

/**
 * CommandException (CommandException.java)
 * @author syam(syamn)
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = -3454686761533405445L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
