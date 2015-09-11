package ru.blogspot.feomatr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.service.TransactionService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO: implement IT
 *
 * @author polovinkin.igor
 * @since 10.09.2015
 */
@ContextConfiguration(locations = {"classpath:TransactionsControllerSpringTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionsControllerSpringTest {

    @Autowired
    TransactionsController transactionsController;

    @Qualifier("transactionService")
    @Autowired
    TransactionService transactionService;

    private ExtendedModelMap model;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }

    @Test
    public void testShowTransactions() throws Exception {
        String expectedView = "transactions";
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);

        String actualView = transactionsController.showTransactions(model);

        assertThat(actualView, is(expectedView));

    }
}