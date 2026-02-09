package lucy.exception;

/**
 * Represents an exception specific to the Lucy chatbot application.
 * This exception is thrown when an error occurs while processing
 * user commands or task operations.
 */
public class LucyException extends Exception {
    public LucyException(String message) {
        super(message);
    }
}
