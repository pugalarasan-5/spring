package com.example.digital_payment_management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digital_payment_management.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(String phone);

	List<User> findByName(String name);

	List<User> findByStatus(String status);

	List<User> findByCreatedAt(LocalDate date);

}
