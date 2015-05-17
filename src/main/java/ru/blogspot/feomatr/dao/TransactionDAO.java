/**
 *
 */
package ru.blogspot.feomatr.dao;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
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

    Transaction get(Long id);

    void create(Transaction tr);

    boolean delete(Transaction tr);

    boolean delete(Long id);

    void update(Transaction tr);

    boolean isExist(Long id);
}
