package com.example.digital_payment_management.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.digital_payment_management.enums.UserStatus;

@Data
public class UserDTO {

    private int id;
    private String userName;
    private String email;
    private String phone;
    private BigDecimal walletBalance;
    private String password;
    private UserStatus status;
    private LocalDate createdAt;
}
