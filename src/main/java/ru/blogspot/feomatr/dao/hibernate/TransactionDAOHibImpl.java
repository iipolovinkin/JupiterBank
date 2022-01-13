package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.blogspot.feomatr.dao.DAOException;
import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Transaction;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Repository
public class TransactionDAOHibImpl implements TransactionDAO {
    private static final Logger log = LoggerFactory.getLogger(TransactionDAOHibImpl.class);
	@Autowired
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
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
	        log.error("Cannot get all transactions", e);
	        throw new DAOException("Cannot get all transactions", e);
        }
        return l;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getAll(int pageNumber, int pageSize) throws DAOException {
		List<Transaction> l;
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Transaction.class);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			l = (List<Transaction>) criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
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
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
	        log.error("Cannot get transaction by id", e);
	        throw new DAOException("Cannot get transaction by id", e);
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
		    tx.commit();
	    } catch (HibernateException e) {
		    if (tx != null) {
			    tx.rollback();
		    }
		    log.error("Cannot create transaction", e);
		    throw new DAOException("Cannot create transaction", e);
	    }
    }

    @Override
    public void delete(Transaction tr) throws DAOException {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.delete(tr);
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
	        log.error("Cannot delete transaction", e);
	        throw new DAOException("Cannot delete transaction", e);
        }
    }

    @Override
    public void update(Transaction tr) throws DAOException {
        Session session = getCurrentSession();
        org.hibernate.Transaction tx = session.beginTransaction();
	    try {
		    session.update(tr);
		    tx.commit();
	    } catch (HibernateException e) {
		    if (tx != null) {
			    tx.rollback();
		    }
		    log.error("Cannot update transaction", e);
		    throw new DAOException("Cannot update transaction", e);
	    }
    }

    @Override
    public void delete(Long id) throws DAOException {
        delete(getById(id));
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getByFilter(String senderAccountNo, String receiverAccountNo,
	                                     DateTime startTime, DateTime endTime) throws DAOException {
		List<Transaction> l = null;
		DetachedCriteria criteria = getFilterCriteria(senderAccountNo, receiverAccountNo, startTime, endTime);
		return getByCriteria(criteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getByFilter(String senderAccountNo, String receiverAccountNo,
	                                     DateTime startTime, DateTime endTime, int pageNumber, int pageSize) throws DAOException {
		List<Transaction> l = null;
		DetachedCriteria criteria = getFilterCriteria(senderAccountNo, receiverAccountNo, startTime, endTime);
		return getByCriteria(criteria, pageNumber, pageSize);
	}

	@NotNull
	private DetachedCriteria getFilterCriteria(String senderAccountNo, String receiverAccountNo, DateTime startTime, DateTime endTime) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class);
		if (startTime != null) {
			criteria.add(Restrictions.ge("time", startTime));
		}
		if (endTime != null) {
			criteria.add(Restrictions.le("time", endTime));
		}
		if (StringUtils.isNotBlank(senderAccountNo)) {
			criteria.add(Restrictions.eq("senderAccountNo", senderAccountNo));
		}
		if (StringUtils.isNotBlank(receiverAccountNo)) {
			criteria.add(Restrictions.eq("receiverAccountNo", receiverAccountNo));
		}
		return criteria;
	}

	//	@SuppressWarnings("unchecked")
	public List<Transaction> getByCriteria(DetachedCriteria detachedCriteria, int pageNumber, int pageSize) throws DAOException {
		List<Transaction> l = null;
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			l = (List<Transaction>) criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			log.error("Cannot get transactions by criteria", e);
			throw new DAOException("Cannot get transactions by criteria", e);
		}

		return l;
	}

	public List<Transaction> getByCriteria(DetachedCriteria detachedCriteria) throws DAOException {
		List<Transaction> l = null;
		Session session = getCurrentSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			l = (List<Transaction>) criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			log.error("Cannot get transactions by criteria", e);
			throw new DAOException("Cannot get transactions by criteria", e);
		}

		return l;
	}

}
