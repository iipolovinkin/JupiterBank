package ru.blogspot.feomatr.dao.hibernate;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class ClientDAOHibImpl implements ClientDAO {

    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        List<Client> l;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            l = (List<Client>) session.createCriteria(Client.class).list();
        } finally {
            tx.commit();
        }
        return l;
    }

    @Override
    public Client getById(Long id) {
        Client c;
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            c = (Client) session.get(ru.blogspot.feomatr.entity.Client.class, id);
        } finally {
            tx.commit();
        }
        return c;
    }

    @Override
    public Client create(Client client) {
        if (client == null) {
            return client;
        }
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(client);
        } finally {
            tx.commit();
        }
        return client;
    }

    @Override
    public void delete(Client client) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(client);
        } finally {
            tx.commit();
        }
    }

    @Override
    public void update(Client client) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(client);
        } finally {
            tx.commit();
        }
    }

}
