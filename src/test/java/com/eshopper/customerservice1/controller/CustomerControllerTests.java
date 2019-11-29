package com.eshopper.customerservice1.controller;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    CustomerController customerController;

    @MockBean
    CustomerService customerService;

    @Test
    public void shouldGetAllCustomer() throws Exception
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

        given(customerService.getAllUsers()).willReturn(userList);

        mockMvc.perform(get("/customer/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("AA"))
                .andDo(MockMvcResultHandlers.print());
    }
}
