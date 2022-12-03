package com.example.finances.DTO.responses;

import com.example.finances.DTO.AccountDTO;

import java.util.List;

public class AccountResponse {
    private List<AccountDTO> accounts;

    public AccountResponse(List<AccountDTO> accounts){
        this.accounts = accounts;
    }

    public List<AccountDTO> getAllAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> account) {
        this.accounts = account;
    }
}
