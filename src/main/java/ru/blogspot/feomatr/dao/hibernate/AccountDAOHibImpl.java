package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        } catch (Exception e) {
            log.error("Cannot get all accounts", e);
            throw new DAOException("Cannot get all accounts", e);
        } finally {
            tx.commit();
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
        } catch (HibernateException e) {
            log.error("Cannot get account by id", e);
            throw new DAOException("Cannot get account by id", e);
        } finally {
            tx.commit();
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
        } catch (HibernateException e) {
            log.error("Cannot create account", e);
            throw new DAOException("Cannot create account", e);
        } finally {
            tx.commit();
        }
        return account;
    }

    @Override
    public void delete(Account account) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(account);
        } catch (HibernateException e) {
            log.error("Cannot delete account", e);
            throw new DAOException("Cannot delete account", e);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void update(Account account) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(account);
        } catch (HibernateException e) {
            log.error("Cannot update account", e);
            throw new DAOException("Cannot update account", e);
        } finally {
            tx.commit();
        }
    }
}
