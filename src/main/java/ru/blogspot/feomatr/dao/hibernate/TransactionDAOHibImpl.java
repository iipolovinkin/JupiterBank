/**
 *
 */
package ru.blogspot.feomatr.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iipolovinkin
 */
public class TransactionDAOHibImpl implements TransactionDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public TransactionDAOHibImpl() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> getAll() {
        List<Transaction> l;
        Session session = null;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Transaction.class);
            l = criteria.list();
        } finally {
            if (session != null) session.close();
        }
        return l;
    }

    @Override
    public Transaction get(Long id) {
        Transaction e;
        try {
            getCurrentSession().beginTransaction();
            e = (Transaction) getCurrentSession().get(Transaction.class, id);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return e;
    }

    @Override
    public void create(Transaction tr) {
        if (tr == null) {
            return;
        }
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().save(tr);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
    }

    @Override
    public boolean delete(Transaction tr) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().delete(tr);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return true;
    }

    @Override
    public void update(Transaction tr) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().update(tr);
        } finally {
            getCurrentSession().getTransaction().commit();
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
        if (startTime != null) criteria.add(Restrictions.ge("time", startTime));
        if (endTime != null) criteria.add(Restrictions.le("time", endTime));
        if (idSender != null) criteria.createCriteria("sender").add(Restrictions.eq("id", idSender));
        if (idReceiver != null) criteria.createCriteria("receiver").add(Restrictions.eq("id", idReceiver));
        Session session = null;
        try {
            session = getCurrentSession();
            session.beginTransaction();
            l = criteria.getExecutableCriteria(session).list();
        } catch (Exception e) {
            //log.error()
        }

        return l;
    }
}
