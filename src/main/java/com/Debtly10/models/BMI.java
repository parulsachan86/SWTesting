package com.Debtly10.models;

public class BMI {
    private Double height;
    private Double weight;

    public BMI() {
    }

    public BMI(Double height, Double weight) {
        this.height = height;
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }
}
