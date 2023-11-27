package com.Debtly10.Services;

import org.springframework.stereotype.Service;

@Service
public class BMIService {
    public Double calculateBMI(Double h, Double w)
    {
        Double bmi = w / (h*h);
        return bmi;
    }
}
