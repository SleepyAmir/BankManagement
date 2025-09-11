package com.sleepy.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sleepy.bankmanagement.entity.enums.ChequeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "chequeEntity")
@Table(name = "cheque")
@NamedQueries({
        @NamedQuery(name = "FindByChequeNumber", query = "select c from chequeEntity c where c.chequeNumber=:chequeNumber"),
        @NamedQuery(name = "FindPendingCheques", query = "select c from chequeEntity c where c.status=:status"),
        @NamedQuery(name = "FindChequesByAccount", query = "select c from chequeEntity c where c.drawerAccountNumber=:accountNumber")
})
public class Cheque extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "chequeNumber", nullable = false, unique = true, length = 20)
    @JsonProperty("شماره چک")
    private String chequeNumber;

    @Column(name = "drawerAccountNumber", nullable = false, length = 20)
    @JsonProperty("شماره حساب صادرکننده")
    private String drawerAccountNumber;

    @Column(name = "payeeName", nullable = false, length = 100)
    @JsonProperty("نام ذینفع")
    private String payeeName;

    @Column(name = "amount", nullable = false)
    @JsonProperty("مبلغ")
    private double amount;

    @Column(name = "issueDate", nullable = false)
    @JsonProperty("تاریخ صدور")
    private LocalDateTime issueDate;

    @Column(name = "dueDate")
    @JsonProperty("تاریخ سررسید")
    private LocalDate dueDate;

    @Column(name = "clearanceDate")
    @JsonProperty("تاریخ تسویه")
    private LocalDateTime clearanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JsonProperty("وضعیت چک")
    private ChequeStatus status;

    @Column(name = "bankName", length = 100)
    @JsonProperty("نام بانک")
    private String bankName;

    @Column(name = "branchCode", length = 10)
    @JsonProperty("کد شعبه")
    private String branchCode;

    @Column(name = "description", length = 500)
    @JsonProperty("توضیحات")
    private String description;

    @Column(name = "processedByEmployeeId", length = 20)
    @JsonProperty("پردازش شده توسط کارمند")
    private String processedByEmployeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clearanceTransactionId")
    private Transaction clearanceTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processedByEmployeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employee processedByEmployee;
}