package com.example.finances.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private Integer accountNumber;
    private Integer amount;
    private String accountType;
    private Date openingDate;
    private Date expirationDate;
}
