/**
 * 
 */
package ru.blogspot.feomatr.dao.stub;

import java.util.ArrayList;
import java.util.List;

import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public class TransactionDaoStubImpl implements TransactionDAO {
	private List<Transaction> transactions = new ArrayList<Transaction>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new Transaction(1L, 100L, new Account(1L, null, 400L),
					new Account(2L, null, 500L)));
			add(new Transaction(2L, 140L, new Account(3L, null, 300L),
					new Account(4L, null, 560L)));
		}
	};

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
		if (tr.getId() == null) {
			tr.setId((transactions.size() + 1L));
		}
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
