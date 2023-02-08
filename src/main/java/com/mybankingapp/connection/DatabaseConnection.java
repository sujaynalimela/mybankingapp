package com.mybankingapp.connection;

import com.mybankingapp.classes.Customer;
import com.mybankingapp.classes.Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private Connection conn;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "");
        } catch (Exception e) {
            System.out.println("am here...");
            System.out.println(e);
        }
    }


    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addCustomer(Customer customer) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (id, name, email, address, phone, balance) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhone());
            stmt.setDouble(6, customer.getBalance());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("am here2...");
            System.out.println(e);
        }
    }

    public Customer getCustomer(int id) {
        Customer customer = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("yayy..");
                customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("phone"), rs.getDouble("balance"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return customer;
    }


    public void updateCustomer(Customer customer) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET name=?, email=?, phone=?, balance=? WHERE id=?");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            stmt.setDouble(4, customer.getBalance());
            stmt.setInt(5, customer.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addTransaction(Transactions transaction) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transactions (id, amount, type, date) VALUES (?,?,?,?)");
            stmt.setInt(1, transaction.getId());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getType());
            stmt.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
            stmt.executeUpdate();
            int id = transaction.getId();
            Customer customer = getCustomer(id);
            if (transaction.getType().equals("deposit")) {
                customer.setBalance(customer.getBalance() + transaction.getAmount());
            } else if (transaction.getType().equals("withdraw")) {
                customer.setBalance(customer.getBalance() - transaction.getAmount());
            }
            updateCustomer(customer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public List<Transactions> getTransactions(int customerId) {
        List<Transactions> transactions = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transactions(rs.getInt("id"),rs.getDouble("amount"), rs.getString("type"), rs.getDate("date")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return transactions;
    }
}


//    public Customer getCustomer(int customerId) {
//        try {
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE id=?");
//            stmt.setInt(1, customerId);
//            ResultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                Customer customer = new Customer();
//                customer.setId(resultSet.getInt("id"));
//                customer.setName(resultSet.getString("name"));
//                customer.setEmail(resultSet.getString("email"));
//                customer.setPhone(resultSet.getString("phone"));
//                customer.setBalance(resultSet.getDouble("balance"));
//                return customer;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }

