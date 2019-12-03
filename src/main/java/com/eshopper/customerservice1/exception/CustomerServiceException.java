package com.eshopper.customerservice1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer details not found")
public class CustomerServiceException extends RuntimeException {
}
