package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity(name = "cardEntity")
@Table(name = "card")
public class Card  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    private Long id;

    @Column(name = "cardNumber", nullable = false, unique = true, length = 20)
    @JsonProperty("شماره کارت")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "cardType", nullable = false)
    @JsonProperty("نوع کارت")
    private CardType cardType;

    @Column(name = "cvv", nullable = false, length = 4)
    @JsonProperty("cvv")
    private String cvv;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiryDate", nullable = false)
    @JsonProperty("تاریخ انقضا")
    private Date expiryDate;

    @Column(name = "cardholderName", nullable = false, length = 100)
    @JsonProperty("نام صاحب کارت")
    private String cardholderName;

    @Column(name = "linkedAccountNumber", nullable = false, length = 20)
    @JsonProperty("شماره اکانت وصل شده")
    private String linkedAccountNumber;

    @Column(name = "isActive")
    private boolean isActive;


    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}