package com.example.finances.DTO.requests;

import lombok.Data;

@Data
public class CashOrderRequest {
    private String type;
    private int amount;
    private int accountId;
}
