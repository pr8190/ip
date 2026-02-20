package exception;

/**
 * Exception class that handles errors while executing the Mike chatbot
 * when the user inputs wrong commands.
 */
public class MikeException extends Exception {
    /* The description of the error. */
    private String exception;

    /**
     * The constructor of the MikeException
     *
     * @param exception
     */
    MikeException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return exception;
    }
}
