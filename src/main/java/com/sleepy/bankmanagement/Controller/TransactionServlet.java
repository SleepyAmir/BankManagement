package com.sleepy.bankmanagement.Controller;

import com.sleepy.bankmanagement.entity.Transaction;
import com.sleepy.bankmanagement.entity.TransactionStatus;
import com.sleepy.bankmanagement.entity.TransactionType;
import com.sleepy.bankmanagement.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "transactions", urlPatterns = "/transactions")
public class TransactionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TransactionService transactionService = new TransactionService();
            req.getSession().setAttribute("transactionList", transactionService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Find: " + e.getMessage());
        }

        req.getRequestDispatcher("/transaction.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TransactionService transactionService = new TransactionService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Transaction transaction = Transaction
                    .builder()
                    .transactionId(req.getParameter("transactionId"))
                    .transactionType(TransactionType.valueOf(req.getParameter("transactionType")))
                    .amount(Double.parseDouble(req.getParameter("amount")))
                    .timestamp(new Date())
                    .sourceAccountNumber(req.getParameter("sourceAccountNumber"))
                    .destinationAccountNumber(req.getParameter("destinationAccountNumber"))
                    .description(req.getParameter("description"))
                    .transactionStatus(TransactionStatus.valueOf(req.getParameter("transactionStatus")))
                    .processedBy(req.getParameter("processedBy"))
                    .build();

            transactionService.save(transaction);
            System.out.println("Transaction Saved Successfully");

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