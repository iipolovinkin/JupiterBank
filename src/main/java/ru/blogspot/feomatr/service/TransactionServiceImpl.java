/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import org.joda.time.DateTime;

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
		dao.create(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.service.TransactionService#getAllTransactions()
	 */
	@Override
	public List<Transaction> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.TransactionService#getById(java.lang.Long)
	 */
	@Override
	public Transaction getById(Long id) {
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
	public List<Transaction> getBySender(Account sender) {
		if (sender != null) {
			return dao.getByFilter(sender.getId(), null, null, null);
		}
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
	public List<Transaction> getByReciver(Account reciver) {
		if (reciver != null) {
			return dao.getByFilter(null, reciver.getId(), null, null);
		}
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

	}

	@Override
	public void create(Transaction tr) {
		dao.create(tr);
	}

	@Override
	public List<Transaction> getAfterTime(DateTime t) {
		return dao.getAfterTime(t);
	}

	@Override
	public List<Transaction> getBeforeTime(DateTime t) {
		return dao.getBeforeTime(t);
	}

	@Override
	public List<Transaction> getBetweenTimes(DateTime startTime,
			DateTime endTime) {
		return dao.getBetweenTimes(startTime, endTime);
	}

	@Override
	public List<Transaction> getByFilter(Long idSender, Long idReciver,
			DateTime startTime, DateTime endTime) {
		return dao.getByFilter(idSender, idReciver, startTime, endTime);
	}

}
