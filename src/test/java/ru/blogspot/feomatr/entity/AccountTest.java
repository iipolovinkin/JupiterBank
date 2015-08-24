package ru.blogspot.feomatr.entity;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.*;

import static java.util.Collections.emptySet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * todo use human readable naming, use AAA, use assertThat
 */
public class AccountTest {
    private static Validator validator;
    private static ResourceBundle validationMsgs;
    private Account account;
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
        account = new Account(1l, client, new BigDecimal("11.05"));
    }

    @Test
    public void balanceMustBeLETopBalance() {
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
    public void balanceMustBeGEBottomBalance() {
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

}