package com.mybankingapp.classes;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    @Expose
    private double balance;

    //getters and setters for each attribute

    public Customer(int id, String name, String email, String address, String phone, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static Customer getCustomer(int id, List<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address
                + ", phone=" + phone + ", balance=" + balance + "]";
    }
}

