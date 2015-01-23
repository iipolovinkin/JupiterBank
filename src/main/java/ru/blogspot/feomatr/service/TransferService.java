/**
 *
 */
package ru.blogspot.feomatr.service;

import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
public interface TransferService {


    public boolean transfer(Broker broker);

    public boolean transferTo(Broker broker);

    public boolean transferFrom(Broker broker);

}
