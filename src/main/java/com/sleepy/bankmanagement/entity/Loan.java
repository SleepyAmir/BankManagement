package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "loanEntity")
@Table(name = "loan")
public class Loan {

    @Id
    @Column(name = "loanId", nullable = false)
    @JsonProperty("شناسه وام")
    private String loanId;

    @Column(name = "customerId", nullable = false)
    @JsonProperty("شناسه مشتری")
    private String customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "loanType", nullable = false)
    @JsonProperty("نوع وام")
    private LoanType loanType;

    @Column(name = "principalAmount", nullable = false)
    @JsonProperty("مبلغ اصلی")
    private double principalAmount;

    @Column(name = "interestRate", nullable = false)
    @JsonProperty("نرخ سود")
    private double interestRate;

    @Column(name = "termInMonths", nullable = false)
    @JsonProperty("مدت بازپرداخت (ماه)")
    private int termInMonths;

    @Column(name = "monthlyPayment", nullable = false)
    @JsonProperty("قسط ماهانه")
    private double monthlyPayment;

    @Column(name = "remainingBalance")
    @JsonProperty("مانده بدهی")
    private double remainingBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "loanStatus", nullable = false)
    @JsonProperty("وضعیت وام")
    private LoanStatus loanStatus;

    @Column(name = "startDate")
    @JsonProperty("تاریخ شروع")
    private Date startDate;

    @Column(name = "nextPaymentDate")
    @JsonProperty("تاریخ قسط بعدی")
    private Date nextPaymentDate;

    @Column(name = "approvedByEmployeeId")
    @JsonProperty("تایید شده توسط کارمند")
    private String approvedByEmployeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee approvedByEmployee;
}