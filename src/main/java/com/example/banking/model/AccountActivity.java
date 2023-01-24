package com.example.banking.model;

import javax.persistence.*;

@Entity
public class AccountActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String activityType;
    private int value;
    private String date;

    @ManyToOne
    private Account account;

    public AccountActivity() {
    }

    public AccountActivity(String activityType, int value, Account account, String date) {
        this.activityType = activityType;
        this.value = value;
        this.account = account;
        this.date = date;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
