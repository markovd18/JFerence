package cz.markovd.jference.exception;

/**
 * Exception to indicate an invalid state of the application to perform requested task.
 *
 * @author <a href="mailto:Markov.David@seznam.cz">David Markov</a>
 * @since 28.2.2021
 */
public class InvalidStateException extends RuntimeException {
    public InvalidStateException() {
    }

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStateException(Throwable cause) {
        super(cause);
    }

    public InvalidStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
