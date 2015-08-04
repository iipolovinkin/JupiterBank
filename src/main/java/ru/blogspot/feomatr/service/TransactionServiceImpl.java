package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private TransactionDAO transactionDAO;

    @Override
    public void saveTransaction(Transaction tr) {
        transactionDAO.create(tr);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }

    @Override
    public Transaction getById(Long id) {
        return transactionDAO.get(id);
    }

    @Override
    public void delete(Transaction tr) {
        transactionDAO.delete(tr);
    }

    @Override
    public void delete(Long id) {
        transactionDAO.delete(id);
    }

    @Override
    public void update(Transaction tr) {
        transactionDAO.update(tr);
    }

    @Override
    public void create(Transaction tr) {
        transactionDAO.create(tr);
    }

    @Override
    public List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                         DateTime startTime, DateTime endTime) {
        return transactionDAO.getByFilter(idSender, idReceiver, startTime, endTime);
    }

}
