package ru.blogspot.feomatr.dao.stub;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author iipolovinkin
 */
public class ClientDaoStubImpl implements ClientDAO {
    private List<Client> clients = asList(
            new Client(1L, "Alex", "NY", 22), new Client(2L, "Nick", "LA", 27),
            new Client(3L, "Ivan", "Spb", 39),
            new Client(4L, "Petr", "RnD", 13));

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public Client getById(Long id) {
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client create(Client client) {
        if (client == null) {
            return client;
        }
        Client c = getById(client.getId());
        if (c == null) {
            clients.add(client);
        }
        return client;
    }

    @Override
    public boolean delete(Client client) {
        Client c = getById(client.getId());
        if (c == null) {
            return false;
        }
        return clients.remove(c);
    }

    @Override
    public int update(Client client) {
        Client c = getById(client.getId());
        if (c == null) {
            return 0;
        }
        c.setId(client.getId());
        c.setFirstname(client.getFirstname());
        c.setAddress(client.getAddress());
        c.setAge(client.getAge());
        return 1;
    }
}
