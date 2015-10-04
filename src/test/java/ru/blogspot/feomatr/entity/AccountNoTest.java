package ru.blogspot.feomatr.entity;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * @author iipolovinkin
 * @since 03.10.2015
 */
public class AccountNoTest {

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
}