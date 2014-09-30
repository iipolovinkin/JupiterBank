/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public interface TransactionService {
	void saveTransaction(Transaction tr);

	List<Transaction> getAllTransactions();

	Transaction getById(Long id);

	List<Transaction> getTransactionsBySender(Account sender);

	List<Transaction> getTransactionsByReciver(Account reciver);

	void delete(Transaction tr);

	void delete(Long id);

	void update(Transaction tr);

	void create(Transaction tr);

}
