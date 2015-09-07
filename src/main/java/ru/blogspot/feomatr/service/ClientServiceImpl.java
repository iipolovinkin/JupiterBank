package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    private ClientDAO clientDAO;

    @Override
    public Client saveClient(Client client) throws ServiceException {
        try {
            return clientDAO.create(client);
        } catch (DAOException e) {
            log.error("Cannot save client", e);
            throw new ServiceException("Cannot save client", e);
        }
    }

    @Override
    public void updateClient(Client client) throws ServiceException {
        try {
            clientDAO.update(client);
        } catch (DAOException e) {
            log.error("Cannot update client", e);
            throw new ServiceException("Cannot update client", e);
        }
    }

    @Override
    public List<Client> getAllClients() throws ServiceException {
        try {
            return clientDAO.getAll();
        } catch (DAOException e) {
            log.error("Cannot get all clients", e);
            throw new ServiceException("Cannot get all clients", e);

        }
    }

    @Override
    public Client getClientById(Long id) throws ServiceException {
        try {
            return clientDAO.getById(id);
        } catch (DAOException e) {
            log.error("Cannot get client by id", e);
            throw new ServiceException("Cannot get client by id", e);

        }
    }

}
