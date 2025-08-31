package com.sleepy.bankmanagement.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Loan {
    @Id
    private String loanId;
    private String customerId;
    private LoanType loanType;
    private double principalAmount;
    private double interestRate;
    private int termInMonths;
    private double monthlyPayment;
    private double remainingBalance;
    private LoanStatus loanStatus;
    private Date startDate;
    private Date nextPaymentDate;
    private String approvedByEmployeeId;



}
