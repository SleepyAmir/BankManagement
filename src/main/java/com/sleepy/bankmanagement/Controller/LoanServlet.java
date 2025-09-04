package com.sleepy.bankmanagement.Controller;

import com.sleepy.bankmanagement.entity.Loan;
import com.sleepy.bankmanagement.entity.LoanStatus;
import com.sleepy.bankmanagement.entity.LoanType;
import com.sleepy.bankmanagement.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "loans", urlPatterns = "/loans")
public class LoanServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoanService loanService = new LoanService();
            req.getSession().setAttribute("loanList", loanService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Find: " + e.getMessage());
        }

        req.getRequestDispatcher("/loan.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoanService loanService = new LoanService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Loan loan = Loan
                    .builder()
                    .loanId(req.getParameter("loanId"))
                    .customerId(req.getParameter("customerId"))
                    .loanType(LoanType.valueOf(req.getParameter("loanType")))
                    .principalAmount(Double.parseDouble(req.getParameter("principalAmount")))
                    .interestRate(Double.parseDouble(req.getParameter("interestRate")))
                    .termInMonths(Integer.parseInt(req.getParameter("termInMonths")))
                    .monthlyPayment(Double.parseDouble(req.getParameter("monthlyPayment")))
                    .remainingBalance(Double.parseDouble(req.getParameter("remainingBalance")))
                    .loanStatus(LoanStatus.valueOf(req.getParameter("loanStatus")))
                    .startDate(dateFormat.parse(req.getParameter("startDate")))
                    .nextPaymentDate(dateFormat.parse(req.getParameter("nextPaymentDate")))
                    .approvedByEmployeeId(req.getParameter("approvedByEmployeeId"))
                    .build();

            loanService.save(loan);
            System.out.println("Loan Saved Successfully");

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