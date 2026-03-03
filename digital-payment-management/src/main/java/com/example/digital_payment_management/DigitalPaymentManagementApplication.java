package com.example.digital_payment_management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalPaymentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalPaymentManagementApplication.class, args);
		System.out.println("Digital Payment Management Application started successfully!");
	}
	
	@Bean
	public ModelMapper getObject() {
		return new ModelMapper();
	}

}
