package ru.blogspot.feomatr.dao;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface TransactionDAO {

    List<Transaction> getByFilter(String senderAccountNo, String receiverAccountNo,
                                  DateTime startTime, DateTime endTime) throws DAOException;

    List<Transaction> getAll() throws DAOException;

    List<Transaction> getAll(int pageNumber, int pageSize) throws DAOException;

    Transaction getById(Long id) throws DAOException;

    void create(Transaction transaction) throws DAOException;

    void delete(Transaction transaction) throws DAOException;

    void delete(Long id) throws DAOException;

    void update(Transaction transaction) throws DAOException;
}
