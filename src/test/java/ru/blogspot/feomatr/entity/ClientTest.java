package ru.blogspot.feomatr.entity;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ResourceBundle;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * todo use  human readable naming, use AAA, use assertThat
 */
public class ClientTest {
    public static ResourceBundle validationMsgs = ResourceBundle.getBundle("ValidationMessages");
    private static Validator validator;
    private Client client;
    private Set<ConstraintViolation<Client>> constraintViolations;

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
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.age.notnull"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void agesMustBeGE1() {
        client.setAge(0);
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.age.between"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void ageMustBeLE150() {
        client.setAge(160);
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.age.between"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameTooShort() {
        client.setFirstname("BO");
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.firstname.size"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameTooLong() {
        client.setFirstname("1234567890123456789012345678901");
        constraintViolations = validator.validate(client);
        assertEquals(31, client.getFirstname().length());
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.firstname.size"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void firstnameMustBeAlphabetic() {
        client.setFirstname("Bill_M$W|NDOW$");
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.firstname.pattern"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void addressTooShort() {
        client.setAddress("Address");
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.address.size"), constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void addressTooLong() {
        client.setAddress("AddressTooLongAddressTooLongAddressTooLongAddressTooLongAddressTooLong");
        constraintViolations = validator.validate(client);
        assertEquals(1, constraintViolations.size());
        assertEquals(validationMsgs.getString("client.address.size"), constraintViolations.iterator().next().getMessage());
    }
}