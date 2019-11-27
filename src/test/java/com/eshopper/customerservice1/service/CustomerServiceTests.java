package com.eshopper.customerservice1.service;

import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTests {
    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    public void setup() throws Exception
    {
        

    }

    @Test
    void shouldCreateNewCustomer()
    {

    }

    @Test
    void shouldDeleteExistingCustomer()
    {

    }

    @Test
    public void shouldGiveAllCustomerDetails()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user = new User();
        user.setId(1);
        user.setActive(true);
        user.setAddress1("AA");
        user.setAddress2("BB");
        user.setBirthdate(new Date());
        user.setCity("AA");
        user.setCountry("AA");
        user.setEmail("AA@gmail.com");
        user.setFirstName("AA");
        user.setLastName("AA");
        user.setMiddleName("AA");
        user.setPassword("AA");
        user.setPhone1(12);
        user.setPhone2(23);
        user.setPostalCode(1234);
        user.setRegistrationDate(new Date());
        user.setRoleId(1);
        user.setState("AA");
        user.setRoleId(1);

        User user1 = new User();
        user1.setActive(true);
        user1.setAddress1("AA");
        user1.setAddress2("BB");
        user1.setId(1);
        user1.setBirthdate(new Date());
        user1.setCountry("AA");
        user1.setEmail("AA@gmail.com");
        user1.setLastName("AA");
        user1.setMiddleName("AA");
        user1.setPassword("AA");
        user1.setPhone1(12);
        user1.setCity("AA");
        user1.setPhone2(23);
        user1.setFirstName("AA");
        user1.setPostalCode(1234);
        user1.setRegistrationDate(new Date());
        user1.setRoleId(1);
        user1.setState("AA");
        user1.setRoleId(1);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        Mockito.when(customerRepository.findAll()).thenReturn(userList);
        assertEquals(2, userList.size());
    }

    @Test
    void shouldGiveDetailsForACustomer()
    {

    }

    @Test
    void shouldFetchSingleCustomerDetails()
    {

    }

}
