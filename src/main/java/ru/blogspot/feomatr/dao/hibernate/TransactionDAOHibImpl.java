package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.dao.DAOException;
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
    private static final Logger log = LoggerFactory.getLogger(TransactionDAOHibImpl.class);
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> getAll() throws DAOException {
        List<Transaction> l;
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Transaction.class);
            l = (List<Transaction>) criteria.list();
        } catch (HibernateException e) {
            log.error("Cannot get all transactions", e);
            throw new DAOException("Cannot get all transactions", e);
        } finally {
            tx.commit();
        }
        return l;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getAll(int pageNumber, int pageSize) throws DAOException {
		List<Transaction> l = new ArrayList<>();
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Transaction.class);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			l = (List<Transaction>) criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			log.error("Cannot get all transactions", e);
			throw new DAOException("Cannot get all transactions", e);
		}
		return l;
	}

    @Override
    public Transaction getById(Long id) throws DAOException {
        Transaction t;
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            t = (Transaction) session.get(Transaction.class, id);
        } catch (HibernateException e) {
            log.error("Cannot get transaction by id", e);
            throw new DAOException("Cannot get transaction by id", e);
        } finally {
            tx.commit();
        }
        return t;
    }

    @Override
    public void create(Transaction tr) throws DAOException {
        if (tr == null) {
            return;
        }
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.save(tr);
        } catch (HibernateException e) {
            log.error("Cannot create transaction", e);
            throw new DAOException("Cannot create transaction", e);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void delete(Transaction tr) throws DAOException {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.delete(tr);
        } catch (HibernateException e) {
            log.error("Cannot delete transaction", e);
            throw new DAOException("Cannot delete transaction", e);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void update(Transaction tr) throws DAOException {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.update(tr);
        } catch (HibernateException e) {
            log.error("Cannot update transaction", e);
            throw new DAOException("Cannot update transaction", e);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        delete(getById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> getByFilter(Long idSender, Long idReceiver,
                                         DateTime startTime, DateTime endTime) throws DAOException {
        List<Transaction> l = null;
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
            l = (List<Transaction>) criteria.getExecutableCriteria(session).list();
        } catch (HibernateException e) {
            log.error("Cannot get transactions by criteria", e);
            throw new DAOException("Cannot get transactions by criteria", e);
        } finally {
            tx.commit();
        }
        return l;
    }

}
