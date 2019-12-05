package com.eshopper.customerservice1.controller;

import com.eshopper.customerservice1.exception.CustomerServiceException;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController{
    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    /* Add new customer */
    @PostMapping(value = "/register", produces = "application/json")
    public User registerCustomer(@RequestBody User user) throws CustomerServiceException {
        User user1 = customerService.addUser(user);
        logger.debug("Register new Customer");
        if(user1 == null)
        {
            throw new CustomerServiceException("Unable to add customer", "CustomerController", "registerCustomer");
        }
        return user1;
    }

    /* Get Customer personal details */
    @GetMapping(value = "/{customerId}/personalDetails", produces = "application/json")
    //@ExceptionHandler(CustomerServiceException.class)
    public @ResponseBody ResponseEntity<Optional<User>> getCustomerDetails(@PathVariable("customerId") Integer customerId) throws CustomerServiceException {
        Optional<User> user1 = customerService.getUserDetails(customerId) ;
        logger.debug("Processing request to get customer details for customer Id:"+customerId);
        System.out.println("In customer ===> "+user1.toString());
        if(user1.isEmpty())
        {
            throw new CustomerServiceException("Customer Not found controller", "CustomerController", "getCustomerDetails");
        }
        System.out.println("In customer1 ===> "+user1.toString());
        return ResponseEntity.ok(user1);
    }

    /* Get details of all registered Customer */
    @GetMapping(value = "/all", produces = "application/json")
    public List<User> getAllCustomer() throws CustomerServiceException {
        logger.debug("Get all customer details");
        return (List<User>) customerService.getAllUsers() ;
    }

}
