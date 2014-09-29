/**
 * 
 */
package ru.blogspot.feomatr.dao;

import java.util.List;

import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public interface TransactionDAO {
	List<Transaction> getAllTransactions();

	Transaction get(Long id);

	void create(Transaction tr);

	boolean delete(Transaction tr);

	boolean delete(Long Id);

	void update(Transaction tr);

	boolean isExist(Long id);
}
