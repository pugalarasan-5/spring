package com.example.digital_payment_management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.digital_payment_management.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Amount is required")
	@DecimalMin(value = "0.01", message = "Amount must be greater than 0")
	private BigDecimal amount;

	@NotBlank(message = "Bank name is required")
	@Size(min = 3, max = 50, message = "Bank name must be between 3 and 50 characters")
	private String bankName;

	@Enumerated(EnumType.STRING)
	private TransactionType type;

	@NotNull(message = "Date is required")
	@PastOrPresent(message = "Transaction date cannot be in the future")
	private LocalDateTime date;

	@ManyToOne
	private User user;

	public Transaction(BigDecimal amount, String bankName, String type, LocalDateTime createdAt, User user) {
		this.amount = amount;
		this.bankName = bankName;
		this.type = type.equalsIgnoreCase("CREDIT") ? TransactionType.CREDIT : TransactionType.DEBIT;
		this.date=createdAt;
		this.user = user;
	}
}
