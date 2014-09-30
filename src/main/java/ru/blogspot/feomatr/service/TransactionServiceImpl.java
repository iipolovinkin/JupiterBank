/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public class TransactionServiceImpl implements TransactionService {
	private TransactionDAO dao;

	/**
	 * 
	 */
	public TransactionServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dao
	 */
	public TransactionServiceImpl(TransactionDAO dao) {
		super();
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#saveTransaction(ru.blogspot
	 * .feomatr.entity.Transaction)
	 */
	@Override
	public void saveTransaction(Transaction tr) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.service.TransactionService#getAllTransactions()
	 */
	@Override
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return dao.getAllTransactions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#getById(java.lang.Long)
	 */
	@Override
	public Transaction getById(Long id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#getTransactionsBySender
	 * (ru.blogspot.feomatr.entity.Account)
	 */
	@Override
	public List<Transaction> getTransactionsBySender(Account sender) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#getTransactionsByReciver
	 * (ru.blogspot.feomatr.entity.Account)
	 */
	@Override
	public List<Transaction> getTransactionsByReciver(Account reciver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#delete(ru.blogspot.feomatr
	 * .entity.Transaction)
	 */
	@Override
	public void delete(Transaction tr) {
		dao.delete(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#update(ru.blogspot.feomatr
	 * .entity.Transaction)
	 */
	@Override
	public void update(Transaction tr) {
		// TODO Auto-generated method stub

	}

}
