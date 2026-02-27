package com.example.digital_payment_management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digital_payment_management.dao.BankDao;
import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.util.ResponseStructure;

@Service
public class BankService {
	@Autowired
	BankDao bankDao;

	public ResponseStructure<?> registerBank(Bank bank) {
		Bank registerBank = bankDao.registerBank(bank);
		if(registerBank==null) {
			throw new IllegalArgumentException("Bank Not Register");
		}
		ResponseStructure<Bank> structure=new ResponseStructure<>();
		structure.setData(registerBank);
		structure.setMessage("Bank Register SuccessFully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
		
	}
	
	public ResponseStructure<?> bankLogin(String email, int pin) {
		Optional<Bank> bank = bankDao.bankLogin(email);
		if(bank.isEmpty()) {
			throw new IllegalArgumentException("Invalid Email Id...");
		}
		if(bank.get().getPin()==pin) {
			ResponseStructure<Optional<Bank>> structure=new ResponseStructure<>();
			structure.setData(bank);
			structure.setMessage("Login Done !!!");
			structure.setStatusCode(201);
			structure.setTimestamp(LocalDateTime.now());
			return structure;
		}
		throw new IllegalArgumentException("Wrong Credentials...");
	}

	public ResponseStructure<?> findByAccountNo(long accountNo) {
		Bank bank = bankDao.findByAccountNo(accountNo);
		if(bank==null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<Bank> structure=new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Register SuccessFully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByUserName(String userName) {
		List<Bank> bank = bankDao.findByUserName(userName);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByBankName(String bankName) {
		List<Bank> bank = bankDao.findByBankName(bankName);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByStatus(String status) {
		List<Bank> bank = bankDao.findByStatus(status);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByPhone(String phone) {
		Bank byPhone = bankDao.findByPhone(phone);
		if (byPhone == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(byPhone);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByEmail(String email) {
		Bank byEmail = bankDao.findByEmail(email);
		if (byEmail == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(byEmail);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByCreatedAt(LocalDate date) {
		List<Bank> bank = bankDao.findByCreatedAt(date);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findAll() {
		List<Bank> bank = bankDao.findAll();
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		ResponseStructure<List<Bank>> structure = new ResponseStructure<>();
		structure.setData(bank);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> updateBankStatus(long accountNo, String status) {
		Bank updateBankStatus = bankDao.updateBankStatus(accountNo,status);
		if (updateBankStatus == null) {
			throw new IllegalArgumentException("Bank Status Not Updated...");
		}
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(updateBankStatus);
		structure.setMessage("Bank Status Updated Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> updateBankPin(long accountNo, int pin) {
		Bank updateBankPin = bankDao.updateBankPin(accountNo, pin);
		if (updateBankPin == null) {
			throw new IllegalArgumentException("Bank Pin Not Updated...");
		}
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(bankDao.findByAccountNo(accountNo));
		structure.setMessage("Bank Pin Updated Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;

	}

	public ResponseStructure<?> updateBankAddress(long accountNo, String address) {
		Bank bank = bankDao.UpdateBankAddress(accountNo,address);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		bank.setAddress(address);
		Bank updateBankAddress = bankDao.registerBank(bank);
		if (updateBankAddress == null) {
			throw new IllegalArgumentException("Bank Address Not Updated...");
		}
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(updateBankAddress);
		structure.setMessage("Bank Address Updated Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> sendMoneyByBank(long senderId, long receiverId, double amount) {
		boolean sendMoneyBybank = bankDao.sendMoneyBybank(senderId,receiverId,amount);
		if(sendMoneyBybank) {
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setData("Money Send Succesfully...");
			structure.setTimestamp(LocalDateTime.now());
			structure.setMessage("Money Send Succesfully...");
			structure.setStatusCode(200);
			return structure;
		}
		return null;
	}

	
}
