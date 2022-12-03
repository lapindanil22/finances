package com.example.finances.services;

import com.example.finances.models.Account;
import com.example.finances.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public List<Account> getAllByClientId(Integer clientId) {
        return accountRepository.getAllByClientId(clientId);
    }
}
