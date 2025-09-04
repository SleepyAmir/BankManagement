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
@Entity

@Table(name = "account")
public  class Account {

    @Id
    @Column(name = "accountNumber", nullable = false, unique = true)
    @JsonProperty("شماره حساب")
    private String accountNumber;

    @Column(name = "customerId", nullable = false)
    @JsonProperty("شناسه مشتری")
    private String customerId;

    @Column(name = "balance", nullable = false)
    @JsonProperty("موجودی")
    private double balance;

    @Column(name = "dateOpened", nullable = false)
    @JsonProperty("تاریخ افتتاح")
    private Date dateOpened;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JsonProperty("وضعیت")
    private AccountStatus status;

}