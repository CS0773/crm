package com.crm.model;
import javax.persistence.*;


@Entity
@Table(name = "Opportunity")
public class Opportunity {

    private int id;
    private String name;
    private String closedate;

    //   private Menmber member;


    public Opportunity(int id, String name, String closedate) {
        this.id = id;
        this.name = name;
        this.closedate = closedate;
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

    public String getDate() {
        return closedate;
    }

    public void setDate(String closedate) {
        this.closedate = closedate;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", closedate=" + closedate +
                '}';
    }


}