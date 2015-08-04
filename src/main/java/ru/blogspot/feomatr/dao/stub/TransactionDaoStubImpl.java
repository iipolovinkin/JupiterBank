package ru.blogspot.feomatr.dao.stub;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

import static org.fluentjava.FluentUtils.list;

/**
 * @author iipolovinkin
 */
public class TransactionDaoStubImpl implements TransactionDAO {
    private List<Transaction> transactions = list(
            new Transaction(1L, 100L, new Account(1L, null, 400L), new Account(2L, null, 500L)),
            new Transaction(2L, 140L, new Account(3L, null, 300L), new Account(4L, null, 560L)));

    public TransactionDaoStubImpl() {
        super();
    }

    public TransactionDaoStubImpl(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static List<Transaction> filterAfterTime(List<Transaction> trs,
                                                    DateTime t) {
        if (t == null) {
            return trs;
        }
        List<Transaction> l = Lists.newArrayList();
        for (int i = 0; i < trs.size(); ++i) {
            if (trs.get(i).getTime().isAfter(t)) {
                l.add(trs.get(i));
            }
        }
        return l;
    }

    public static List<Transaction> filterBeforeTime(List<Transaction> trs,
                                                     DateTime t) {
        if (t == null) {
            return trs;
        }
        List<Transaction> l = Lists.newArrayList();
        for (int i = 0; i < trs.size(); ++i) {
            if (trs.get(i).getTime().isBefore(t)) {
                l.add(trs.get(i));
            }
        }
        return l;
    }

    public static List<Transaction> filterSender(List<Transaction> trs, Long idSender) {
        if (idSender == null) {
            return trs;
        }
        List<Transaction> l = Lists.newArrayList();
        for (int i = 0; i < trs.size(); ++i) {
            Account sender = trs.get(i).getSender();
            if (sender != null && sender.getId().equals(idSender)) {
                l.add(trs.get(i));
            }
        }
        return l;
    }

    public static List<Transaction> filterReciver(List<Transaction> trs,
                                                  Long idReciver) {
        if (idReciver == null) {
            return trs;
        }
        List<Transaction> l = Lists.newArrayList();
        for (int i = 0; i < trs.size(); ++i) {
            Account reciver = trs.get(i).getReceiver();
            if (reciver != null && reciver.getId().equals(idReciver)) {
                l.add(trs.get(i));
            }
        }
        return l;
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }

    @Override
    public Transaction get(Long id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public void create(Transaction tr) {
        if (tr.getId() == null) {
            tr.setId((transactions.size() + 1L));
        }
        transactions.add(tr);
    }

    @Override
    public boolean delete(Transaction tr) {
        return transactions.remove(tr);
    }

    @Override
    public void update(Transaction tr) {
        transactions.contains(tr);
    }

    @Override
    public boolean delete(Long id) {
        return transactions.remove(get(id));
    }

    @Override
    public boolean isExist(Long id) {
        return (get(id) != null ? true : false);
    }

    @Override
    public List<Transaction> getByFilter(Long idSender, Long idReciver,
                                         DateTime startTime, DateTime endTime) {
        // todo use guava predicates or apache commons
        List<Transaction> l = filterAfterTime(transactions, startTime);
        l = filterBeforeTime(l, endTime);
        l = filterReciver(l, idReciver);
        l = filterSender(l, idSender);
        return l;
    }
}
