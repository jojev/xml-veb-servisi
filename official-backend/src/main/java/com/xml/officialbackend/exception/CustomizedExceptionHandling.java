package main.java.com.xml.officialbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.JAXBException;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<ExceptionResponse> handleExceptions(JAXBException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Xml koji ste poslali se ne poklapa sa šemom.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponse> handleExceptions(ConflictException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
