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
    List<Transaction> getByFilter(Long idSender, Long idReciver, DateTime startTime, DateTime endTime);

    List<Transaction> getAll();

    Transaction get(Long id);

    void create(Transaction tr);

    void delete(Transaction tr);

    void delete(Long id);

    void update(Transaction tr);
}
