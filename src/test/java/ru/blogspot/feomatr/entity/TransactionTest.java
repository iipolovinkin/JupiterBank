package ru.blogspot.feomatr.entity;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @BeforeEach
    void setUp() throws Exception {
        accs = new Account[]{new Account(1L, null, 100L),
                new Account(2L, null, 770L),
                new Account(3L, null, 200L),
                new Account(4L, null, 400L)};

        t1 = new Transaction(1L, 50L, accs[0].getAccountNo(), accs[1].getAccountNo());
        t2 = new Transaction(2L, 550L, accs[1].getAccountNo(), accs[2].getAccountNo());
        t3 = new Transaction(3L, 560L, accs[2].getAccountNo(), accs[3].getAccountNo());
    }

    @Test
    void testTransactionLongLongAccountAccount() {
        assertEquals(t1.getAmount(), BigDecimal.valueOf(50));
        assertEquals(t2.getAmount(), BigDecimal.valueOf(550));
        assertEquals(t3.getAmount(), BigDecimal.valueOf(560));
        assertFalse(new Transaction(null, 10L, accs[0].getAccountNo(), accs[1].getAccountNo()).equals(new Transaction()));
    }

    @Test
    void testTransactionHash() {
        Set<Transaction> t = Sets.newHashSet(t1, t2, t3);
        Transaction tt = new Transaction();
        tt.setId(t1.getId());
        t.add(tt);
        assertEquals(4, t.size());
    }
}
