package com.example.digital_payment_management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digital_payment_management.dao.BankDao;
import com.example.digital_payment_management.dto.BankDTO;
import com.example.digital_payment_management.dto.BankLoginDTO;
import com.example.digital_payment_management.entity.Bank;
import com.example.digital_payment_management.enums.BankStatus;
import com.example.digital_payment_management.util.ResponseStructure;

@Service
public class BankService {
	@Autowired
	BankDao bankDao;
	
	@Autowired
	ModelMapper mapper;

	public ResponseStructure<?> registerBank(BankDTO bankDto) {
		Bank bank = mapper.map(bankDto, Bank.class);
		Bank registerBank = bankDao.registerBank(bank);
		if(registerBank==null) {
			throw new IllegalArgumentException("Bank Not Register");
		}
		BankDTO map = mapper.map(registerBank, BankDTO.class);
		ResponseStructure<BankDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setMessage("Bank Register SuccessFully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
		
	}
	
	public ResponseStructure<?> bankLogin(BankLoginDTO loginDTO) {
		Bank bank = bankDao.bankLogin(loginDTO.getEmail());
		if(bank==null) {
			throw new IllegalArgumentException("Invalid Email Id...");
		}
		if(bank.getPin().equals(loginDTO.getPin())) {
			BankDTO map = mapper.map(bank, BankDTO.class);
			ResponseStructure<BankDTO> structure=new ResponseStructure<>();
			structure.setData(map);
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
		BankDTO map = mapper.map(bank, BankDTO.class);
		ResponseStructure<BankDTO> structure=new ResponseStructure<>();
		structure.setData(map);
		structure.setMessage("Bank Found SuccessFully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByUserName(String userName) {
		List<Bank> bank = bankDao.findByUserName(userName);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
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
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> findByStatus(BankStatus status) {
		List<Bank> bank = bankDao.findByStatus(status);
		if (bank == null) {
			throw new IllegalArgumentException("Bank NOT Found...");
		}
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
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
		BankDTO map = mapper.map(byPhone, BankDTO.class);
		ResponseStructure<BankDTO> structure = new ResponseStructure<>();
		structure.setData(map);
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
		BankDTO map = mapper.map(byEmail, BankDTO.class);
		ResponseStructure<BankDTO> structure = new ResponseStructure<>();
		structure.setData(map);
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
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
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
		List<BankDTO> list = bank.stream().map(b -> mapper.map(b, BankDTO.class)).toList();
		ResponseStructure<List<BankDTO>> structure = new ResponseStructure<>();
		structure.setData(list);
		structure.setMessage("Bank Found Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> updateBankStatus(long accountNo, BankStatus status){
		Bank updateBankStatus = bankDao.updateBankStatus(accountNo,status);
		if (updateBankStatus == null) {
			throw new IllegalArgumentException("Bank Status Not Updated...");
		}
		BankDTO map = mapper.map(updateBankStatus, BankDTO.class);
		ResponseStructure<BankDTO> structure = new ResponseStructure<>();
		structure.setData(map);
		structure.setMessage("Bank Status Updated Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> updateBankPin(long accountNo, String pin) {
		Bank updateBankPin = bankDao.updateBankPin(accountNo, pin);
		if (updateBankPin == null) {
			throw new IllegalArgumentException("Bank Pin Not Updated...");
		}
		BankDTO map = mapper.map(updateBankPin, BankDTO.class);
		ResponseStructure<BankDTO> structure = new ResponseStructure<>();
		structure.setData(map);
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
		BankDTO map = mapper.map(bank, BankDTO.class);
		ResponseStructure<BankDTO> structure = new ResponseStructure<>();
		structure.setData(map);
		structure.setMessage("Bank Address Updated Successfully...");
		structure.setStatusCode(200);
		structure.setTimestamp(LocalDateTime.now());
		return structure;
	}

	public ResponseStructure<?> sendMoneyByBank(long senderId, long receiverId, BigDecimal amount) {
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
