package com.example.digital_payment_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
@Entity
public class user
{
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should contain only letters and spaces")
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20)
	private String password;
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number should be 10 digits")
	private String phoneNumber;
	@NotBlank(message = "Account number cannot be blank")
	@Pattern(regexp = "^[0-9]{9,18}$", message = "Account number must be between 9 and 18 digits")
	private String accountNo;
	@Id
	@NotBlank(message = "User ID cannot be blank")
	private int  wallet= 0;
	 @ManyToOne
	 @JoinColumn(name = "bank_id")
	 private bank bank;
}
