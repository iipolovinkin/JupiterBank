package ru.blogspot.feomatr.entity;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.fluentjava.FluentUtils.set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class TransactionTest {
    private Account[] accs = null;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;

    @Before
    public void setUp() throws Exception {
        accs = new Account[]{new Account(1L, null, 100L),
                new Account(2L, null, 770L),
                new Account(3L, null, 200L),
                new Account(4L, null, 400L)};

        t1 = new Transaction(1L, 50L, accs[0], accs[1]);
        t2 = new Transaction(2L, 550L, accs[1], accs[2]);
        t3 = new Transaction(3L, 560L, accs[2], accs[3]);
    }

    @Test
    public void testTransactionLongLongAccountAccount() {
        assertEquals(t1.getAmount(), BigDecimal.valueOf(50));
        assertEquals(t2.getAmount(), BigDecimal.valueOf(550));
        assertEquals(t3.getAmount(), BigDecimal.valueOf(560));
        assertFalse(new Transaction(null, 10L, accs[0], accs[1]).equals(new Transaction()));
    }

    @Test
    public void testTransactionHash() {
        Set<Transaction> t = set(t1, t2, t3);
        Transaction tt = new Transaction();
        tt.setId(t1.getId());
        t.add(tt);
        assertEquals(4, t.size());
    }
}
