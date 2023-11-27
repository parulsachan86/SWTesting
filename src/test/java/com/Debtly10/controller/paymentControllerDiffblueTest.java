package com.Debtly10.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Services.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
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

@ContextConfiguration(classes = {paymentController.class})
@ExtendWith(SpringExtension.class)
class paymentControllerDiffblueTest {
    @Autowired
    private paymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    /**
     * Method under test:
     * {@link paymentController#addMortgage(PaymentRegistrationDTO, Long)}
     */
    @Test
    void testAddMortgage() throws Exception {
        when(paymentService.addPayment(Mockito.<PaymentRegistrationDTO>any(), Mockito.<Long>any()))
                .thenReturn("Add Payment");
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        PaymentRegistrationDTO paymentRegistrationDTO = new PaymentRegistrationDTO(10.0f, date);

        String content = (new ObjectMapper()).writeValueAsString(paymentRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payment/register_payment/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Payment"));
    }

    /**
     * Method under test: {@link paymentController#deletePayment(Long)}
     */
    @Test
    void testDeletePayment() throws Exception {
        doNothing().when(paymentService).deletePayment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/payment/delete_payment/{pid}", 1L);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" payment deleted : 1"));
    }

    /**
     * Method under test: {@link paymentController#deletePayment(Long)}
     */
    @Test
    void testDeletePayment2() throws Exception {
        doNothing().when(paymentService).deletePayment(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/payment/delete_payment/{pid}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(" payment deleted : 1"));
    }

    /**
     * Method under test: {@link paymentController#getPayments()}
     */
    @Test
    void testGetPayments() throws Exception {
        when(paymentService.getAllPayment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/get_payments");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link paymentController#getPayments()}
     */
    @Test
    void testGetPayments2() throws Exception {
        when(paymentService.getAllPayment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/get_payments");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link paymentController#totalDue(PaymentRegistrationDTO, Long)}
     */
    @Test
    void testTotalDue() throws Exception {
        when(paymentService.seeDue(Mockito.<PaymentRegistrationDTO>any(), Mockito.<Long>any())).thenReturn(10.0f);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        PaymentRegistrationDTO paymentRegistrationDTO = new PaymentRegistrationDTO(10.0f, date);

        String content = (new ObjectMapper()).writeValueAsString(paymentRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payment/see_total_due/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }
}
