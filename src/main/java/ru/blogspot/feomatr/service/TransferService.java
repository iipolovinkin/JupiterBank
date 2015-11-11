package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Broker;

/**
 * @author iipolovinkin
 */
public interface TransferService {

    boolean transfer(Broker broker) throws ServiceException;

    boolean transferTo(Broker broker) throws ServiceException;

    boolean transferFrom(Broker broker) throws ServiceException;

}
