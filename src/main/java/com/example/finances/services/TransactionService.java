package com.example.finances.services;

import com.example.finances.DTO.requests.TransferRequest;
import com.example.finances.exceptions.NotEnoughMoneyException;
import com.example.finances.models.Account;
import com.example.finances.models.Transaction;
import com.example.finances.repositories.AccountRepository;
import com.example.finances.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    public List<Transaction> getAllByAccountId(Integer accountId) {
        return transactionRepository.getAllByAccountId(accountId);
    }
    public void doTransfer(TransferRequest transferRequest) {
        Account receiverAccount = accountRepository.findById(transferRequest.getReceiverAccountId()).get();
        Account senderAccount = accountRepository.findById(transferRequest.getSenderAccountId()).get();

        if (senderAccount.getBalance() - transferRequest.getAmount() < 0)
            throw new NotEnoughMoneyException();

        receiverAccount.setBalance(receiverAccount.getBalance() + transferRequest.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - transferRequest.getAmount());

        Transaction transaction = Transaction.builder()
                .creationDate(new Date())
                .amount(transferRequest.getAmount())
                .type("TRANSFER")
                .accountId(receiverAccount)
                .senderAccountId(senderAccount.getId())
                .executionResult("SUCCESS")
                .build();

        accountRepository.save(receiverAccount);
        accountRepository.save(senderAccount);
        transactionRepository.save(transaction);
    }
}
