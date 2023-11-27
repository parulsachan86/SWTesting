package com.Debtly10.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BMIService.class})
@ExtendWith(SpringExtension.class)
class BMIServiceDiffblueTest {
    @Autowired
    private BMIService bMIService;

    /**
     * Method under test: {@link BMIService#calculateBMI(Double, Double)}
     */
    @Test
    void testCalculateBMI() {
        assertEquals(0.1d, bMIService.calculateBMI(10.0d, 10.0d).doubleValue());
        assertEquals(40.0d, bMIService.calculateBMI(0.5d, 10.0d).doubleValue());
        assertEquals(40.0d, bMIService.calculateBMI(-0.5d, 10.0d).doubleValue());
        assertEquals(Double.NaN, bMIService.calculateBMI(Double.NaN, 10.0d).doubleValue());
    }
}
