package com.Debtly10.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customer")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public Customer() {
    }

    @Column(name="email")
    private String email;

    @Column(name="contact",nullable = false,unique = true)
    private String contact;

    @Column(name="address",nullable = false)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Mortgage> mortgageList;

    public Customer(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("contact") String contact,
            @JsonProperty("address") String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.address = address;
    }

//    public Customer(Long id){
//        this.id=id;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Mortgage> getMortgageList() {
        return mortgageList;
    }

    public void setMortgageList(List<Mortgage> mortgageList) {
        this.mortgageList = mortgageList;
    }
}
