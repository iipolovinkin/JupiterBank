package ru.blogspot.feomatr.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.formBean.FormFilter;
import ru.blogspot.feomatr.service.TransactionService;

/**
 * @author iipolovinkin
 *
 */
public class TransactionsControllerTest {

	/**
	 * Test method for {@link ru.blogspot.feomatr.controller.TransactionsController#showTransactions(org.springframework.ui.Model)}.
	 */
	@Test
	public void testShowTransactions() throws Exception {
		TransactionService transactionService = mock(TransactionService.class);
		List<Transaction> expectedTransactions= Lists.newArrayList();
		when(transactionService.getAll()).thenReturn(expectedTransactions);

//		TODO use spring test configuration
		TransactionsController controller = new TransactionsController();
		controller.setTransactionService(transactionService);
		
		Model model = new ExtendedModelMap();
		String viewName = controller.showTransactions(model);
		assertEquals("transactions", viewName);
		
		assertSame(expectedTransactions, model.asMap().get("transactions")); 
		assertEquals(new FormFilter().toString(), model.asMap().get("formFilter").toString()); 
	}

	/**
	 * Test method for {@link ru.blogspot.feomatr.controller.TransactionsController#doFilter(ru.blogspot.feomatr.formBean.FormFilter, org.springframework.ui.Model)}.
	 */
	@Test
	public void testDoFilter() throws Exception {
		FormFilter formFilter = new FormFilter();
		TransactionService transactionService = mock(TransactionService.class);
		List<Transaction> expectedTransactions= Lists.newArrayList();
		when(transactionService.getByFilter(null, null, null, null)).thenReturn(expectedTransactions);
		
		TransactionsController controller = new TransactionsController();
		controller.setTransactionService(transactionService);
		
		Model model = new ExtendedModelMap();
		String viewName = controller.doFilter(formFilter, model);
		assertEquals("transactions", viewName);
		
		assertSame(expectedTransactions, model.asMap().get("transactions")); 
	}

}
