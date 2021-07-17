package cz.markovd.jference.exception;

/**
 * Generic runtime exception indicating some error in the application.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 17.7.2021
 */
public class SystemException extends RuntimeException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
