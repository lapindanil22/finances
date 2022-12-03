package com.example.finances.DTO.responses;

import com.example.finances.DTO.TransactionDTO;

import java.util.List;

public class TransactionResponse {
    private List<TransactionDTO> transactions;

    public TransactionResponse(List<TransactionDTO> transactions){
        this.transactions = transactions;
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transaction) {
        this.transactions = transaction;
    }
}
