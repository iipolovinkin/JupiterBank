package ru.blogspot.feomatr.entity;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author iipolovinkin
 * @since 03.10.2015
 */
class AccountNoTest {


    @Test
    void testGeneratePrivateBankAccountNo() throws Exception {
        int clientNum = 123;

        String actual = AccountNo.generatePrivateBankAccountNo(clientNum);

        assertTrue(actual.endsWith(String.format("%07d", clientNum)));
    }

    @Test
    void testGeneratePrivateBankAccountNo1() throws Exception {
        int clientNum = 1567;
        Pattern p = Pattern.compile("\\d{20}");

        String actual = AccountNo.generatePrivateBankAccountNo(clientNum);
        Matcher m = p.matcher(actual);

        assertTrue(m.matches());
    }

    @Test
    void testGetCurrentNo() throws Exception {
        int currentNo = AccountNo.getCurrentNo();

        AccountNo.generatePrivateBankAccountNo();

        assertThat(AccountNo.getCurrentNo(), is(currentNo + 1));
    }

    @Test
    void shouldThrowExceptionClientNumLessThanONE() {
        int clientNumLessThanONE = 0;

        assertThrows(IllegalArgumentException.class, () -> AccountNo.generatePrivateBankAccountNo(clientNumLessThanONE));
    }
}