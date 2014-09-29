/**
 * 
 */
package ru.blogspot.feomatr.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author iipolovinkin
 *
 */
public class BrokerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Broker#transfer(ru.blogspot.feomatr.entity.Account, ru.blogspot.feomatr.entity.Account, java.lang.Long)}
	 * .
	 */
	@Test
	public void testTransfer() throws Exception {
		Account account1 = new Account();
		Account account2 = new Account();
		Long balance = 100L;

		account1.setBalance(balance);
		Broker.transfer(account1, account2, 30L);
		assertEquals(account2.getBalance(), (Long) 30L);
	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Broker#transferTo(ru.blogspot.feomatr.entity.Account, java.lang.Long)}
	 * .
	 */
	@Test
	public void testTransferTo() throws Exception {
		Account account = new Account();
		Long balance = 100L;

		account.setBalance(balance);
		Broker.transferTo(account, 30L);
		assertEquals(account.getBalance(), (Long) 130L);

		account.setBalance(balance);
		Broker.transferTo(account, 130L);
		assertEquals(account.getBalance(), (Long) 230L);

	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Broker#transferTo(ru.blogspot.feomatr.entity.Account, java.lang.Long)}
	 * .
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testTransferToAmountLessThanZero() throws Exception {
		Account account = new Account();
		Long balance = 100L;

		account.setBalance(balance);
		Broker.transferTo(account, -30L);
		assertEquals(account.getBalance(), balance);
	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Broker#transferFrom(ru.blogspot.feomatr.entity.Account, java.lang.Long)}
	 * .
	 */
	@Test
	public void testTransferFrom() throws Exception {
		Account account = new Account();
		Long balance = 100L;

		account.setBalance(balance);
		Broker.transferFrom(account, 30L);
		assertEquals(account.getBalance(), (Long) 70L);

		account.setBalance(balance);
		Broker.transferFrom(account, 130L);
		assertEquals(account.getBalance(), balance);

	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Broker#transferFrom(ru.blogspot.feomatr.entity.Account, java.lang.Long)}
	 * .
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testTransferFromAmountLessThanZeio() throws Exception {
		Account account = new Account();
		Long balance = 100L;

		account.setBalance(balance);
		Broker.transferFrom(account, -130L);
		assertEquals(account.getBalance(), balance);
	}

}
