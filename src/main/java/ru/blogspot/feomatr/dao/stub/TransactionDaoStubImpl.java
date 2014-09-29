/**
 * 
 */
package ru.blogspot.feomatr.dao.stub;

import java.util.ArrayList;
import java.util.List;

import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public class TransactionDaoStubImpl implements TransactionDAO {
	private List<Transaction> transactions = new ArrayList<Transaction>();

	/**
	 * 
	 */
	public TransactionDaoStubImpl() {
		super();
	}

	/**
	 * @param transactions
	 */
	public TransactionDaoStubImpl(List<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.TransactionDAO#getAllTransactions()
	 */
	@Override
	public List<Transaction> getAllTransactions() {
		return transactions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.TransactionDAO#getById(java.lang.Long)
	 */
	@Override
	public Transaction get(Long id) {
		for (Transaction transaction : transactions) {
			if (transaction.getId().equals(id)) {
				return transaction;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#create(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public void create(Transaction tr) {
		transactions.add(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#delete(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public boolean delete(Transaction tr) {
		return transactions.remove(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#update(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public void update(Transaction tr) {
		transactions.contains(tr);
	}

	@Override
	public boolean delete(Long Id) {
		return transactions.remove(get(Id));
	}

	@Override
	public boolean isExist(Long id) {
		return (get(id) != null ? true : false);
	}

}
