package com.eshopper.customerservice1.controller;

import com.eshopper.customerservice1.dto.OrderDTO;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/register")
    public User registerCustomer(@RequestBody User user)
    {
        return customerService.addUser(user);
    }

    @GetMapping("/{customerId}/personalDetails")
    public Optional<User> getCustomerDetails(@PathVariable("customerId") Integer customerId)
    {
        System.out.println("in getCustomerDetails"+customerId);
        return customerService.getUserDetails(customerId) ;
    }

    @GetMapping("/all")
    public List<User> getAllCustomer()
    {
        return (List<User>) customerService.getAllUsers() ;
    }

    @GetMapping("/{customerId}/orderDetails")
    public List<OrderDTO> getCustomerAllOrdersDetails(@PathVariable("customerId") Integer customerId)
    {
        //String orderAPI = "http://localhost:8082/order/"+customerId+"/orderDetails";
        String orderAPI = "http://ORDER-SERVICE1/order/"+customerId+"/orderDetails";
        List<OrderDTO> details = new ArrayList<>();
        try {
            ResponseEntity<List<OrderDTO>> orderServiceResponse = restTemplate.exchange(
                    orderAPI,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OrderDTO>>() {});
            if(orderServiceResponse != null && orderServiceResponse.hasBody()){
                details = orderServiceResponse.getBody();
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return details ;
    }

    @GetMapping("/{customerId}/{orderId}/orderDetails")
    public OrderDTO getCustomerOrderDetails(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId) {
        String orderAPI = "http://ORDER-SERVICE1/order/"+customerId+"/"+orderId+"/details";
        OrderDTO details = new OrderDTO();
        try {
            ResponseEntity<OrderDTO> orderServiceResponse = restTemplate.exchange(
                    orderAPI,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<OrderDTO>() {});
            if(orderServiceResponse != null && orderServiceResponse.hasBody()){
                details = orderServiceResponse.getBody();
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return details ;
    }
}
