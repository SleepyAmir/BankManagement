package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sleepy.bankmanagement.entity.enums.ChequeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "chequeEntity")
@Table(name = "cheque")
@NamedQueries({
        @NamedQuery(name = "FindByChequeNumber", query = "select c from chequeEntity c where c.chequeNumber=:chequeNumber"),
        @NamedQuery(name = "FindPendingCheques", query = "select c from chequeEntity c where c.status=:status"),
        @NamedQuery(name = "FindChequesByAccount", query = "select c from chequeEntity c where c.drawerAccountNumber=:accountNumber")
})
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "chequeNumber", nullable = false, unique = true, length = 20)
    private String chequeNumber;

    @Column(name = "drawerAccountNumber", nullable = false, length = 20)
    private String drawerAccountNumber;

    @Column(name = "payeeName", nullable = false, length = 100)
    private String payeeName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "issueDate", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "clearanceDate")
    private LocalDateTime clearanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ChequeStatus status;

    @Column(name = "bankName", length = 100)
    private String bankName;

    @Column(name = "branchCode", length = 10)
    private String branchCode;

    @Column(name = "description", length = 500)
    private String description;


    @Column(name = "processedByEmployeeId", length = 20, insertable = false, updatable = false)
    private String processedByEmployeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clearanceTransactionId")
    private Transaction clearanceTransaction;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processedByEmployeeId")
    private Employee processedByEmployee;
}