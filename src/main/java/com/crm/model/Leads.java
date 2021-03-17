package com.crm.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Leads {

    private int id;
    private String firstName;
    private String lastName;
    private String company;
    private String emailId;
    private String phoneNumber;
    @Column(name="status", nullable=false)
    private LeadStatus status;

    private List<Product> productList;

    public Leads() {
    }


    public Leads(int id) {
        this.id = id;
    }

    public Leads(int id, String firstName, String lastName, String company, String emailId, String phoneNumber, LeadStatus status, List<Product> productList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.productList = productList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    @Enumerated(EnumType.STRING)
    public LeadStatus getStatus() {
        return status;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }

    @ManyToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}