package com.eshopper.customerservice1.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class CustomerServiceException extends Exception {
    private String errorMessage;
    private String moduleName;
    private String functionName;

    public CustomerServiceException(String errorMessage, String moduleName, String functionName) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.moduleName = moduleName;
        this.functionName = functionName;
    }

    public CustomerServiceException() {
        super();
    }
}
