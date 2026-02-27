package com.example.digital_payment_management.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private double walletBalance;
	private String status;
	private LocalDate createdAt;
	@ManyToMany
	private List<Bank> accounts=new ArrayList<>();
	@ManyToMany
	private Transcation transcation;

	public List<Bank> setAccounts(Bank bank) {
		accounts.add(bank);
		return accounts;
	}
}
