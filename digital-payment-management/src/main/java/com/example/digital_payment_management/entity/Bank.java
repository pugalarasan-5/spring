package com.example.digital_payment_management.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNo;
	private String userName;
	private String bankName;
	private String status;
	private String phone;
	private String email;
	private String address;
	private double balance;
	private int pin;
	private LocalDate CreateAt;
	
	@ManyToMany
	private Transcation transcation;
}
