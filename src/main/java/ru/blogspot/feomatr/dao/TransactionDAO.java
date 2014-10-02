/**
 * 
 */
package ru.blogspot.feomatr.dao;

import java.util.List;

import org.joda.time.DateTime;

import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public interface TransactionDAO {

	/**
	 * @param idSender
	 * @param idReciver
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Transaction> getByFilter(Long idSender, Long idReciver,
			DateTime startTime, DateTime endTime);

	/**
	 * @return
	 */
	List<Transaction> getAll();

	/**
	 * @param t
	 * @return
	 */
	List<Transaction> getAfterTime(DateTime t);

	/**
	 * @param t
	 * @return
	 */
	List<Transaction> getBeforeTime(DateTime t);

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Transaction> getBetweenTimes(DateTime startTime, DateTime endTime);

	Transaction get(Long id);

	void create(Transaction tr);

	boolean delete(Transaction tr);

	boolean delete(Long Id);

	void update(Transaction tr);

	boolean isExist(Long id);
}
