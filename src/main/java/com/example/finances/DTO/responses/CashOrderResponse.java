package com.example.finances.DTO.responses;

import com.example.finances.DTO.CashOrderDTO;

import java.util.List;

public class CashOrderResponse {
    private List<CashOrderDTO> cashOrders;

    public CashOrderResponse(List<CashOrderDTO> cashOrders){
        this.cashOrders = cashOrders;
    }

    public List<CashOrderDTO> getAllCashOrders() {
        return cashOrders;
    }

    public void setCashOrders(List<CashOrderDTO> cashOrder) {
        this.cashOrders = cashOrder;
    }
}
