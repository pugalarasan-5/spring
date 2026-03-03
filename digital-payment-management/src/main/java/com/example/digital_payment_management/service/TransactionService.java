package com.example.digital_payment_management.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digital_payment_management.dao.TransactionDao;
import com.example.digital_payment_management.dto.TransactionDTO;
import com.example.digital_payment_management.entity.Transaction;
import com.example.digital_payment_management.enums.TransactionType;



@Service
public class TransactionService {
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	ModelMapper mapper;

	public TransactionDTO registerTransaction(TransactionDTO transcationDto) {
		Transaction map = mapper.map(transcationDto, Transaction.class);
		Transaction trans = transactionDao.registerTranscation(map);
		TransactionDTO dto = mapper.map(trans, TransactionDTO.class);
		return dto;
	}
	public List<TransactionDTO> findByAmount(BigDecimal amount) {
		List<Transaction> byamount = transactionDao.findByamount(amount);
		if(byamount.isEmpty()) {
			throw new IllegalArgumentException(amount+" transcation is Not found...");
		}
		List<TransactionDTO> list = byamount.stream().map(trans->mapper.map(trans, TransactionDTO.class)).toList();
		return list;
	}

	public List<TransactionDTO> findByBankName(String name) {
		List<Transaction> byBankName = transactionDao.findByBankName(name);
		if(byBankName.isEmpty()) {
			throw new IllegalArgumentException(name+" transcation is Not found...");
		}
		List<TransactionDTO> list = byBankName.stream().map(trans->mapper.map(trans, TransactionDTO.class)).toList();
		return list;		
	}

	public List<TransactionDTO> findByType(TransactionType type) {
		List<Transaction> byProcess = transactionDao.findByType(type);
		if(byProcess.isEmpty()) {
			throw new IllegalArgumentException(type+" transcation is Not found...");
		}
		    List<TransactionDTO> list = byProcess.stream().map(trans->mapper.map(trans, TransactionDTO.class)).toList();
		return list;
	}

	public List<TransactionDTO> findByDate(LocalDateTime date) {
		List<Transaction> byDate = transactionDao.findByDate(date);
		if(byDate.isEmpty()) {
			throw new IllegalArgumentException(date+" transcation is Not found...");
		}
		    List<TransactionDTO> list = byDate.stream().map(trans->mapper.map(trans, TransactionDTO.class)).toList();
		return list;
	}

//	public List<TransactionDTO> findByUser(int id) {
//		List<Transaction> list = transactionDao.findByUser(id);
//		if(list.isEmpty()) {
//			throw new IllegalArgumentException(id+" user id transcation is Not found...");
//		}
//		List<TransactionDTO> dto = list.stream().map(trans->mapper.map(trans, TransactionDTO.class)).toList();
//		return dto;
//				
//	}

}
