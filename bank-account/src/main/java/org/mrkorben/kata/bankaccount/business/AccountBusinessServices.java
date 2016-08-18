package org.mrkorben.kata.bankaccount.business;

import org.mrkorben.kata.bankaccount.entities.Account;

public interface AccountBusinessServices {
	public Account createAccount(String accountNumber);
	public Account readAccount(String accountNumber);

}
