package main.java.com.xml.officialbackend.exception;

public class BadCredentialException extends RuntimeException {
    private String message;

    public BadCredentialException() {

    }

    public BadCredentialException(String message) {
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
