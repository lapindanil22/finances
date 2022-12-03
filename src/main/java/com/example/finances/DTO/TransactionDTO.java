package com.example.finances.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private Date creationDate;
    private Integer amount;
    private String type;
    private Integer senderAccountId;
    private String executionResult;
}
