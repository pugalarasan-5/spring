package com.example.digital_payment_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.digital_payment_management.dao.TranscationDao;
import com.example.digital_payment_management.entity.Transcation;


@Service
public class TranscationService {
	
	@Autowired
	TranscationDao transcationDao;

	public Transcation registerTranscation(Transcation transcation) {
		Transcation trans = transcationDao.registerTranscation(transcation);
		return trans;
	}
	public List<Transcation> findByAmount(double amount) {
		List<Transcation> byamount = transcationDao.findByamount(amount);
		if(byamount.isEmpty()) {
			throw new IllegalArgumentException(amount+" transcation is Not found...");
		}
		return byamount;
	}

	public List<Transcation> findByBankName(String name) {
		List<Transcation> byBankName = transcationDao.findByBankName(name);
		if(byBankName.isEmpty()) {
			throw new IllegalArgumentException(name+" transcation is Not found...");
		}
		return byBankName;		
	}

	public List<Transcation> findByProcess(String process) {
		List<Transcation> byProcess = transcationDao.findByProcess(process);
		if(byProcess.isEmpty()) {
			throw new IllegalArgumentException(process+" transcation is Not found...");

		}
		return byProcess;
	}

	public List<Transcation> findByDate(LocalDate date) {
		List<Transcation> byDate = transcationDao.findByDate(date);
		if(byDate.isEmpty()) {
			throw new IllegalArgumentException(date+" transcation is Not found...");
		}
		return byDate;
	}

	public List<Transcation> findByUser(int id) {
		List<Transcation> list = transcationDao.findByUser(id);
		if(list.isEmpty()) {
			throw new IllegalArgumentException(id+" user id transcation is Not found...");
		}
		return list;
				
	}

}
