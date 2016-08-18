package org.mrkorben.kata.bankaccount.business;

import java.util.List;

import org.mrkorben.kata.bankaccount.entities.Account;
import org.mrkorben.kata.bankaccount.entities.Operation;
import org.mrkorben.kata.bankaccount.exception.AccountNotFoundException;
import org.mrkorben.kata.bankaccount.exception.InvalidOperationException;

public interface ClientBusinessServices {
	public void makeADeposit(Operation deposit) throws InvalidOperationException;
	public void makeAWithdrawal(Operation withdrawal)  throws InvalidOperationException;
	public List<Operation> seeHistory(Account account) throws AccountNotFoundException;
}
