/**
 * 
 */
package ru.blogspot.feomatr.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import ru.blogspot.feomatr.dao.TransactionDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

/**
 * @author iipolovinkin
 *
 */
public class TransactionDaoHibImpl implements TransactionDAO {
	private Class<Transaction> clazz = Transaction.class;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 */
	public TransactionDaoHibImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.TransactionDAO#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getAll() {
		getCurrentSession().beginTransaction();
		List<Transaction> l = getCurrentSession().createQuery(
				"from Transaction").list();
		getCurrentSession().getTransaction().rollback();
		return l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.TransactionDAO#getById(java.lang.Long)
	 */
	@Override
	public Transaction get(Long id) {
		getCurrentSession().beginTransaction();
		System.out.println("getById(" + id + ")");
		System.out.println("class (" + clazz + ")");
		Transaction e = (Transaction) getCurrentSession().get(clazz, id);
		getCurrentSession().getTransaction().commit();
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#create(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public void create(Transaction tr) {
		if (tr == null)
			return;
		getCurrentSession().beginTransaction();
		getCurrentSession().save(tr);
		getCurrentSession().getTransaction().commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#delete(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public boolean delete(Transaction tr) {
		getCurrentSession().beginTransaction();
		getCurrentSession().delete(tr);
		getCurrentSession().getTransaction().commit();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.TransactionDAO#update(ru.blogspot.feomatr.entity
	 * .Transaction)
	 */
	@Override
	public void update(Transaction tr) {
		getCurrentSession().beginTransaction();
		getCurrentSession().update(tr);
		getCurrentSession().getTransaction().commit();
	}

	@Override
	public boolean delete(Long Id) {
		return delete(get(Id));
	}

	@Override
	public boolean isExist(Long id) {
		return (get(id) != null ? true : false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Transaction> getAfterTime(DateTime t) {
		return filterAfterTime(getAll(), t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Transaction> getBeforeTime(DateTime t) {
		return filterBeforeTime(getAll(), t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Transaction> getBetweenTimes(DateTime startTime,
			DateTime endTime) {
		List<Transaction> l = filterAfterTime(getAll(), startTime);
		l = filterBeforeTime(l, endTime);
		return l;
	}

	@Override
	public List<Transaction> getByFilter(Long idSender, Long idReciver,
			DateTime startTime, DateTime endTime) {
		List<Transaction> l = filterAfterTime(getAll(), startTime);
		l = filterBeforeTime(l, endTime);
		l = filterReciver(l, idReciver);
		l = filterSender(l, idSender);
		return l;
	}

	public static List<Transaction> filterAfterTime(List<Transaction> trs,
			DateTime t) {
		if (t == null) {
			return trs;
		}
		List<Transaction> l = new ArrayList<>();
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
		List<Transaction> l = new ArrayList<>();
		for (int i = 0; i < trs.size(); ++i) {
			if (trs.get(i).getTime().isBefore(t)) {
				l.add(trs.get(i));
			}
		}
		return l;
	}

	public static List<Transaction> filterSender(List<Transaction> trs,
			Long idSender) {
		if (idSender == null) {
			return trs;
		}
		List<Transaction> l = new ArrayList<>();
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
		List<Transaction> l = new ArrayList<>();
		for (int i = 0; i < trs.size(); ++i) {
			Account reciver = trs.get(i).getReciver();
			if (reciver != null && reciver.getId().equals(idReciver)) {
				l.add(trs.get(i));
			}
		}
		return l;
	}
}