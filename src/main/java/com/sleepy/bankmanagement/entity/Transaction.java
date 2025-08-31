package com.sleepy.bankmanagement.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    private String transactionId;
    private TransactionType transactionType;
    private double amount;
    private Date timestamp;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String description;
    private TransactionStatus transactionStatus;
    private String processedBy;
}
