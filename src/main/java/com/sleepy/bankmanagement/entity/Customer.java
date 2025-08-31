package com.sleepy.bankmanagement.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name="customerEntity")
@Table
public class Customer {

    @Id
    private String customerId;

    private String firstName;

    private String lastName;

    private String nationalId;

    private Date dateOfBirth;

    private String phoneNumber;

    private Date registrationDate;

    private boolean isActive;




}
