package com.IAPDemoPOC.Subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAPDemoPOC.Subscription.models.Transaction;
import com.IAPDemoPOC.Subscription.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public boolean saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		return true;
	}
	//get Transaction based on ID / subscription ID and User ID also.
	

}
