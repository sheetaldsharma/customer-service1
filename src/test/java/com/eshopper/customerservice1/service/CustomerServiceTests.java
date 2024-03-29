package com.eshopper.customerservice1.service;

import com.eshopper.customerservice1.exception.CustomerServiceException;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTests {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;



    public User getUserTestData1()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user1 = new User();
        user1.setId(1);
        user1.setPassword("AA");
        user1.setActive(true);
        user1.setRoleId(1);
        user1.setFirstName("AA");
        user1.setMiddleName("AA");
        user1.setLastName("AA");
        user1.setBirthdate(new Date());
        user1.setAddress1("AA");
        user1.setAddress2("BB");
        user1.setCity("AA");
        user1.setState("AA");
        user1.setCountry("AA");
        user1.setPostalCode(1234);
        user1.setEmail("AA@gmail.com");
        user1.setPhone1(12);
        user1.setPhone2(23);
        user1.setRegistrationDate(new Date());

        return user1;

    }

    public User getUserTestData2()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user2 = new User();
        user2.setId(2);
        user2.setPassword("AA");
        user2.setActive(true);
        user2.setRoleId(1);
        user2.setFirstName("AA");
        user2.setMiddleName("AA");
        user2.setLastName("AA");
        user2.setBirthdate(new Date());
        user2.setAddress1("AA");
        user2.setAddress2("BB");
        user2.setCity("AA");
        user2.setState("AA");
        user2.setCountry("AA");
        user2.setPostalCode(1234);
        user2.setEmail("AA@gmail.com");
        user2.setPhone1(12);
        user2.setPhone2(23);
        user2.setRegistrationDate(new Date());

        return user2;

    }

    public User getUserTestData1ForException()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user1 = new User();
        user1.setId(1);
        user1.setPassword("AA");
        user1.setActive(true);
        user1.setRoleId(14);
        user1.setFirstName("AA");
        user1.setMiddleName("AA");
        user1.setLastName("AA");
        user1.setBirthdate(new Date());
        user1.setAddress1("AA");
        user1.setAddress2("BB");
        user1.setCity("AA");
        user1.setState("AA");
        user1.setCountry("AA");
        user1.setPostalCode(1234);
        user1.setEmail("AA@gmail.com");
        user1.setPhone1(12);
        user1.setPhone2(23);
        user1.setRegistrationDate(new Date());

        return user1;

    }

    @Test
    public void shouldGetAllCustomer() throws CustomerServiceException {
        List<User> userList = new ArrayList<>();
        userList.add(getUserTestData1());
        userList.add(getUserTestData2());

        given(customerRepository.findAll()).willReturn(userList);
        List<User> actualUserList = customerService.getAllUsers();
        assertThat(userList.size()).isEqualTo(2);
        assertThat(actualUserList.size()).isEqualTo(userList.size());
    }


    @Test
    public void shouldGetCustomerDetails() throws CustomerServiceException {
        User expectedUser = getUserTestData1();
        when(customerRepository.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = customerService.getUserDetails(expectedUser.getId());
        assertNotNull(actualUser);
        assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    public void shouldRegisterCustomer() throws CustomerServiceException {
        User user = getUserTestData1();
        when(customerRepository.save(any(User.class))).thenReturn(user);
        User actualUser = customerService.addUser(user);
        assertEquals(actualUser, user);
    }

    @Test
    public void shouldThrowExceptionForGetCustomerDetails() throws CustomerServiceException {

//        Assertions.assertThrows(CustomerServiceException.class, ()->{
//            customerService.getUserDetails(10);
//        });

        Exception exception = assertThrows(
                CustomerServiceException.class,
                () -> customerService.getUserDetails(10));

        assertTrue(exception.getMessage().contentEquals("Customer Not found"));

    }

//    @Test
//    public void shouldThrowExceptionForRegisterCustomer()
//    {
//        User user = getUserTestData1ForException();
//        Exception exception = assertThrows(
//                CustomerServiceException.class,
//                () -> customerRepository.save(user));
//        System.out.println("exception.getMessage()"+exception.getMessage());
//        //assertTrue(exception.getMessage().contentEquals("Unable to add new Customer"));
//    }


    @Test
    public void shouldThrowExceptionForGetAllCustomer() throws CustomerServiceException {

        Exception exception = assertThrows(
                CustomerServiceException.class,
                () -> customerService.getAllUsers());

        assertTrue(exception.getMessage().contentEquals("No customer data available"));

    }
}
