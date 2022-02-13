package main.java.com.xml.userbackend.exception;

public class ConflictException extends RuntimeException {
    private String message;

    public ConflictException() {

    }

    public ConflictException(String message) {
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
