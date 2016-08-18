package org.mrkorben.kata.bankaccount.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mrkorben.kata.bankaccount.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:bank-account-context.xml")
public class TestAccountBusinessServicesImpl {
	
	@Autowired
	AccountBusinessServices accountBusinessServices;
	
	@Test
	public void testCreateAccount() throws Exception {
		Account account = accountBusinessServices.createAccount("11111");
		Assert.assertNotNull(account);
	}
	
	@Test
	public void testReadAccount() throws Exception {
		accountBusinessServices.createAccount("11111");
		Account account = accountBusinessServices.readAccount("11111");
		Assert.assertNotNull(account);
	}

	
}
	