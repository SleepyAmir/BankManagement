package com.sleepy.bankmanagement.Controller;

import com.sleepy.bankmanagement.entity.Account;
import com.sleepy.bankmanagement.entity.AccountStatus;
import com.sleepy.bankmanagement.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "accounts", urlPatterns = "/accounts")
public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountService accountService = new AccountService();
            req.getSession().setAttribute("accountList", accountService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Find: " + e.getMessage());
        }

        req.getRequestDispatcher("/account.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountService accountService = new AccountService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Account account = Account
                    .builder()
                    .accountNumber(req.getParameter("accountNumber"))
                    .customerId(req.getParameter("customerId"))
                    .balance(Double.parseDouble(req.getParameter("balance")))
                    .dateOpened(dateFormat.parse(req.getParameter("dateOpened")))
                    .status(AccountStatus.valueOf(req.getParameter("status")))
                    .build();

            accountService.save(account);
            System.out.println("Account Saved Successfully");

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