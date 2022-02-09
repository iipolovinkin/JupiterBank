package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.formBean.FormFilter;
import ru.blogspot.feomatr.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class TransactionsControllerTest {
    private TransactionService transactionService;
    private TransactionsController transactionsController;
    private Model model;
    private String transactionsView = "transactions";
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() throws Exception {
        model = new ExtendedModelMap();
        transactionsController = new TransactionsController();
        transactionService = mock(TransactionService.class);
        transactionsController.setTransactionService(transactionService);
        request = new MockHttpServletRequest();
    }

    @Test
    void testShowTransactions() throws Exception {
        List<Transaction> expectedTransactions = Lists.newArrayList(new Transaction());
        when(transactionService.getAll()).thenReturn(Arrays.asList(new Transaction()));

        String actualView = transactionsController.showTransactions(model, mock(HttpServletRequest.class));

        assertEquals(transactionsView, actualView);
        assertThat((List<Transaction>) model.asMap().get("transactions"), is(expectedTransactions));
        assertEquals(new FormFilter().toString(), model.asMap().get("formFilter").toString());
    }

    @Test
    void testDoFilter() throws Exception {
        FormFilter formFilter = new FormFilter();
        List<Transaction> expectedTransactions = Lists.newArrayList();
        when(transactionService.getByFilter(null, null, null, null)).thenReturn(expectedTransactions);

        String actualView = transactionsController.doFilter(formFilter, model, mock(HttpServletRequest.class));

        assertEquals(transactionsView, actualView);
        Assertions.assertSame(expectedTransactions, model.asMap().get("transactions"));
    }

    @Test
    void testSaveAllTransactions() throws Exception {
        ModelAndView modelAndView = new ModelAndView("ExcelTransactionsReportView", "data", model);
        request.setParameter("output", "excel");

        ModelAndView actualModelAndView = transactionsController.saveAllTransactions(model, request);

        assertThat(actualModelAndView.getViewName(), is(modelAndView.getViewName()));
        assertTrue(actualModelAndView.getModel().containsKey("data"));

    }
}
