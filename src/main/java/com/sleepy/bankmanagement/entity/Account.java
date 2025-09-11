package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sleepy.bankmanagement.entity.enums.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account extends Base {

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
    private LocalDateTime dateOpened;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JsonProperty("وضعیت")
    private AccountStatus status;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    private Customer customer;
}