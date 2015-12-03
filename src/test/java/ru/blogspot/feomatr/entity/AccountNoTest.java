package ru.blogspot.feomatr.entity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author iipolovinkin
 * @since 03.10.2015
 */
public class AccountNoTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGeneratePrivateBankAccountNo() throws Exception {
        int clientNum = 123;

        String actual = AccountNo.generatePrivateBankAccountNo(clientNum);

        assertTrue(actual.endsWith(String.format("%07d", clientNum)));
    }

    @Test
    public void testGeneratePrivateBankAccountNo1() throws Exception {
        int clientNum = 1567;
        Pattern p = Pattern.compile("\\d{20}");

        String actual = AccountNo.generatePrivateBankAccountNo(clientNum);
        Matcher m = p.matcher(actual);

        assertTrue(m.matches());
    }

    @Test
    public void testGetCurrentNo() throws Exception {
		int currentNo = AccountNo.getCurrentNo();

		AccountNo.generatePrivateBankAccountNo();

	    assertThat(AccountNo.getCurrentNo(), is(currentNo + 1));
    }

    @Test
	public void shouldThrowExceptionClientNumLessThanONE(){
	    int clientNumLessThanONE = 0;

	    expectedException.expect(IllegalArgumentException.class);
	    String actual = AccountNo.generatePrivateBankAccountNo(clientNumLessThanONE);
    }
}