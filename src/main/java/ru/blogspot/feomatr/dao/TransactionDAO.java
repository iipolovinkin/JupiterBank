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
    List<Transaction> getByFilter(Long idSender, Long idReciver, DateTime startTime, DateTime endTime) throws DAOException;

    List<Transaction> getAll() throws DAOException;

    Transaction getById(Long id) throws DAOException;

    void create(Transaction tr) throws DAOException;

    void delete(Transaction tr) throws DAOException;

    void delete(Long id) throws DAOException;

    void update(Transaction tr) throws DAOException;
}
