package com.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    private  int id;
    private String accname;
    private int accno;
    //private Leads lead;

    public Member() {
    }

    public Member(int id, String accname, int accno) {
        this.id = id;
        this.accname = accname;
        this.accno = accno;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public int getAccno() {
        return accno;
    }

    public void setAccno(int accno) {
        this.accno = accno;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", accname='" + accname + '\'' +
                ", accno=" + accno +
                '}';
    }
}
