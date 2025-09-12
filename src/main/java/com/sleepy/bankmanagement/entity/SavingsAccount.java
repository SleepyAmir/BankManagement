package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "savings_account")
public class SavingsAccount extends Account {

    @Column(name = "interestRate")
    @JsonProperty("نرخ سود")
    private double interestRate;

    @Column(name = "minimumBalance")
    @JsonProperty("حداقل موجودی")
    private double minimumBalance;

    @Column(name = "maxWithdrawalsPerMonth")
    @JsonProperty("حداکثر برداشت در ماه")
    private int maxWithdrawalsPerMonth;

    @Column(name = "currentMonthWithdrawals")
    @JsonProperty("تعداد برداشت ماه جاری")
    private int currentMonthWithdrawals = 0;

    @Column(name = "lastInterestCalculation")
    @JsonProperty("آخرین محاسبه سود")
    private LocalDateTime lastInterestCalculation;

    @Column(name = "compoundingFrequency")
    @JsonProperty("دوره تسهیم سود")
    private String compoundingFrequency = "MONTHLY";

    @Column(name = "penaltyFeeForMinBalance")
    @JsonProperty("جریمه کسری موجودی")
    private double penaltyFeeForMinBalance;

    @Column(name = "excessWithdrawalFee")
    @JsonProperty("کارمزد برداشت اضافی")
    private double excessWithdrawalFee;
}