package com.example.finances.controllers;

import com.example.finances.DTO.requests.TransferRequest;
import com.example.finances.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    TransactionService transactionService;

    @Autowired
    public TransferController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void doTransfer(@RequestBody TransferRequest transferRequest) {
        transactionService.doTransfer(transferRequest);
    }
}

