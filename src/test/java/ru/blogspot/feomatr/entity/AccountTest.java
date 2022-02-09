package ru.blogspot.feomatr.entity;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * todo use human readable naming, use AAA, use assertThat
 */
public class AccountTest {
    private static Validator validator;
    private static ResourceBundle validationMsgs;
    private Account account;
    private Client client;

    @BeforeAll
    public static void setUpClass() {
        Locale.setDefault(Locale.US);
        validationMsgs = ResourceBundle.getBundle("ValidationMessages");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        client = new Client(1l, "Bill", "45 White avenu", 20);
        account = new Account(1l, client, new BigDecimal("11.05"));
    }

    @Test
    void balanceMustBeLETopBalance() {
        BigDecimal topBalance = new BigDecimal(10000000);
        BigDecimal greaterThanTopBalance = topBalance.add(BigDecimal.ONE);

        String accountBalanceGreaterThanValue = validationMsgs.getString("account.balance.max")
                .replace("{value}", String.valueOf(topBalance));

        Set<String> expected = Collections.singleton(accountBalanceGreaterThanValue);

        account.setBalance(greaterThanTopBalance);
        Set<String> actual = getConstraintMessages(account);

        assertThat(actual, is(expected));
    }
    @Test
    void balanceMustBeGEBottomBalance() {
        BigDecimal bottomBalance = BigDecimal.ZERO;
        BigDecimal lessThanBottomBalance = bottomBalance.subtract(BigDecimal.ONE);

        String accountBalanceLessThanValue = validationMsgs.getString("account.balance.min")
                .replace("{value}", String.valueOf(bottomBalance));


        Set<String> expected = Collections.singleton(accountBalanceLessThanValue);

        account.setBalance(lessThanBottomBalance);
        Set<String> actual = getConstraintMessages(account);

        assertThat(actual, is(expected));
    }

    private Set<String> getConstraintMessages(Account account) {
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        if (constraintViolations.isEmpty()) {
            return emptySet();
        } else {
            Set<String> messages = Sets.newHashSet();
            Iterator<ConstraintViolation<Account>> iterator = constraintViolations.iterator();
            for (; iterator.hasNext(); ) {
                messages.add(iterator.next().getMessage());
            }
            return messages;
        }
    }

	@Test
	void testToString() {
        account = new Account(1l, client, new BigDecimal("11.05"), "40817810061010007001");
		String expectedString = "Account(id=1, balance=11.05, accountNo=40817810061010007001, state=OPEN)";

		String actualString = account.toString();

		assertThat(actualString, is(expectedString));
	}

}