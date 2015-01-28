package ru.blogspot.feomatr.entity;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private static Validator validator;
    private Account account;
    private Client client;

    @BeforeClass
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        client = new Client(1l, "Bill", "45 White avenu", 20);
        account = new Account(1l, client, new BigDecimal("11.05"));
    }

    @Test
    public void balanceMustBeGE0() {
        account.setBalance(new BigDecimal(-3));
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        assertEquals(1, constraintViolations.size());
        assertEquals("Balance should be not less than 0", constraintViolations.iterator().next().getMessage());
    }

}