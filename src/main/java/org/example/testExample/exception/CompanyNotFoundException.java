package org.example.testExample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
