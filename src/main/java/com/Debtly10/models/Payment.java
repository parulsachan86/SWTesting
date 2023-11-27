package com.Debtly10.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "payment")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Payment {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mortgage getMortgage() {
        return mortgage;
    }

    public void setMortgage(Mortgage mortgage) {
        this.mortgage = mortgage;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private Date date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "mortgage_id")
    private Mortgage mortgage;
}
