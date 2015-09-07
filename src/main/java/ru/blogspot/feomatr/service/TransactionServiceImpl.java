package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.dao.DAOException;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private TransactionDAO transactionDAO;

    @Override
    public void saveTransaction(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.create(transaction);
        } catch (DAOException e) {
            log.error("Cannot save transaction", e);
            throw new ServiceException("Cannot save transaction", e);
        }
    }

    @Override
    public List<Transaction> getAll() throws ServiceException {
        try {
            return transactionDAO.getAll();
        } catch (DAOException e) {
            log.error("Cannot get all transactions", e);
            throw new ServiceException("Cannot get all transactions", e);
        }
    }

    @Override
    public Transaction getById(Long id) throws ServiceException {
        try {
            return transactionDAO.getById(id);
        } catch (DAOException e) {
            log.error("Cannot get transaction by id", e);
            throw new ServiceException("Cannot get transaction by id", e);
        }
    }

    @Override
    public void delete(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.delete(transaction);
        } catch (DAOException e) {
            log.error("Cannot delete transaction", e);
            throw new ServiceException("Cannot delete transaction", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transactionDAO.delete(id);
        } catch (DAOException e) {
            log.error("Cannot delete transaction", e);
            throw new ServiceException("Cannot delete transaction", e);
        }
    }

    @Override
    public void update(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.update(transaction);
        } catch (DAOException e) {
            log.error("Cannot update transaction", e);
            throw new ServiceException("Cannot update transaction", e);
        }
    }

    @Override
    public void create(Transaction transaction) throws ServiceException {
        try {
            transactionDAO.create(transaction);
        } catch (DAOException e) {
            log.error("Cannot save transaction", e);
            throw new ServiceException("Cannot save transaction", e);
        }
    }

    @Override
    public List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                         DateTime startTime, DateTime endTime) throws ServiceException {
        try {
            return transactionDAO.getByFilter(idSender, idReceiver, startTime, endTime);
        } catch (DAOException e) {
            log.error("Cannot transactions by Filter", e);
            throw new ServiceException("Cannot transactions by Filter", e);
        }
    }

}
