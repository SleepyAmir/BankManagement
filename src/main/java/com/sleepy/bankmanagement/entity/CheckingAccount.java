package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "checking_account")
public class CheckingAccount extends Account {

    @Column(name = "monthlyMaintenanceFee")
    @JsonProperty("کارمزد ماهانه نگهداری")
    private double monthlyMaintenanceFee;

    @Column(name = "freeTransactionLimit")
    @JsonProperty("حد تراکنش رایگان")
    private int freeTransactionLimit;

    @Column(name = "checkBookFee")
    @JsonProperty("کارمزد دسته چک")
    private double checkBookFee;
}