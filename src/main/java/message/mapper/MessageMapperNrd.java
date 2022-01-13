package message.mapper;

import message.dto.Message;
import message.entity.Contract;
import message.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = FormMapperNrd.class
)
public interface MessageMapperNrd {

    @Mapping(target = "form", source = "contract")
    Message map(Customer customer, Contract contract);
}
