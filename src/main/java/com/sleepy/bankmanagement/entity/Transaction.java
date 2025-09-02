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
@Entity(name = "transactionEntity")
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transactionId", nullable = false)
    @JsonProperty("شناسه تراکنش")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transactionType", nullable = false)
    @JsonProperty("نوع تراکنش")
    private TransactionType transactionType;

    @Column(name = "amount", nullable = false)
    @JsonProperty("مبلغ")
    private double amount;

    @Column(name = "timestamp", nullable = false)
    @JsonProperty("تاریخ و زمان")
    private Date timestamp;

    @Column(name = "sourceAccountNumber")
    @JsonProperty("شماره حساب مبدا")
    private String sourceAccountNumber;

    @Column(name = "destinationAccountNumber")
    @JsonProperty("شماره حساب مقصد")
    private String destinationAccountNumber;

    @Column(name = "description")
    @JsonProperty("توضیحات")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transactionStatus", nullable = false)
    @JsonProperty("وضعیت تراکنش")
    private TransactionStatus transactionStatus;

    @Column(name = "processedBy")
    @JsonProperty("پردازش شده توسط")
    private String processedBy;
}