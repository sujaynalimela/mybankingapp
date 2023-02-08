package com.mybankingapp.classes;

import java.util.Date;

public class Transactions {
    private int id;
    private double amount;
    private String type;
    private Date date;

    //getters and setters for each attribute

    public Transactions(int id, double amount, String type, Date date) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", type=" + type
                + ", date=" + date + "]";
    }
}

