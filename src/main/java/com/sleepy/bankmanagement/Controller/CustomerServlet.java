package com.sleepy.bankmanagement.Controller;

import com.sleepy.bankmanagement.entity.Customer;
import com.sleepy.bankmanagement.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "customers", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerService customerService = new CustomerService();
            req.getSession().setAttribute("customerList", customerService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Find: " + e.getMessage());
        }

        req.getRequestDispatcher("/customer.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerService customerService = new CustomerService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Customer customer = Customer
                    .builder()
                    .customerId(req.getParameter("customerId"))
                    .firstName(req.getParameter("firstName"))
                    .lastName(req.getParameter("lastName"))
                    .nationalId(req.getParameter("nationalId"))
                    .dateOfBirth(dateFormat.parse(req.getParameter("dateOfBirth")))
                    .phoneNumber(req.getParameter("phoneNumber"))
                    .registrationDate(new Date())
                    .isActive(Boolean.parseBoolean(req.getParameter("isActive")))
                    .build();

            customerService.save(customer);
            System.out.println("Customer Saved Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Save: " + e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}