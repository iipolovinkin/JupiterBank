/**
 *
 */
package ru.blogspot.feomatr.dao.stub;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iipolovinkin
 */
public class ClientDaoStubImpl implements ClientDAO {
    List<Client> clients = new ArrayList<Client>(Arrays.asList(new Client[]{
            new Client(1L, "Alex", "NY", 22), new Client(2L, "Nick", "LA", 27),
            new Client(3L, "Ivan", "Spb", 39),
            new Client(4L, "Petr", "RnD", 13)}));

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.ClientDAO#getAllClients()
     */
    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.ClientDAO#getById(java.lang.Long)
     */
    @Override
    public Client getById(Long id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#create(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public Client create(Client cl) {
        if (cl == null)
            return cl;
        Client c = getById(cl.getId());
        if (c == null)
            clients.add(cl);
        return cl;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#delete(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public boolean delete(Client cl) {
        Client c = getById(cl.getId());
        if (c == null) {
            return false;
        }
        return clients.remove(c);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#update(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public int update(Client cl) {
        Client c = getById(cl.getId());
        if (c == null) {
            return 0;
        }
        c.setId(cl.getId());
        c.setFirstname(cl.getFirstname());
        c.setAddress(cl.getAddress());
        c.setAge(cl.getAge());
        return 1;
    }
}
