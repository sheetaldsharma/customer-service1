package com.eshopper.customerservice1.controller;

import com.eshopper.customerservice1.dto.OrderDTO;
import com.eshopper.customerservice1.dto.UserDTO;
import com.eshopper.customerservice1.exception.CustomerServiceException;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.service.CustomerService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController{
    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    /* Add new customer */
    @PostMapping("/register")
    public User registerCustomer(@RequestBody User user)
    {
        logger.debug("CustomerController - Create new user");
        return customerService.addUser(user);
    }

    /* Get Customer personal details */
    @GetMapping("/{customerId}/personalDetails")
    public ResponseEntity<Optional<User>> getCustomerDetails(@PathVariable("customerId") Integer customerId)
    {
        logger.debug("Received request to get customer details for customer Id:"+customerId);
        return ResponseEntity.ok().body(customerService.getUserDetails(customerId)) ;
    }

    /* Get details of all registered Customer */
    @GetMapping(value = "/all", produces = "application/json")
    public List<User> getAllCustomer()
    {
        logger.debug("CustomerController - In getAllUsers");
        return (List<User>) customerService.getAllUsers() ;
    }

}
