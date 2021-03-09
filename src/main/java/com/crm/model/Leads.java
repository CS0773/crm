package com.crm.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Leads {

    private int id;
    private String userName;
    private String company;
    private String emailId;
    @Column(name="status", nullable=false)
    private LeadStatus status;

    private List<Product> productList;

    public Leads() {
    }


    public Leads(int id) {
        this.id = id;
    }


    public Leads(int id, String userName, String company, String emailId, LeadStatus status) {
        this.id = id;
        this.userName = userName;
        this.company = company;
        this.emailId = emailId;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @OneToMany()
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}