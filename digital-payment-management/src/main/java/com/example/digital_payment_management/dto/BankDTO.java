package com.example.digital_payment_management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.digital_payment_management.enums.BankStatus;

@Data
public class BankDTO {

    private long accountNo;
    private String userName;
    private String bankName;
    private BankStatus status;
    private String phone;
    private String email;
    private String address;
    private BigDecimal balance;
    private LocalDate createdAt;
    private String pin;
    private int userId;
}
