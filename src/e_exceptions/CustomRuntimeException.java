package e_exceptions;

public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException() {
        super("Custom Runtime Exception with default message");
    }
}
