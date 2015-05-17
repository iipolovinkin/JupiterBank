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

    /**
     * @param idSender
     * @param idReceiver
     * @param startTime
     * @param endTime
     * @return
     */
    List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                  DateTime startTime, DateTime endTime);

    void delete(Transaction tr);

    void delete(Long id);

    void update(Transaction tr);

    void create(Transaction tr);

}
