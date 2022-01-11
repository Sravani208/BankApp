package com.springboot.bankapp.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.dto.Transfer;
import com.springboot.bankapp.model.Transaction;
import com.springboot.bankapp.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	/*beneficiary(To) acct no
	 * amount
	 * {
	 * toAccountNumber:"",
	 * username: "",
	 * amount: ""
	 * }:request body
	 * transfer?toAccountNumber=___&username=____&amount=___:Request param
	 * transfer/toAccountNumber/username/amount : path variable
	 * */
	@PostMapping("/transfer")
	public Transaction doTransfer(Principal principal, @RequestBody Transfer transfer) {
		String username=principal.getName();
		
		/*Step 1:
		 * Fetch detials of fromAccount
		 * 1.1 fetch fromAccountNumber from username
		 * 
		 * Step 2:
		 * 2.1 Debit the amount from fromAccountNumber/update the balance
		 * 2.2 Credit the amount to toAccountNUmber/update the balance
		 * 
		 * Step 3:
		 * 3.1 insert the entry of transfer in transaction table
		 * 
		 * */
		
		//1.1
		String fromAccountNumber=transactionService.fetchFromAccountNumber(username);
		
		//2.1
		transactionService.updateBalance(fromAccountNumber, transfer.getAmount());
		
		//2.2
		transactionService.creditAmount(transfer.getToAccountNumber(), transfer.getAmount());
		
		//3.1
		Transaction transaction = new Transaction();
		transaction.setAccountFrom(fromAccountNumber);
		transaction.setAccountTo(transfer.getToAccountNumber());
		transaction.setAmount(transfer.getAmount());
		transaction.setOperationType("TRANSFER");
		transaction.setDateOfTransaction(new Date());
		
		return transactionService.saveTransaction(transaction);
	}

}