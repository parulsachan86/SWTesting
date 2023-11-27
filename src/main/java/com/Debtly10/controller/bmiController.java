package com.Debtly10.controller;

import com.Debtly10.Services.BMIService;
import com.Debtly10.models.BMI;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bmi")
public class bmiController {
    @Autowired
    private BMIService bmiService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateBMI(@NotNull @RequestBody BMI bmi)
    {
        Double calculatedBMI = bmiService.calculateBMI(bmi.getHeight(), bmi.getWeight());
        return ResponseEntity.ok(calculatedBMI);
    }
}
