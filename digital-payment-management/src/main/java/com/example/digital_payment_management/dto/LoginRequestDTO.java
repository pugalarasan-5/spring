package com.example.digital_payment_management.dto;


import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
	private String phone;
}
