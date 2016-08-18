package org.mrkorben.kata.bankaccount.business.impl;

import org.mrkorben.kata.bankaccount.business.AccountBusinessServices;
import org.mrkorben.kata.bankaccount.entities.Account;
import org.mrkorben.kata.bankaccount.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * Service metier de création de compte.
 * @author u029109
 *
 */
@Service
public class AccountBusinessServicesImpl implements AccountBusinessServices {

	@Autowired
	AccountRepository accountRepository;
	/**
	 * Création d'un compte client
	 */
	@Transactional(propagation =Propagation.REQUIRED)
	public Account createAccount(String accountNumber) {
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		accountRepository.save(account);	
		return account;
	}
	
	/**
	 * Lecture d'un compte client en fonction de son numéro de compte
	 */
	@Transactional(propagation =Propagation.REQUIRED, readOnly=true)
	public Account readAccount(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}
	
	
}
