package com.example.digital_payment_management.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transcation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double amount;
	private String bankName;
	private String process;
	private LocalDate date;
	private LocalTime time;

	@ManyToOne
	private User user;

	public Transcation(double amount, String bankName, String process, LocalDate date, LocalTime time, User user) {
		this.amount = amount;
		this.bankName = bankName;
		this.process = process;
		this.date = date;
		this.time = time;
		this.user=user;
		
	}
}
