package com.eshopper.customerservice1.service;

import com.eshopper.customerservice1.dto.ProductDTO;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.repository.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    public List<User> getAllUsers()
    {
        return customerRepository.findAll();
    }

    public Optional<User> getUserDetails(Integer customerId)
    {
        return customerRepository.findById(customerId);
    }

    public User addUser(User user)
    {
        return customerRepository.save(user);
    }

}
