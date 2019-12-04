package com.eshopper.customerservice1.controller;

import com.eshopper.customerservice1.model.User;
import com.eshopper.customerservice1.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }}

    public User getUserTestData1()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user1 = new User();
        user1.setId(1);
        user1.setActive(true);
        user1.setAddress1("AA");
        user1.setAddress2("BB");
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
        user1.setRoleId(6);
        user1.setState("AA");
        user1.setRoleId(1);

        return user1;
    }

    public User getUserTestData2()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        User user2 = new User();
        user2.setId(2);
        user2.setActive(true);
        user2.setAddress1("BB");
        user2.setAddress2("BB");
        user2.setBirthdate(new Date());
        user2.setCity("BB");
        user2.setCountry("BB");
        user2.setEmail("BB@gmail.com");
        user2.setFirstName("BB");
        user2.setLastName("BB");
        user2.setMiddleName("BB");
        user2.setPassword("AA");
        user2.setPhone1(12);
        user2.setPhone2(23);
        user2.setPostalCode(1234);
        user2.setRegistrationDate(new Date());
        user2.setRoleId(1);
        user2.setState("BB");
        user2.setRoleId(1);

        return user2;
    }

    @Test
    public void shouldGetAllCustomer() throws Exception
    {
        List<User> userList = new ArrayList<>();
        userList.add(getUserTestData1());
        userList.add(getUserTestData2());

        given(customerService.getAllUsers()).willReturn(userList);

        mockMvc.perform(get("/customer/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("AA"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldGetCustomerDetails() throws Exception {
        User user = getUserTestData1();

        Optional<User> tempUser = Optional.of(user);
        given(customerService.getUserDetails(1)).willReturn(tempUser);
        mockMvc.perform(get("/customer/{customerId}/personalDetails", user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("AA"))
                .andExpect(jsonPath("id").value(1))
                .andDo(MockMvcResultHandlers.print());
        verify(customerService, times(1)).getUserDetails(user.getId());
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void shouldNotGetCustomerDetails() throws Exception {
        User user = getUserTestData1();

        Optional<User> tempUser = Optional.of(user);
        given(customerService.getUserDetails(1)).willReturn(tempUser);
        mockMvc.perform(get("/customer/{customerId}/personalDetails", user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(customerService, times(1)).getUserDetails(user.getId());
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void shouldRegisterCustomer() throws Exception
    {
        User user = getUserTestData1();
        when(customerService.addUser(user)).thenReturn(user);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

}
