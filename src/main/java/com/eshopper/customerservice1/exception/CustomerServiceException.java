package com.eshopper.customerservice1.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class CustomerServiceException extends Exception {
    private String errorMessage;

    public CustomerServiceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomerServiceException() {
        super();
    }
}
