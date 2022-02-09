package ru.blogspot.feomatr.controller;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ExtendedModelMap;
import ru.blogspot.feomatr.entity.Gender;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * TODO: implement IT
 *
 * @author polovinkin.igor
 * @since 10.09.2015
 */
@ContextConfiguration(locations = {"classpath:controllerITContext.xml"})
@ExtendWith(SpringExtension.class)
public class TransactionsControllerSpringTest {

    @Autowired
    private TransactionsController transactionsController;

    @Autowired
    private TransactionService transactionService;

    private ExtendedModelMap model;

    @Autowired
    private SessionFactory factory;

    @BeforeEach
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }

    @Test
    void testCountry() throws Exception {
        String country = getCountry("Country,address");
        System.out.println("country = " + country);

        String countryAndWithoutComma = getCountry("CountryAndWithoutComma");
        System.out.println("countryAndWithoutComma = " + countryAndWithoutComma);

        String countryAndWithoutComma2 = getCountry("");
        System.out.println("countryAndWithoutComma2 = " + countryAndWithoutComma2);
    }


    public static String trimToNull(String str) {
        return ofNullable(str).map(String::trim).filter(x->!x.isEmpty()).orElse(null);
    }

    private String getCountry(String stringAddress) {
        return ofNullable(trimToNull(stringAddress)).map(addr->addr.split(",")[0]).orElse("1234");
    }

    @Test
    void testShowTransactions() throws Exception {
        String expectedView = "transactions";
        transactionService.create(new Transaction(Gender.FEMALE));
        transactionService.create(new Transaction(Gender.MALE));
        transactionService.create(new Transaction(Gender.MALE2));
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);

        String actualView = transactionsController.showTransactions(model, mock(HttpServletRequest.class));

        assertThat(actualView, is(expectedView));

        System.out.println("transactions = " + transactions);
//        factory..
        List list = factory.openSession().createSQLQuery("select sex from Transaction").list();


        System.out.println("list = " + list);


    }
}