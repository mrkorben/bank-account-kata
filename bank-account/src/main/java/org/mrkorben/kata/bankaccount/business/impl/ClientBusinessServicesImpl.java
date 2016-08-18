package org.mrkorben.kata.bankaccount.business.impl;

import java.math.BigDecimal;
import java.util.List;

import org.mrkorben.kata.bankaccount.business.ClientBusinessServices;
import org.mrkorben.kata.bankaccount.entities.Account;
import org.mrkorben.kata.bankaccount.entities.Operation;
import org.mrkorben.kata.bankaccount.exception.AccountNotFoundException;
import org.mrkorben.kata.bankaccount.exception.InvalidOperationException;
import org.mrkorben.kata.bankaccount.repositories.AccountRepository;
import org.mrkorben.kata.bankaccount.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service metier des opération destinées aux clients
 * @author u029109
 *
 */
@Service
public class ClientBusinessServicesImpl implements ClientBusinessServices {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	OperationRepository operationRepository;
	/**
	 * En tant que client 
	 * Lorsque je réalise un depot valide
	 * Alors je modifie la balance de mon compte et historise l'opération
	 *
	 **/
	public void makeADeposit(Operation deposit) throws InvalidOperationException {
		// Check account
		Account account = accountRepository.findOne(deposit.getAccount().getAccountId());
		if ( account == null ) {
			throw new InvalidOperationException();
		}
		
		// Check amount deposit; Must be greater than 0.  Add more business rules here...
		if ( deposit.getAmount().compareTo(BigDecimal.ZERO) == 0 ) {
			throw new InvalidOperationException();
		}
		BigDecimal balance = account.getBalance() != null ? account.getBalance() : new BigDecimal("0");
		account.setBalance(balance.add(deposit.getAmount()));
		
		accountRepository.save(account);
		operationRepository.save(deposit);
	}

	/**
	 * En tant que client l
	 * Lorsque je réalise un retrait valide
	 * alors je modifie la balance de mon compte et historise l'opération
	 **/
	public void makeAWithdrawal(Operation withdrawal) throws InvalidOperationException {
		// Check account
		Account account = accountRepository.findOne(withdrawal.getAccount().getAccountId());
		if ( account == null ) {
			throw new InvalidOperationException();
		}

		BigDecimal balance = account.getBalance() != null ? account.getBalance() : new BigDecimal("0");

		if ( balance.compareTo(withdrawal.getAmount()) <= 0) {
			throw new InvalidOperationException();
		}
		
		account.setBalance(balance.subtract(withdrawal.getAmount()));
		
		accountRepository.save(account);
		operationRepository.save(withdrawal);

	}
	
	/**
	 * En tant que client
	 * Lorsque je renseigne mon compte
	 * alors je peux consulter l'ensemble des opérations réalisées sur le compte.
	 **/
	public List<Operation> seeHistory(Account account) throws AccountNotFoundException {
		return operationRepository.findAll();
	}
	
	
}
