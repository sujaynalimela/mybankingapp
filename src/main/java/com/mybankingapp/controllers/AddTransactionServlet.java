package com.mybankingapp.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mybankingapp.classes.Customer;
import com.mybankingapp.classes.Transactions;
import com.mybankingapp.connection.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AddTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I'm in doGet...");
//        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        DatabaseConnection dbConnection = new DatabaseConnection();
        List<Transactions> transactions = dbConnection.getTransactions(id);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString = "";
        jsonString = gson.toJson(transactions);
        Customer customer = dbConnection.getCustomer(id);
        Gson gson2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String balance = gson2.toJson(customer);
        System.out.println(balance);
        System.out.println(jsonString);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String bothJson = "["+jsonString+","+balance+"]";
        response.getWriter().write(bothJson);
//        request.setAttribute("data",jsonString);
//        request.getRequestDispatcher("CustomerResult.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("i'm in TransServlet..");
//        BufferedReader reader = request.getReader();
        String jsonString = request.getParameter("jsonString");
        Gson gson = new Gson();
        Transactions transaction = gson.fromJson(jsonString, Transactions.class);
        int id = transaction.getId();
        String type = transaction.getType();
        System.out.println("id : " + id + "name : " + type);


        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.addTransaction(transaction);
        dbConnection.close();

        PrintWriter out = response.getWriter();
        out.println("<h1>transaction recorded successfully.....</h1>");
    }
}
