package com.example.digital_payment_management.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private T data;
	private LocalDateTime timestamp;
	private String message;
	private int statusCode;
}
