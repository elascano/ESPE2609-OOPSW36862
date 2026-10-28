package exceptions;

public class InvalidPaymentException extends ValidationException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
