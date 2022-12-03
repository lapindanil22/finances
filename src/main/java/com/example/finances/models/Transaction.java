package com.example.finances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;

    @ManyToOne
    @JoinColumn(name = "cash_order_id", referencedColumnName = "cash_order_id")
    private CashOrder cashOrderId;

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Date creationDate;
    @Column
    private Integer amount;
    @Column
    private String type;
    @Column
    private Integer senderAccountId;
    @Column
    private String executionResult;
}

