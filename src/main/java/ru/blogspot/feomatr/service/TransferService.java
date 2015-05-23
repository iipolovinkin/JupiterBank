package ru.blogspot.feomatr.service;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
public interface TransferService {


    boolean transfer(Broker broker);

    boolean transferTo(Broker broker);

    boolean transferFrom(Broker broker);

}
