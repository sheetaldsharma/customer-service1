package com.eshopper.customerservice1.service;

import com.eshopper.customerservice1.model.Customer;
import com.eshopper.customerservice1.repository.CustomerRepository;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers()
    {
        return (List<Customer>)customerRepository.findAll();
    }

    public Optional<Customer> getCustomerDetails(Integer customerId)
    {
        System.out.println("in service ="+customerId);
        return customerRepository.findById(customerId);
    }

}
