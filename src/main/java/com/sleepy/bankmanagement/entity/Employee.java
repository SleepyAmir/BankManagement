package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sleepy.bankmanagement.entity.enums.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "employeeEntity")
@Table(name = "employee")
public class Employee extends Base {

    @Id
    @Column(name = "employeeId", nullable = false)
    @JsonProperty("شناسه کارمند")
    private String employeeId;

    @Column(name = "firstName", nullable = false)
    @JsonProperty("نام")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @JsonProperty("نام خانوادگی")
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    @JsonProperty("نام کاربری")
    private String username;

    @Column(name = "password", nullable = false)
    @JsonProperty("رمز عبور")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "accessLevel", nullable = false)
    @JsonProperty("سطح دسترسی")
    private AccessLevel accessLevel;

    @JsonProperty("فعال است")
    private boolean isActive;

    @OneToMany(mappedBy = "processedByEmployee", fetch = FetchType.LAZY)
    private List<Transaction> processedTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "approvedByEmployee", fetch = FetchType.LAZY)
    private List<Loan> approvedLoans = new ArrayList<>();

    @OneToMany(mappedBy = "processedByEmployee", fetch = FetchType.LAZY)
    private List<Cheque> processedCheques = new ArrayList<>();
}