package org.example.testExample.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The global exceptions' handler. This class handles all the exceptions for each controller.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles User class exceptions
     * @param ex the exception
     * @return ResponseEntity
     */
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundUser(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Company class exceptions
     * @param ex the exception
     * @return ResponseEntity
     */
    @ExceptionHandler({CompanyNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundCompany(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Employee class exceptions
     * @param ex then exception
     * @return ResponseEntity
     */
    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundEmployee(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

}
