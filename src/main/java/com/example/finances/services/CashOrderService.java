package com.example.finances.services;

import com.example.finances.DTO.requests.CashOrderRequest;
import com.example.finances.exceptions.ExpiredAccountException;
import com.example.finances.exceptions.NotEnoughMoneyException;
import com.example.finances.exceptions.WrongSecretWordException;
import com.example.finances.models.Account;
import com.example.finances.models.CashOrder;
import com.example.finances.models.Transaction;
import com.example.finances.repositories.AccountRepository;
import com.example.finances.repositories.CashOrderRepository;
import com.example.finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashOrderService {
    private final CashOrderRepository cashOrderRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CashOrderService(CashOrderRepository cashOrderRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.cashOrderRepository = cashOrderRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<CashOrder> getAll() {
        return cashOrderRepository.findAll();
    }

    public List<CashOrder> getAllByAccountId(Integer accountId) {
        return cashOrderRepository.getAllByAccountId(accountId);
    }

    public void createCashOrder(CashOrderRequest cashOrderRequest) {
        Account account = accountRepository.findById(cashOrderRequest.getAccountId()).get();

        Date now = new Date();

        CashOrder cashOrder = CashOrder.builder()
                .orderType(cashOrderRequest.getType())
                .amount(cashOrderRequest.getAmount())
                .accountId(account)
                .creationDate(now)
                .build();

        Transaction transaction = Transaction.builder()
                .creationDate(now)
                .amount(cashOrderRequest.getAmount())
                .type(cashOrderRequest.getType())
                .accountId(account)
                .build();

        if (!bCryptPasswordEncoder.matches(
                cashOrderRequest.getSecretWord(),
                account.getClientId().getSecretWord()
        )) {
            cashOrder.setExecutionResult("WRONG_SECRET_WORD");
            transaction.setExecutionResult("WRONG_SECRET_WORD");
            cashOrderRepository.save(cashOrder);
            transactionRepository.save(transaction);
            throw new WrongSecretWordException("Неверное кодовое слово");
        }

        if (now.after(account.getExpirationDate()))
            throw new ExpiredAccountException("Срок действия аккаунта истек");

        if (cashOrderRequest.getType().equals("DEPOSIT"))
            account.setBalance(account.getBalance() + cashOrderRequest.getAmount());

        if (cashOrderRequest.getType().equals("WITHDRAW")) {
            if (account.getBalance() < cashOrderRequest.getAmount())
                throw new NotEnoughMoneyException("Недостаточно средств на счету");
            account.setBalance(account.getBalance() - cashOrderRequest.getAmount());
        }

        cashOrder.setExecutionResult("SUCCESS");
        transaction.setExecutionResult("SUCCESS");

        accountRepository.save(account);
        cashOrderRepository.save(cashOrder);
        transactionRepository.save(transaction);
    }
}
