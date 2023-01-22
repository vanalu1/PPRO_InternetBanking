package com.example.bootintro.model;

import javax.persistence.*;

@Entity
public class AccountActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String activityType;
    private int value;

    @ManyToOne
    private Account account;

    public AccountActivity() {
    }

    public AccountActivity(String activityType, int value, Account account) {
        this.activityType = activityType;
        this.value = value;
        this.account = account;
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
}
