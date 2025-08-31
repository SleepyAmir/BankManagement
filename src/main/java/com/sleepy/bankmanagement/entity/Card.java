package com.sleepy.bankmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table
public class Card {
@Id
    private String cardNumber;
    private CardType cardType;
    private String cvv;
    private Date expiryDate;
    private boolean isActive;
    private String cardholderName;
    private String linkedAccountNumber;
}
