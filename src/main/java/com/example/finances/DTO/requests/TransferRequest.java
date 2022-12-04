package com.example.finances.DTO.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private int receiverAccountId;
    private int senderAccountId;
    private int amount;
    private String secretWord;
}
