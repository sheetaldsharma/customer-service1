package com.eshopper.customerservice1.controller;

import com.eshopper.customerservice1.dto.OrderDTO;
import com.eshopper.customerservice1.dto.UserDTO;
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
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

//    private static final String TOPIC = "kafka-common";
    private static final String TOPIC = "CUSTOMER_PRODUCER";

    /* Add new customer */
    @PostMapping("/register")
    public User registerCustomer(@RequestBody User user)
    {
        System.out.println("---------------------------------register ");
        logger.trace("In registerCustomer");
        return customerService.addUser(user);
    }

    /* Get CUtomer personal details */
    @GetMapping("/{customerId}/personalDetails")
    public Optional<User> getCustomerDetails(@PathVariable("customerId") Integer customerId)
    {
        System.out.println("---------------------------------get cust details ");
//        logger.trace("in getCustomerDetails"+customerId);
//        System.out.println("in getCustomerDetails"+customerId);
        return customerService.getUserDetails(customerId) ;
    }

    /* Get details of all registered Customer */
    @GetMapping(value = "/all", produces = "application/json")
    public List<User> getAllCustomer()
    {
        System.out.println("---------------------------------get all ");
//        this.kafkaTemplateString.send(TOPIC, "first kafka msg");
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(1);
//        userDTO.setFirstName("AA");
//        this.kafkaTemplate.send(TOPIC, new UserDTO(1, "AA"));

        return (List<User>) customerService.getAllUsers() ;
    }

//    /* Get order details for a Customer */
//    @GetMapping("/{customerId}/order/details")
//    public List<OrderDTO> getCustomerAllOrdersDetails(@PathVariable("customerId") Integer customerId)
//    {
//
////        kafkaTemplate.send(TOPIC, new UserDTO(1, "AA"));
////        kafkaTemplate.send(TOPIC,"hello");
////        System.out.println("after kafka --------------------------------");
////        //* send a msg to kafka /{customerId}/orderDetails
//////        this.kafkaTemplate.send(TOPIC, customerId);
////        //String orderAPI = "http://localhost:8082/order/"+customerId+"/orderDetails";
//        String orderAPI = "http://ORDER-SERVICE1/order/"+customerId+"/orderDetails";
//        List<OrderDTO> details = new ArrayList<>();
//        try {
//            ResponseEntity<List<OrderDTO>> orderServiceResponse = restTemplate.exchange(
//                    orderAPI,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<OrderDTO>>() {});
//            if(orderServiceResponse != null && orderServiceResponse.hasBody()){
//                details = orderServiceResponse.getBody();
//            }
//        } catch (RestClientException e) {
//            e.printStackTrace();
//        }
//        return details ;
//    }
//
//
    /* Get particular detailesc(All included products) of a Order for a Customer */
    @GetMapping("/{customerId}/order/{orderId}/details")
    public OrderDTO getCustomerOrderDetails(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") Integer orderId) {

        //this.kafkaTemplate.send(TOPIC, new UserDTO(1, 1));
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
