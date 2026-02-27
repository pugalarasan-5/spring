package com.example.digital_payment_management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digital_payment_management.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

	Optional<Bank> findByAccountNo(long accountNo);

	List<Bank> findByUserName(String userName);

	List<Bank> findByBankName(String bankName);

	List<Bank> findByStatus(String status);

	Optional<Bank> findByPhone(String phone);

	Optional<Bank> findByEmail(String email);

	List<Bank> findByCreatedAt(LocalDate date);

}
