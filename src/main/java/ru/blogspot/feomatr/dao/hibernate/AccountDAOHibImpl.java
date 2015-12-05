package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.dao.DAOException;
import ru.blogspot.feomatr.entity.Account;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class AccountDAOHibImpl implements AccountDAO {
    private static final Logger log = LoggerFactory.getLogger(AccountDAOHibImpl.class);
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public List<Account> getAll() throws DAOException {
        List<Account> l;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            l = session.createCriteria(Account.class).list();
            tx.commit();
        } catch (Exception e) {
	        if (tx != null) {
		        tx.rollback();
	        }
            log.error("Cannot get all accounts", e);
            throw new DAOException("Cannot get all accounts", e);
        } finally {

        }
        return l;
    }

	@Override
	@NotNull
	@SuppressWarnings("unchecked")
	public List<Account> getAll(int pageNumber, int pageSize) throws DAOException {
		List<Account> l;
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Account.class);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
			criteria.setMaxResults(pageSize);
			l = criteria.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			log.error("Cannot get all accounts", e);
			throw new DAOException("Cannot get all accounts", e);
		}
		return l;
	}

    @Override
    public Account getById(Long id) throws DAOException {
        Account a;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            a = (Account) session.get(Account.class, id);
	        tx.commit();
        } catch (Exception e) {
	        if (tx != null) {
		        tx.rollback();
	        }
            log.error("Cannot get account by id: " + id, e);
            throw new DAOException("Cannot get account by id: " + id, e);
        }
        return a;
    }

	@Override
	public Account getByNo(String accountNo) throws DAOException {
		Account a;
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Account.class);
			criteria.add(Restrictions.eq("accountNo", accountNo));
			a = (Account) criteria.getExecutableCriteria(session).uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			log.error("Cannot get account by No: " + accountNo, e);
			throw new DAOException("Cannot get account by No: " + accountNo, e);
		}
		return a;
	}

    @Override
    public Account create(Account account) throws DAOException {
        if (account == null) {
            return account;
        }
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(account);
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
            log.error("Cannot create account" + account, e);
            throw new DAOException("Cannot create account" + account, e);
        }
        return account;
    }

    @Override
    public void delete(Account account) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(account);
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
            log.error("Cannot delete account" + account, e);
            throw new DAOException("Cannot delete account" + account, e);
        }
    }

    @Override
    public void update(Account account) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(account);
	        tx.commit();
        } catch (HibernateException e) {
	        if (tx != null) {
		        tx.rollback();
	        }
            log.error("Cannot update account" + account, e);
            throw new DAOException("Cannot update account" + account, e);
        }
    }
}
