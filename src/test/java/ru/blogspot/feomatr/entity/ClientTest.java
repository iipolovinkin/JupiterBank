package ru.blogspot.feomatr.entity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * todo use  human readable naming, use AAA, use assertThat
 */
public class ClientTest {
    public static ResourceBundle validationMsgs;
    private static Validator validator;
    private Client client;

    @BeforeClass
    public static void setUpClass() {
        Locale.setDefault(Locale.US);
        validationMsgs = ResourceBundle.getBundle("ValidationMessages");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUp() {
        client = new Client(1l, "Bill", "45 White avenu", 20);
    }

    @Test
    public void testAgeCanNotBeNull() {
        String expected = validationMsgs.getString("client.age.notnull");

        client.setAge(null);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testAgeMustBeGreater0() {
        String expected = validationMsgs.getString("client.age.lessThanValue")
                .replace("{value}", "1");
        int ageLessThanZero = 0;

        client.setAge(ageLessThanZero);
        String actual = getConstraintMessage(client);

        assertThat(actual, startsWith(expected));
    }

    @Test
    public void testAgeMustBeLess151() {
        String expected = validationMsgs.getString("client.age.greaterThanValue").replace("{value}", "150");
        int ageGreaterThan150 = 160;

        client.setAge(ageGreaterThan150);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testValidAge() {
        String expected = "";
        int validAge = 10;

        client.setAge(validAge);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testFirstnameTooShort() {
        String expected = validationMsgs.getString("client.firstname.size")
                .replace("{min}", "3").replace("{max}", "30");
        String shortName = "BO";

        client.setFirstname(shortName);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testFirstnameTooLong() {
        String expected = validationMsgs.getString("client.firstname.size")
                .replace("{min}", "3").replace("{max}", "30");
        String longName = "It_is_very_long_name_for_our_system";

        client.setFirstname(longName);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testValidFirstname() {
        String expected = "";
        String validName = "Jake";

        client.setFirstname(validName);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testFirstnameMustBeAlphabetic() {
        String expected = validationMsgs.getString("client.firstname.pattern");
        String nonAlpabeticName = "Bill_M$W|NDOW$";

        client.setFirstname(nonAlpabeticName);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testAddressTooShort() {
        String expected = validationMsgs.getString("client.address.size")
                .replace("{min}", "10").replace("{max}", "100");
        String shortAddress = "Address";

        client.setAddress(shortAddress);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    @Test
    public void testAddressTooLong() {
        String expected = validationMsgs.getString("client.address.size")
                .replace("{min}", "10").replace("{max}", "100");
        String longString = "AddressTooLongAddressTooLongAddressTooLongAddressTooLongAddressTooLong";
        String longAddress = longString + longString + longString;

        client.setAddress(longAddress);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }


    @Test
    public void testValidAddress() {
        String expected = "";
        String validAddress = "NY, Wall st. 60";

        client.setAddress(validAddress);
        String actual = getConstraintMessage(client);

        assertThat(actual, is(expected));
    }

    private String getConstraintMessage(Client client) {
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        if (constraintViolations.isEmpty()) {
            return "";
        } else {
            return constraintViolations.iterator().next().getMessage();
        }
    }
}