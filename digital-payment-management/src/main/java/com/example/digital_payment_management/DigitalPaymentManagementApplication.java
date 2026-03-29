package com.example.digital_payment_management;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DigitalPaymentManagementApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
		SpringApplication.run(DigitalPaymentManagementApplication.class, args);
		System.out.println("Digital Payment Management Application started successfully!");
	}
	
	@Bean
	public ModelMapper getObject() {
		return new ModelMapper();
	}
	
}
