package com.example.finances.services;

import com.example.finances.DTO.requests.CashOrderRequest;
import com.example.finances.exceptions.NotEnoughMoneyException;
import com.example.finances.models.Account;
import com.example.finances.models.CashOrder;
import com.example.finances.models.Transaction;
import com.example.finances.repositories.AccountRepository;
import com.example.finances.repositories.CashOrderRepository;
import com.example.finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashOrderService {
    private final CashOrderRepository cashOrderRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CashOrderService(CashOrderRepository cashOrderRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.cashOrderRepository = cashOrderRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<CashOrder> getAll() {
        return cashOrderRepository.findAll();
    }

    public List<CashOrder> getAllByAccountId(Integer accountId) {
        return cashOrderRepository.getAllByAccountId(accountId);
    }

    public void createCashOrder(CashOrderRequest cashOrderRequest) {
        Account account = accountRepository.findById(cashOrderRequest.getAccountId()).get();

        if (cashOrderRequest.getType().equals("DEPOSIT")) {
            account.setBalance(account.getBalance() + cashOrderRequest.getAmount());
        } else if (cashOrderRequest.getType().equals("WITHDRAW")) {
            if (account.getBalance() < cashOrderRequest.getAmount()) {
                throw new NotEnoughMoneyException();
            }
            account.setBalance(account.getBalance() - cashOrderRequest.getAmount());
        }
        CashOrder cashOrder = CashOrder.builder()
                .orderType(cashOrderRequest.getType())
                .amount(cashOrderRequest.getAmount())
                .accountId(account)
                .executionResult("SUCCESS")
                .creationDate(new Date())
                .build();

        Transaction transaction = Transaction.builder()
                .creationDate(new Date())
                .amount(cashOrderRequest.getAmount())
                .type(cashOrderRequest.getType())
                .accountId(account)
                .executionResult("SUCCESS")
                .build();

        accountRepository.save(account);
        cashOrderRepository.save(cashOrder);
        transactionRepository.save(transaction);
    }
}
