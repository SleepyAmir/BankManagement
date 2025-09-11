package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Entity(name="customerEntity")
@Table(name = "customer")
public class Customer extends Base {

    @Id
    @Column(name = "customerId", nullable = false)
    @JsonProperty("شناسه مشتری")
    private String customerId;

    @Column(name = "firstName", nullable = false)
    @JsonProperty("نام")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @JsonProperty("نام خانوادگی")
    private String lastName;

    @Column(name = "nationalId", nullable = false, unique = true)
    @JsonProperty("کد ملی")
    private String nationalId;

    @Column(name = "dateOfBirth")
    @JsonProperty("تاریخ تولد")
    private LocalDateTime dateOfBirth;

    @Column(name = "phoneNumber")
    @JsonProperty("شماره تلفن")
    private String phoneNumber;

    @Column(name = "registrationDate")
    @JsonProperty("تاریخ ثبت نام")
    private LocalDateTime registrationDate;

    @JsonProperty("فعال است")
    private boolean isActive;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
}