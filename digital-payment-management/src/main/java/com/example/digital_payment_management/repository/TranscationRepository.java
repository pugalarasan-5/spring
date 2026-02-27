package com.example.digital_payment_management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digital_payment_management.entity.Transcation;
import com.example.digital_payment_management.entity.User;

public interface TranscationRepository extends JpaRepository<Transcation, Integer>{

	List<Transcation> findByBankName(String name);

	List<Transcation> findByProcess(String process);

	List<Transcation> findByDate(LocalDate date);

	List<Transcation> findByFrom(User user);

	List<Transcation> findByTo(User user);

	List<Transcation> findByAmount(double amount);

}
