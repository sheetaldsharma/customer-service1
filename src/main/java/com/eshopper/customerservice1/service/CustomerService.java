package com.eshopper.customerservice1.service;

import com.eshopper.customerservice1.exception.CustomerServiceException;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    public List<User> getAllUsers() throws CustomerServiceException {
        List<User> userList = null;
        userList = customerRepository.findAll();
        if(userList.size() == 0)
        {
            throw new CustomerServiceException("No customer data available");
        }
        return userList;
    }

    public Optional<User> getUserDetails(Integer customerId) throws CustomerServiceException {
        Optional<User> user1 = Optional.empty();
        user1 = customerRepository.findById(customerId);
        System.out.println("In service ===> "+user1.toString());
        if(user1.isEmpty())
        {
            throw new CustomerServiceException("Customer Not found");
        }
        System.out.println("In service1 ===> "+user1.toString());
        return user1;
    }

    public User addUser(User user) throws CustomerServiceException {
        User user1 = null;
        try {
            user1 = customerRepository.save(user);
        }
        catch (Exception ex)
        {
            throw new CustomerServiceException("Unable to add new Customer");
        }
        return user1;
    }

}
