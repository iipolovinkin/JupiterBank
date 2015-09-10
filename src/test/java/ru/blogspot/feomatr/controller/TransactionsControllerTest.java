package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.formBean.FormFilter;
import ru.blogspot.feomatr.service.TransactionService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class TransactionsControllerTest {
    TransactionService transactionService;
    private TransactionsController controller;
    private Model model;
    private String transactionsView = "transactions";

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        controller = new TransactionsController();
        transactionService = mock(TransactionService.class);
        controller.setTransactionService(transactionService);
    }

    @Test
    public void testShowTransactions() throws Exception {
        List<Transaction> expectedTransactions = Lists.newArrayList(new Transaction());
        when(transactionService.getAll()).thenReturn(Arrays.asList(new Transaction()));

        String actualView = controller.showTransactions(model);

        assertEquals(transactionsView, actualView);
        assertThat((List<Transaction>) model.asMap().get("transactions"), is(expectedTransactions));
        assertEquals(new FormFilter().toString(), model.asMap().get("formFilter").toString());
    }

    @Test
    public void testDoFilter() throws Exception {
        FormFilter formFilter = new FormFilter();
        List<Transaction> expectedTransactions = Lists.newArrayList();
        when(transactionService.getByFilter(null, null, null, null)).thenReturn(expectedTransactions);

        String actualView = controller.doFilter(formFilter, model);

        assertEquals(transactionsView, actualView);
        assertSame("transaction objects differs", expectedTransactions, model.asMap().get("transactions"));
    }

}
