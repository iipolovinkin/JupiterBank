package message.mapper;

import message.dto.Message;
import message.entity.Contract;
import message.entity.Customer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = FormMapperSpb.class
)
public interface MessageMapperSpb {

    @Mapping(target = "form", source = "contract")
    Message map(Customer customer, Contract contract);

    @AfterMapping
    default void fillCorn(Customer customer, @MappingTarget Message message) {
        message.setCorn(" CORN " + customer.getDoc());
    }
}
