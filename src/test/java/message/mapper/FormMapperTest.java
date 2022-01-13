package message.mapper;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import message.dto.Form;
import message.entity.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(RandomBeansExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringMapperContext.class)
class FormMapperTest {
    @Autowired
    private FormMapperNrd underTest;
    @Random
    private Contract contract;

    @Test
    void testFormMapper() {
        Form form = underTest.toForm(contract);

        Assertions.assertEquals(contract.getContractNumber(), form.getContractNumber());
        Assertions.assertEquals(contract.getCounterPartyNumber(), form.getCounterPartyNumber());
    }
}