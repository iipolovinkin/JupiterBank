package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.dao.DAOException;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class ClientDAOHibImpl implements ClientDAO {
    private static final Logger log = LoggerFactory.getLogger(ClientDAOHibImpl.class);

    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getAll() throws DAOException {
        List<Client> l;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            l = (List<Client>) session.createCriteria(Client.class).list();
        } catch (HibernateException e) {
            log.error("Cannot get all clients", e);
            throw new DAOException("Cannot get all clients", e);
        } finally {
            tx.commit();
        }
        return l;
    }

    @Override
    public Client getById(Long id) throws DAOException {
        Client c;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            c = (Client) session.get(ru.blogspot.feomatr.entity.Client.class, id);
        } catch (HibernateException e) {
            log.error("Cannot get client by id", e);
            throw new DAOException("Cannot get client by id", e);
        } finally {
            tx.commit();
        }
        return c;
    }

    @Override
    public Client create(Client client) throws DAOException {
        if (client == null) {
            return client;
        }
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(client);
        } catch (HibernateException e) {
            log.error("Cannot create client", e);
            throw new DAOException("Cannot create client", e);
        } finally {
            tx.commit();
        }
        return client;
    }

    @Override
    public void delete(Client client) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(client);
        } catch (HibernateException e) {
            log.error("Cannot delete client", e);
            throw new DAOException("Cannot delete client", e);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void update(Client client) throws DAOException {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(client);
        } catch (HibernateException e) {
            log.error("Cannot update client", e);
            throw new DAOException("Cannot update client", e);
        } finally {
            tx.commit();
        }
    }

}
