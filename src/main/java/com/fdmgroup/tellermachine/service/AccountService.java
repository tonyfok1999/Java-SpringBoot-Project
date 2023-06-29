package com.fdmgroup.tellermachine.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.tellermachine.exception.ResourceNotFoundException;
import com.fdmgroup.tellermachine.model.*;
import com.fdmgroup.tellermachine.repository.AccountRepo;
import com.fdmgroup.tellermachine.repository.UserRepo;

@Service
public class AccountService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private AccountRepo accountRepo;
	
	public void saveAccount(Account account) {
		accountRepo.save(account);
		logger.info("New account " + account.getAccountName() + " has been saved");
	};
	
	public Account getAccountById(long accountId) {
		return accountRepo.findByAccountId(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
	};
	
	public void updateAccount(long accountId, Account newAccount) {
		Account currentAccount = this.getAccountById(accountId);
		currentAccount.setAccountName(newAccount.getAccountName());
		currentAccount.setBalance(newAccount.getBalance());
		logger.info("Account id " + accountId + " has been updated");
		accountRepo.save(currentAccount);
	}

	public void deleteAccountById(Long id) {
		accountRepo.deleteById(id);
		logger.warn("Account id " + id + " has been deleted");
	}
	
}
