/**
 *
 */
package ru.blogspot.feomatr.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * @author iipolovinkin
 */
public class ClientDAOHibImpl implements ClientDAO {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public ClientDAOHibImpl() {
    }

    @Override
    public List<Client> getAll() {
        List<Client> l;
        try {
            getCurrentSession().beginTransaction();
            l = getCurrentSession().createCriteria(Client.class).list();
        } finally {
            getCurrentSession().getTransaction().rollback();
        }
        return l;
    }

    @Override
    public Client getById(Long id) {
        Client c;
        try {
            getCurrentSession().beginTransaction();
            c = (Client) getCurrentSession().get(ru.blogspot.feomatr.entity.Client.class, id);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return c;
    }

    @Override
    public Client create(Client client) {
        if (client == null) {
            return client;
        }
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().save(client);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return client;
    }

    @Override
    public boolean delete(Client client) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().delete(client);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return true;
    }

    @Override
    public int update(Client client) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().update(client);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return 1;
    }

}
