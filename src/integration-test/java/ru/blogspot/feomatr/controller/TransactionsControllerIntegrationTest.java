package ru.blogspot.feomatr.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.WebApplicationContext;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.service.TransactionService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * @author polovinkin.igor
 * @since 10.09.2015
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:controllerITContext.xml"})
@ExtendWith(SpringExtension.class)
public class TransactionsControllerIntegrationTest {

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Resource
    private TransactionsController transactionsController;

    @Resource
    private TransactionService transactionService;

    private ExtendedModelMap model;

    @BeforeAll
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testShowTransactions() throws Exception {
        String expectedView = "transactions";
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);

        String actualView = transactionsController.showTransactions(model, mock(HttpServletRequest.class));

        assertThat(actualView, is(expectedView));
    }

}