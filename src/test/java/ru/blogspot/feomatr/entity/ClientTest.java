package ru.blogspot.feomatr.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author iipolovinkin
 */
public class ClientTest {
    public static ResourceBundle validationMsgs;
    private static Validator validator;
    private static String clientAgeIsNotNull;
    private static String clientAgeLessThanValue;
    private static String clientFirstnameSize;
    private static String clientAddressSize;
    private static String clientFirstnamePattern;
    private static String clientAgeGreaterThanValue;
    private static int bottomAge = 1;
    private static int upperAge = 150;
    private static int clientAddressMinSize = 10;
    private static int clientAddressMaxSize = 100;
    private static int clientFirstnameMinSize = 3;
    private static int clientFirstnameMaxSize = 30;

    private Client client;

    @BeforeAll
    public static void setUpClass() {
        Locale.setDefault(Locale.US);
        validationMsgs = ResourceBundle.getBundle("ValidationMessages");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        clientAgeIsNotNull = validationMsgs.getString("client.age.notnull");
        clientAgeLessThanValue = validationMsgs.getString("client.age.lessThanValue")
                .replace("{value}", String.valueOf(bottomAge));
        clientAgeGreaterThanValue = validationMsgs.getString("client.age.greaterThanValue")
                .replace("{value}", String.valueOf(upperAge));
        clientFirstnameSize = validationMsgs.getString("client.firstname.size")
                .replace("{min}", String.valueOf(clientFirstnameMinSize)).replace("{max}", String.valueOf(clientFirstnameMaxSize));
        clientAddressSize = validationMsgs.getString("client.address.size")
                .replace("{min}", String.valueOf(clientAddressMinSize)).replace("{max}", String.valueOf(clientAddressMaxSize));
        clientFirstnamePattern = validationMsgs.getString("client.firstname.pattern");
    }

    @BeforeEach
    void setUp() {
        long someId = 1L;
        String validFirstname = "Bill";
        String validAddress = "10 Downing Street";
        int validAge = 20;
        client = new Client(someId, validFirstname, validAddress, validAge);
    }

    @Test
    void testSetNullAgeGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientAgeIsNotNull);

        client.setAge(null);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetAgeLessThanBottomAgeGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientAgeLessThanValue);
        int lessThanBottomAge = bottomAge - 1;

        client.setAge(lessThanBottomAge);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetAgeGreaterThanUpperAgeGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientAgeGreaterThanValue);
        int greaterThanUpperAge = upperAge + 1;

        client.setAge(greaterThanUpperAge);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetValidAge() {
        Set<String> expected = emptySet();
        int validAge = 10;

        client.setAge(validAge);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetShortFirstnameGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientFirstnameSize);
        String shortName = "BO";

        client.setFirstname(shortName);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetLpngFirstnameGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientFirstnameSize);
        String longName = "ItIsVeryLongNameForOurCurrentSystem";

        client.setFirstname(longName);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetValidFirstname() {
        Set<String> expected = emptySet();
        String validName = "Jake";

        client.setFirstname(validName);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetNonAlphabeticFirstnameGetValidationMessages() {
        Set<String> expected = Collections.singleton(clientFirstnamePattern);
        String nonAlpabeticName = "Bill_M$W|NDOW$";

        client.setFirstname(nonAlpabeticName);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetShortAddressGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientAddressSize);
        String shortAddress = "Address";

        client.setAddress(shortAddress);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testSetLongAddressThenGetValidationMessage() {
        Set<String> expected = Collections.singleton(clientAddressSize);
        String longString = "AddressTooLongAddressTooLongAddressTooLongAddressTooLongAddressTooLong";
        String longAddress = longString + longString + longString;

        client.setAddress(longAddress);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }


    @Test
    void testSetValidAddress() {
        Set<String> expected = emptySet();
        String validAddress = "NY, Wall st. 60";

        client.setAddress(validAddress);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    @Test
    void testWhenSetInvalidAgeFirstNameAddressThenGetValidationMessages() {
        Integer negativeAge = -10;
        String shortName = "2";
        String shortAddress = "10st";

        Set<String> expected = ImmutableSet.of(clientAgeLessThanValue, clientFirstnameSize, clientAddressSize);


        client.setAge(negativeAge);
        client.setFirstname(shortName);
        client.setAddress(shortAddress);
        Set<String> actual = getConstraintMessages(client);

        assertThat(actual, is(expected));
    }

    private Set<String> getConstraintMessages(Client client) {
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        if (constraintViolations.isEmpty()) {
            return emptySet();
        } else {
            Set<String> messages = Sets.newHashSet();
            for (ConstraintViolation<Client> constraintViolation : constraintViolations) {
                messages.add(constraintViolation.getMessage());
            }
            return messages;
        }
    }
}