package org.mrkorben.kata.bankaccount.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mrkorben.kata.bankaccount.entities.Account;
import org.mrkorben.kata.bankaccount.entities.Operation;
import org.mrkorben.kata.bankaccount.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:bank-account-context.xml")
public class TestClientBusinessServicesImpl {
	
	@Autowired
	ClientBusinessServices clientBusinessServices;
	
	@Autowired
	AccountBusinessServices accountBusinessServices;
	
	private Account account;
	private static final String DEFAULT_ACCOUNT_NUMBER="1111";
	
	/**
	 * Injection d'un jeux de donn�e pour r�aliser le test
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception{
		account  = accountBusinessServices.createAccount(DEFAULT_ACCOUNT_NUMBER);
	}
	
	
	/**
	 * Par simplicit� pour la kata, on r�alise dans une m�me methode successivement le test de depot et de retrait.
	 * Pr�f�rer la s�paration en deux tests distincts.
	 * @throws Exception
	 */
	@Test
	public void testMakeADepositAndWithdrawal() throws Exception {
		BigDecimal deposit= new BigDecimal("100");
		BigDecimal withdrawal= new BigDecimal("10");

		clientBusinessServices.makeADeposit(createOperation(deposit));
		Account account = accountBusinessServices.readAccount(DEFAULT_ACCOUNT_NUMBER);
		
		// Assertion
		List<Operation> lstOperation=clientBusinessServices.seeHistory(account);
		Assert.assertTrue(account.getBalance().compareTo(deposit)==0);
		Assert.assertNotNull(lstOperation);
		Assert.assertFalse(lstOperation.isEmpty());
		Assert.assertTrue(lstOperation.size()==1);
		
		clientBusinessServices.makeAWithdrawal(createOperation(withdrawal));
		account = accountBusinessServices.readAccount(DEFAULT_ACCOUNT_NUMBER);
		lstOperation=clientBusinessServices.seeHistory(account);
		Assert.assertTrue(account.getBalance().compareTo(deposit.subtract(withdrawal))==0);
		Assert.assertNotNull(lstOperation);
		Assert.assertFalse(lstOperation.isEmpty());
		Assert.assertTrue(lstOperation.size()==2);
	}
	
	/**
	 * En tant que client lorsque je r�alise un d�pot d'un montant invalide alors une exception est lev�e
	 * @throws Exception
	 */
	@Test(expected=InvalidOperationException.class)
	public void testMakeAIllegalDeposit() throws Exception {
		clientBusinessServices.makeADeposit(createOperation(new BigDecimal("0")));
	}
	
	/**
	 * En tant que client lorsque je r�alise le retrait sur un compte sans cr�dit, alors je leve une exception
	 * @throws Exception
	 */
	@Test(expected=InvalidOperationException.class)
	public void testMakeAIllegalWithdrawal() throws Exception {
		clientBusinessServices.makeAWithdrawal(createOperation(new BigDecimal("10000")));
	}
	
	private Operation createOperation(BigDecimal db){
		Operation operation = new Operation();
		operation.setAmount( db );
		operation.setOperationDate(new Date());
		operation.setAccount(account);
		return operation;
	}
	
}
	