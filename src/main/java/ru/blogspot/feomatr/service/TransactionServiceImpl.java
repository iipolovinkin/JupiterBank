/**
 *
 */
package ru.blogspot.feomatr.service;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
public class TransactionServiceImpl implements TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionServiceImpl() {
        super();
    }

    @Override
    public void saveTransaction(Transaction tr) {
        transactionDAO.create(tr);
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.service.TransactionService#getAllTransactions()
     */
    @Override
    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.service.TransactionService#getById(java.lang.Long)
     */
    @Override
    public Transaction getById(Long id) {
        return transactionDAO.get(id);
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
            return transactionDAO.getByFilter(sender.getId(), null, null, null);
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
            return transactionDAO.getByFilter(null, reciver.getId(), null, null);
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
        transactionDAO.delete(tr);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.service.TransactionService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        transactionDAO.delete(id);
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
        transactionDAO.update(tr);
    }

    @Override
    public void create(Transaction tr) {
        transactionDAO.create(tr);
    }

    @Override
    public List<Transaction> getAfterTime(DateTime t) {
        return transactionDAO.getAfterTime(t);
    }

    @Override
    public List<Transaction> getBeforeTime(DateTime t) {
        return transactionDAO.getBeforeTime(t);
    }

    @Override
    public List<Transaction> getBetweenTimes(DateTime startTime,
                                             DateTime endTime) {
        return transactionDAO.getBetweenTimes(startTime, endTime);
    }

    @Override
    public List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                         DateTime startTime, DateTime endTime) {
        return transactionDAO.getByFilter(idSender, idReceiver, startTime, endTime);
    }

    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
}
