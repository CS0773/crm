package com.crm.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Opportunity")
public class Opportunity {

    private int id;
    private String name;
    private Date date;

    //   private Menmber member;


    public Opportunity(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Opportunity() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    } */

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", date=" + date +
                '}';
    }


}