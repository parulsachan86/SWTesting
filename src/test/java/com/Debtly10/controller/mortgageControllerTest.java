package com.Debtly10.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.MortgageUpdateDto;
import com.Debtly10.Services.MortgageService;
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

@ContextConfiguration(classes = {mortgageController.class})
@ExtendWith(SpringExtension.class)
class mortgageControllerTest {
    @Autowired
    private mortgageController mortgageController;

    @MockBean
    private MortgageService mortgageService;

    @Test
    void testAddMortgage() throws Exception {
        when(mortgageService.addMortgage(Mockito.<MortgageRegistrationDTO>any(), Mockito.<Long>any()))
                .thenReturn("Add Mortgage");
        Date issueDate = mock(Date.class);
        when(issueDate.getTime()).thenReturn(10L);
        Date lastPaid = mock(Date.class);
        when(lastPaid.getTime()).thenReturn(10L);
        MortgageRegistrationDTO mortgageRegistrationDTO = new MortgageRegistrationDTO("Product Name", 10.0f, 10.0f, 10.0f,
                issueDate, lastPaid, 10.0f);

        String content = (new ObjectMapper()).writeValueAsString(mortgageRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mortgage/register_mortgage/{cid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Mortgage"));
    }

    @Test
    void testGetMortgageByCustomer() throws Exception {
        when(mortgageService.getMortgageByCustomer(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/mortgage_by_cid/{cid}", 1L);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetMortgageByCustomer2() throws Exception {
        when(mortgageService.getMortgageByCustomer(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/mortgage_by_cid/{cid}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeleteMortgage() throws Exception {
        when(mortgageService.deleteMortgage(Mockito.<Long>any())).thenReturn("Delete Mortgage");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mortgage/delete_mortgage/{mid}", 1L);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Mortgage"));
    }


    @Test
    void testDeleteMortgage2() throws Exception {
        when(mortgageService.deleteMortgage(Mockito.<Long>any())).thenReturn("Delete Mortgage");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mortgage/delete_mortgage/{mid}", 1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Mortgage"));
    }


    @Test
    void testUpdateMortgage() throws Exception {
        when(mortgageService.updateMortgage(Mockito.<MortgageUpdateDto>any(), Mockito.<Long>any()))
                .thenReturn("2020-03-01");

        MortgageUpdateDto mortgageUpdateDto = new MortgageUpdateDto("Product Name", 10.0f, 10.0f, 10.0f, mock(Date.class),
                mock(Date.class), 10.0f);
        mortgageUpdateDto.setInterestRate(0.0f);
        mortgageUpdateDto.setLastPaid(null);
        mortgageUpdateDto.setGivenAmount(0.0f);
        mortgageUpdateDto.setMarketValue(0.0f);
        mortgageUpdateDto.setLeftAmount(0.0f);
        mortgageUpdateDto.setIssueDate(null);
        String content = (new ObjectMapper()).writeValueAsString(mortgageUpdateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/mortgage/update_mortgage/{mid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }


    @Test
    void testGetMortgages() throws Exception {
        when(mortgageService.getAllMortgage()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/get_mortgages");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetMortgages2() throws Exception {
        when(mortgageService.getAllMortgage()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mortgage/get_mortgages");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(mortgageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
