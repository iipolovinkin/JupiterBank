package message.mapper;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import message.dto.Message;
import message.entity.Contract;
import message.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(RandomBeansExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringMapperContext.class)
class MessageMapperNrdTest {


    @Autowired
    private MessageMapperNrd underTest;
    @Autowired
    private MessageMapperSpb underSpb;
    @Random
    private Contract contract;

    @Test
    void testMessageMapperNrd() {
        Customer customer = contract.getCustomer();
        Message map = underTest.map(customer, contract);

        Assertions.assertEquals(customer.getCrm(), map.getCrm());
        Assertions.assertEquals(customer.getFio(), map.getFio());
        Assertions.assertEquals(customer.getDoc(), map.getDoc());
        Assertions.assertNull(map.getCorn());

    }

    @Test
    void testMessageMapperSpb() {
        Customer customer = contract.getCustomer();
        Message map = underSpb.map(customer, contract);

        Assertions.assertEquals(customer.getCrm(), map.getCrm());
        Assertions.assertEquals(customer.getFio(), map.getFio());
        Assertions.assertEquals(customer.getDoc(), map.getDoc());
        Assertions.assertEquals(" CORN " + customer.getDoc(), map.getCorn());

    }
}