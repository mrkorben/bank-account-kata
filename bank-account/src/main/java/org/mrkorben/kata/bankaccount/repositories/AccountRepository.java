package org.mrkorben.kata.bankaccount.repositories;

import org.mrkorben.kata.bankaccount.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account,Long>{
	public Account findByAccountNumber( String accountNumber);
}
