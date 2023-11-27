package com.Debtly10.controller;

import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import com.Debtly10.models.BMI;
import org.springframework.test.web.servlet.ResultActions;
import com.Debtly10.Services.BMIService;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5)
@WebMvcTest()
@ContextConfiguration(classes = bmiController.class)
class bmiControllerSapientGeneratedWebMvcTest {

    @Autowired()
    private MockMvc mockMvc;

    @MockBean(name = "bmiService")
    private BMIService bmiServiceMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${00fc7001-042d-3a33-adc7-ed8ba8dd399e}
    @Test()
    void calculateBMITest() throws Exception {
        //Arrange Statement(s)
        doReturn(Double.parseDouble("0.0")).when(bmiServiceMock).calculateBMI(Double.parseDouble("0.0"), Double.parseDouble("0.0"));
        BMI bMI = new BMI(Double.parseDouble("0.0"), Double.parseDouble("0.0"));
        String contentStr = new ObjectMapper().writeValueAsString(bMI);

        //Act Statement(s)
        ResultActions resultActions = this.mockMvc.perform(get("/bmi/calculate").content(contentStr).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        ResponseEntity<Double> responseEntity = ResponseEntity.ok(Double.parseDouble("0.0"));

        //Assert statement(s)
        assertAll("result", () -> {
            resultActions.andExpect(status().is(responseEntity.getStatusCode().value()));
            resultActions.andExpect(content().string(containsString(String.valueOf(responseEntity.getBody()))));
        });
    }

    @SpringBootApplication(scanBasePackageClasses = bmiController.class)
    static class bmiControllerSapientGeneratedWebMvcTestConfig {
    }
}
