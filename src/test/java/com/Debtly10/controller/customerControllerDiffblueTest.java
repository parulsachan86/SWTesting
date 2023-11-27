package com.Debtly10.controller;

import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.CustomerRegistrationDTO;
import com.Debtly10.DTOS.CustomerUpdateDto;
import com.Debtly10.Services.CustomerService;
import com.Debtly10.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {customerController.class})
@ExtendWith(SpringExtension.class)
class customerControllerDiffblueTest {
    @Autowired
    private customerController customerController;

    @MockBean
    private CustomerService customerService;

    /**
     * Method under test:
     * {@link customerController#addCustomer(CustomerRegistrationDTO)}
     */
    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setLastName("Doe");
        customer.setFirstName("Jane");
        customer.setEmail("jane.doe@example.org");

        when(customerService.addCustomer(Mockito.<CustomerRegistrationDTO>any())).thenReturn(customer);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/customer/register_customer")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new CustomerRegistrationDTO("jane.doe@example.org", "Jane", "Doe", "42 Main St", "Contact")));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(customer.toString()));
    }

    /**
     * Method under test: {@link customerController#getCustomer(Long)}
     */
    @Test
    void testGetCustomer3() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setContact("Contact");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setMortgageList(new ArrayList<>());
        when(customerService.getCustomer(Mockito.<Long>any())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers/{id}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"contact\":\"Contact\",\"address\":\"42"
                                        + " Main St\",\"id\":1,\"mortgageList\":[]}"));
    }

    /**
     * Method under test: {@link customerController#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() throws Exception {
        when(customerService.deleteCustomer(Mockito.<Long>any())).thenReturn("Delete Customer");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/delete_customer/{id}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Customer"));
    }

    /**
     * Method under test: {@link customerController#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer2() throws Exception {
        when(customerService.deleteCustomer(Mockito.<Long>any())).thenReturn("Delete Customer");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/delete_customer/{id}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Customer"));
    }

    /**
     * Method under test:
     * {@link customerController#updateCustomer(CustomerUpdateDto, Long)}
     */
    @Test
    void testUpdateCustomer() throws Exception {
        when(customerService.updateCustomer(Mockito.<CustomerUpdateDto>any(), Mockito.<Long>any()))
                .thenReturn("2020-03-01");

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto("Jane", "Doe", "jane.doe@example.org", "Contact",
                "42 Main St");
        customerUpdateDto.setFirstname((String) "");
        customerUpdateDto.setAddress((String) "");
        customerUpdateDto.setContact((String) "");
        customerUpdateDto.setEmail((String) "");
        customerUpdateDto.setLastname((String) "");
        String content = (new ObjectMapper()).writeValueAsString(customerUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/customer/update_customer/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    /**
     * Method under test: {@link customerController#getCustomer()}
     */
    @Test
    void testGetCustomer() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link customerController#getCustomer()}
     */
    @Test
    void testGetCustomer2() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/get_customers");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
