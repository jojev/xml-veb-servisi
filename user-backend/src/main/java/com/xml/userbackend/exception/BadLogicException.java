package main.java.com.xml.userbackend.exception;

public class BadLogicException extends RuntimeException {
    private String message;

    public BadLogicException() {

    }

    public BadLogicException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
