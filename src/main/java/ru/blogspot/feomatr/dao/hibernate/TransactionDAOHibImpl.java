package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class TransactionDAOHibImpl implements TransactionDAO {
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> getAll() {
        List<Transaction> l;
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Transaction.class);
            l = criteria.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return l;
    }

    @Override
    public Transaction get(Long id) {
        Transaction e;
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            e = (Transaction) session.get(Transaction.class, id);
        } finally {
            tx.commit();
        }
        return e;
    }

    @Override
    public void create(Transaction tr) {
        if (tr == null) {
            return;
        }
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.save(tr);
        } finally {
            tx.commit();
        }
    }

    @Override
    public boolean delete(Transaction tr) {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.delete(tr);
        } finally {
            tx.commit();
        }
        return true;
    }

    @Override
    public void update(Transaction tr) {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.update(tr);
        } finally {
            tx.commit();
        }
    }

    @Override
    public boolean delete(Long id) {
        return delete(get(id));
    }

    @Override
    public boolean isExist(Long id) {
        return (get(id) != null ? true : false);
    }

    @Override
    public List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                         DateTime startTime, DateTime endTime) {
        List<Transaction> l = new ArrayList<Transaction>();
        DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
        if (startTime != null) {
            criteria.add(Restrictions.ge("time", startTime));
        }
        if (endTime != null) {
            criteria.add(Restrictions.le("time", endTime));
        }
        if (idSender != null) {
            criteria.createCriteria("sender").add(Restrictions.eq("id", idSender));
        }
        if (idReceiver != null) {
            criteria.createCriteria("receiver").add(Restrictions.eq("id", idReceiver));
        }
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            l = criteria.getExecutableCriteria(session).list();
        } catch (Exception e) {
            //todo Resolve this issue
        }

        return l;
    }

}
