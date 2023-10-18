package e_exceptions;

public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

    public CustomException() {
        super("Custom Exception with default message");
    }
}
