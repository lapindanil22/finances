package com.example.finances.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CashOrderDTO {
    private String orderType;
    private Integer amount;
    private String executionResult;
    private Date creationDate;
}
