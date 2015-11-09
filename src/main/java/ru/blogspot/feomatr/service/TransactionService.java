package ru.blogspot.feomatr.service;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface TransactionService {
    void saveTransaction(Transaction transaction) throws ServiceException;

    List<Transaction> getAll() throws ServiceException;

    List<Transaction> getAll(int pageNumber, int pageSize) throws ServiceException;

    Transaction getById(Long id) throws ServiceException;

    List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                  DateTime startTime, DateTime endTime) throws ServiceException;

    void delete(Transaction transaction) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void update(Transaction transaction) throws ServiceException;

    void create(Transaction transaction) throws ServiceException;

}
