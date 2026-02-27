package com.example.digital_payment_management.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.digital_payment_management.entity.Transcation;
import com.example.digital_payment_management.entity.User;
import com.example.digital_payment_management.repository.TranscationRepository;
import com.example.digital_payment_management.repository.UserRepository;

@Repository
public class TranscationDao {
	@Autowired
	TranscationRepository transcationRepository;
	
	@Autowired
	UserRepository userRepository;

	public Transcation registerTranscation(Transcation transcation) {
		return transcationRepository.save(transcation);
		
	}
	
	public List<Transcation> findByamount(double amount) {
		return transcationRepository.findByAmount(amount);
				
	}

	public List<Transcation> findByBankName(String name) {
		return transcationRepository.findByBankName(name);
				 
	}

	public List<Transcation> findByProcess(String process) {
		return transcationRepository.findByProcess(process);
	}

	public List<Transcation> findByDate(LocalDate date) {
		return transcationRepository.findByDate(date);		
	}

	public List<Transcation> findByUser(int id) {
		User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user Id.."));
		List<Transcation> from = transcationRepository.findByFrom(user);
		List<Transcation> to = transcationRepository.findByTo(user);
		from.addAll(to);
		return from;
	}

	
}
