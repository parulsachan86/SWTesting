package com.Debtly10.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "mortgage")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Mortgage {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public float getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(float givenAmount) {
        this.givenAmount = givenAmount;
    }

    public float getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(float leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getLastPaid() {
        return lastPaid;
    }

    public void setLastPaid(Date lastPaid) {
        this.lastPaid = lastPaid;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;


    private float marketValue=-1;

    @Column(nullable = false)
    private float givenAmount = -1;

    @Column(nullable = false)
    private float leftAmount=-1;

    private Date issueDate=null;

    private Date lastPaid = null;

    private float interestRate=-1;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "mortgage", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Payment> paymentList;


}
