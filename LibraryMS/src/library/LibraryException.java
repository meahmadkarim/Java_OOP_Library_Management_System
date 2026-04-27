package library;

/**
 * LibraryException - Custom exception class.
 *
 * OOP Concept: INHERITANCE
 * - Extends RuntimeException (a built-in Java class), inheriting
 *   all exception-handling behavior while adding library-specific context.
 *
 * OOP Concept: ENCAPSULATION
 * - Wraps error codes alongside the message for structured error info.
 */
public class LibraryException extends RuntimeException {

    // ENCAPSULATION: private field with getter
    private final String errorCode;

    /**
     * Constructor: creates a library-specific exception with a code and message.
     *
     * @param errorCode A short code identifying the error type (e.g., "BOOK_NOT_FOUND")
     * @param message   Human-readable description of what went wrong
     */
    public LibraryException(String errorCode, String message) {
        super(message);            // INHERITANCE: calls parent constructor
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * POLYMORPHISM: Overrides getMessage() to include the error code.
     * This is method overriding — a key form of polymorphism.
     */
    @Override
    public String getMessage() {
        return "[" + errorCode + "] " + super.getMessage();
    }
}
