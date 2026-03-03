package com.example.digital_payment_management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.digital_payment_management.enums.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should contain only letters and spaces")
	private String userName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20)
	private String password;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number should be 10 digits")
	private String phone;

	
	private BigDecimal walletBalance;

	private UserStatus status;
	
	@NotNull(message = "Created date is required")
    @PastOrPresent(message = "Created date cannot be in the future")
	private LocalDate createdAt;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Bank> accounts = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Transaction> transactions = new ArrayList<>();

	public List<Bank> setAccounts(Bank bank) {
		accounts.add(bank);
		return accounts;
	}
}
