package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cardEntity")
@Table(name = "card")
public class Card {

    @Id

    @Column(name = "cardNumber", nullable = false)
    @JsonProperty("شماره کارت")
    private String cardNumber;

    @Column(name = "cardType", nullable = false)
    @JsonProperty("نوع کارت")
    private CardType cardType;

    @Column(name = "cvv", nullable = false)
    @JsonProperty("cvv")
    private String cvv;

    @Column(name = "expiryDate", nullable = false)
    @JsonProperty("تاریخ انقضا")
    private Date expiryDate;

    @Column(name = "cardholderName", nullable = false)
    @JsonProperty("نام صاحب کارت")
    private String cardholderName;

    @Column(name = "linkedAccountNumber", nullable = false)
    @JsonProperty("شماره اکانت وصل شده")
    private String linkedAccountNumber;


    private boolean isActive;
}
