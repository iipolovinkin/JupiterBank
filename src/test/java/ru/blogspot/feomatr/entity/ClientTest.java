package ru.blogspot.feomatr.entity;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private static Validator validator;
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
    }

    @Test
    public void ageIsNotNull() {
        client.setAge(null);
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Age should be not empty", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void agesMustBeGE1() {
        client.setAge(0);
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Age must be between 1 and 150", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void ageMustBeLE150() {
        client.setAge(160);
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Age must be between 1 and 150", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameTooShort() {
        client.setFirstname("BO");
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Name must be between 3 and 30", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameTooLong() {
        client.setFirstname("1234567890123456789012345678901");
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(31, client.getFirstname().length());
        assertEquals(1, constraintViolations.size());
        assertEquals("Name must be between 3 and 30", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameMustBeAlphabetic() {
        client.setFirstname("Bill_M$W|NDOW$");
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Name must be alphanumeric with no spaces", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void addressTooShort() {
        client.setAddress("Address");
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Address must be between 10 and 50", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void addressTooLong() {
        client.setAddress("AddressTooLongAddressTooLongAddressTooLongAddressTooLongAddressTooLong");
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals("Address must be between 10 and 50", constraintViolations.iterator().next().getMessage());
    }
}