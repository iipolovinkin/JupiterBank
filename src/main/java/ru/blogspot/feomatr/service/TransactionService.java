/**
 *
 */
package ru.blogspot.feomatr.service;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface TransactionService {
    void saveTransaction(Transaction tr);

    List<Transaction> getAll();

    Transaction getById(Long id);

    List<Transaction> getBySender(Account sender);

    List<Transaction> getByReciver(Account reciver);

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

    void delete(Transaction tr);

    void delete(Long id);

    void update(Transaction tr);

    void create(Transaction tr);

}
