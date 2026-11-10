package school.facade;

/** Thrown when a user tries an operation without the required permission. */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}
