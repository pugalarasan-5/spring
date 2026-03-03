package com.example.digital_payment_management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.digital_payment_management.enums.TransactionType;

@Data
public class TransactionDTO {

    private long id;
    private BigDecimal amount;
    private String bankName;
    private TransactionType type;
    private LocalDateTime createdAt;
    private int userId;
}
