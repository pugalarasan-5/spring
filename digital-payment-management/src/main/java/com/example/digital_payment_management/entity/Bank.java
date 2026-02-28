package com.example.digital_payment_management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.digital_payment_management.enums.BankStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Bank {
	
	@Id
	private long accountNo;

	@NotBlank
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$")
	private String userName;

	@NotBlank
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$")
	private String bankName;

	@Enumerated(EnumType.STRING)
	private BankStatus status;

	@NotBlank
	@Pattern(regexp = "^[6-9][0-9]{9}$")
	private String phone;

	@NotNull
	@Email
	private String email;

	@NotBlank
	private String address;

	@NotNull
	private BigDecimal balance;

	@NotNull
	private String pin;
	
	private LocalDate createdAt;

	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
