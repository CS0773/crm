package com.crm.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name="activity_type", nullable=false)
    private ActivityType activityType;

    @OneToOne
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private ActivityStatus status;

    private String dueDate;

    @OneToOne
    private Leads leadAccountName;
    private String comments;


    public Activity() {
    }

    public Activity(int id, ActivityType activityType, User assignedTo, ActivityStatus status, String dueDate, Leads leadAccountName, String comments) {
        this.id = id;
        this.activityType = activityType;
        this.assignedTo = assignedTo;
        this.status = status;
        this.dueDate = dueDate;
        this.leadAccountName = leadAccountName;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getDueDate()  {
        return dueDate;
    }

    public void setDueDate(String dueDate)  {
        this.dueDate = dueDate;
    }

    public Leads getLeadAccountName() {
        return leadAccountName;
    }

    public void setAccountName(Leads leadAccountName) {
        this.leadAccountName = leadAccountName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType='" + activityType + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", status='" + status + '\'' +
                ", dueDate=" + dueDate +
                ", leadAccountName='" + leadAccountName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
