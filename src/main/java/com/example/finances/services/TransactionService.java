package com.example.finances.services;

import com.example.finances.DTO.requests.TransferRequest;
import com.example.finances.exceptions.ExpiredAccountException;
import com.example.finances.exceptions.NotEnoughMoneyException;
import com.example.finances.exceptions.WrongSecretWordException;
import com.example.finances.models.Account;
import com.example.finances.models.Transaction;
import com.example.finances.repositories.AccountRepository;
import com.example.finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    public List<Transaction> getAllByAccountId(Integer accountId) {
        return transactionRepository.getAllByAccountId(accountId);
    }
    public void doTransfer(TransferRequest transferRequest) {
        Account receiverAccount = accountRepository.findById(transferRequest.getReceiverAccountId()).get();
        Account senderAccount = accountRepository.findById(transferRequest.getSenderAccountId()).get();

        Date now = new Date();

        Transaction transaction = Transaction.builder()
                .creationDate(now)
                .amount(transferRequest.getAmount())
                .type("TRANSFER")
                .accountId(receiverAccount)
                .senderAccountId(senderAccount.getId())
                .build();

        if (!bCryptPasswordEncoder.matches(
                transferRequest.getSecretWord(),
                senderAccount.getClientId().getSecretWord()
        )) {
            transaction.setExecutionResult("WRONG_SECRET_WORD");
            transactionRepository.save(transaction);
            throw new WrongSecretWordException("Неверное кодовое слово");
        }

//        if (!senderAccount.getClientId().getSecretWord().equals()) {
//            transaction.setExecutionResult("WRONG_SECRET_WORD");
//            transactionRepository.save(transaction);
//            throw new WrongSecretWordException("Неверное кодовое слово");
//        }

        if (senderAccount.getBalance() < transferRequest.getAmount())
            throw new NotEnoughMoneyException("Недостаточно средств на счету");

        if (now.after(senderAccount.getExpirationDate()) || now.after(receiverAccount.getExpirationDate()))
            throw new ExpiredAccountException("Срок действия аккаунта истек");

        receiverAccount.setBalance(receiverAccount.getBalance() + transferRequest.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - transferRequest.getAmount());

        transaction.setExecutionResult("SUCCESS");

        accountRepository.save(receiverAccount);
        accountRepository.save(senderAccount);
        transactionRepository.save(transaction);
    }
}
