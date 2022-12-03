package com.example.finances.controllers;

import com.example.finances.DTO.requests.CashOrderRequest;
import com.example.finances.models.CashOrder;
import com.example.finances.services.CashOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashOrder")
public class CashOrderController {

    private final CashOrderService cashOrderService;

    @Autowired
    public CashOrderController(CashOrderService cashOrderService) {
        this.cashOrderService = cashOrderService;
    }

    @GetMapping
    public List<CashOrder> getAll() {
        return cashOrderService.getAll();
    }

    @GetMapping("/{accountId}")
    public List<CashOrder> getAllByAccountId(@PathVariable(name = "accountId") Integer accountId) {
        return cashOrderService.getAllByAccountId(accountId);
    }

    @PostMapping("/create")
    public void createCashOrder(@RequestBody CashOrderRequest cashOrderRequest) {
        cashOrderService.createCashOrder(cashOrderRequest);
    }
}
