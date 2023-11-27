package com.Debtly10.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.Debtly10.models.BMI;
import org.mockito.MockedStatic;
import com.Debtly10.Services.BMIService;
import org.springframework.http.ResponseEntity;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5)
class bmiControllerSapientGeneratedTest {

    private final BMIService bmiServiceMock = mock(BMIService.class, "bmiService");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private bmiController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${00fc7001-042d-3a33-adc7-ed8ba8dd399e}
    @Test()
    void calculateBMITest() {
        //Arrange Statement(s)
        ResponseEntity<Double> responseEntityMock = mock(ResponseEntity.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
            responseEntity.when(() -> ResponseEntity.ok(Double.parseDouble("0.0"))).thenReturn(responseEntityMock);
            target = new bmiController();
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            doReturn(Double.parseDouble("0.0")).when(bmiServiceMock).calculateBMI(Double.parseDouble("0.0"), Double.parseDouble("0.0"));
            BMI bMI = new BMI(Double.parseDouble("0.0"), Double.parseDouble("0.0"));
            //Act Statement(s)
            ResponseEntity<Double> result = target.calculateBMI(bMI);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock));
                responseEntity.verify(() -> ResponseEntity.ok(Double.parseDouble("0.0")), atLeast(1));
                verify(bmiServiceMock).calculateBMI(Double.parseDouble("0.0"), Double.parseDouble("0.0"));
            });
        }
    }
}
