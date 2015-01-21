/**
 * 
 */
package ru.blogspot.feomatr.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author iipolovinkin
 *
 */
public class TransactionTest {
	Account[] accs = null;
	Transaction t1, t2, t3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accs = new Account[] { new Account(1L, null, 100L),
				new Account(2L, null, 770L), new Account(3L, null, 200L),
				new Account(4L, null, 400L) };
		t1 = new Transaction(1L, 50L, accs[0], accs[1]);
		t2 = new Transaction(2L, 550L, accs[1], accs[2]);
		t3 = new Transaction(3L, 560L, accs[2], accs[3]);
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
		assertEquals(t1.getAmount(), BigDecimal.valueOf(50));
		assertEquals(t2.getAmount(), BigDecimal.valueOf(550));
		assertEquals(t3.getAmount(), BigDecimal.valueOf(550));
		assertFalse(new Transaction(null, 10L, accs[0], accs[1])
				.equals(new Transaction()));
	}

	/**
	 * Test method for {@link Transaction#hashCode()} ()}
	 */
	@Test
	public void testTransactionHash() {
		Set<Transaction> t = new HashSet<Transaction>() {
			{
				add(t1);
				add(t2);
				add(t3);
			}
		};
		Transaction tt = new Transaction();
		tt.setId(t1.getId());
		t.add(tt);
		assertEquals(t.size(), 4);
	}
}
