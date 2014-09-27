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
public class TransactionTest {
	Account[] accs = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accs = new Account[] { new Account(1L, null, 100L),
				new Account(2L, null, 770L), new Account(3L, null, 200L),
				new Account(4L, null, 400L) };
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link ru.blogspot.feomatr.entity.Transaction#Transaction(java.lang.Long, java.lang.Long, ru.blogspot.feomatr.entity.Account, ru.blogspot.feomatr.entity.Account)}
	 * .
	 */
	@Test
	public void testTransactionLongLongAccountAccount() {
		Transaction t1 = new Transaction(1L, 50L, accs[0], accs[1]);
		Transaction t2 = new Transaction(2L, 550L, accs[1], accs[2]);
		Transaction t3 = new Transaction(3L, 560L, accs[2], accs[3]);
		assertEquals(t1.getAmount(), (Long) 50L);
		assertEquals(t2.getAmount(), (Long) 550L);
		assertEquals(t3.getAmount(), (Long) 560L);
		assertEquals(t1.getTime().getSecondOfDay(), t2.getTime().getSecondOfDay());
		assertEquals(t1.getTime().getSecondOfDay(), t3.getTime().getSecondOfDay());
	}

}
