package com.mybankingapp.controllers;

import com.google.gson.*;
import com.mybankingapp.classes.Customer;
import com.mybankingapp.connection.DatabaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.*;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I'm in doGet...");
//        response.setContentType("text/html;charset=UTF-8");
        int id = request.getIntHeader("id");
        DatabaseConnection dbConnection = new DatabaseConnection();

        Customer customer = dbConnection.getCustomer(id);
        int num = customer.getId();
        String name = customer.getName();
        System.out.println("id : " + num + "name : " + name);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString = "";
        jsonString = gson.toJson(customer);
        PrintWriter out = response.getWriter();
        out.println(jsonString);
//        request.setAttribute("data",jsonString);
//        request.getRequestDispatcher("CustomerResult.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("i'm in check2..");
//        BufferedReader reader = request.getReader();
        String jsonString = request.getParameter("jsonString");
        Gson gson = new Gson();
//        String message = org.apache.commons.io.IOUtils.toString(reader);
        System.out.println(jsonString);
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        System.out.println("i'm in check2..");
        System.out.println(jsonObject);
        String jsonstr = "";
        jsonObject.getAsJsonObject().addProperty("balance", "0.0");
        System.out.println("i'm in servlet..");
        jsonstr = gson.toJson(jsonObject);
        Customer customer = gson.fromJson(jsonstr, Customer.class);
        System.out.println(jsonstr);


        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.addCustomer(customer);
        dbConnection.close();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Added Successfuly");
    }
}



//        JsonObject obj = new JsonParser().parse(reader).getAsJsonObject();
