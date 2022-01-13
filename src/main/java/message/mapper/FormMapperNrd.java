package message.mapper;

import message.dto.Form;
import message.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface FormMapperNrd {
    @Mapping(target = "depoCode", constant = "NRD_CODE" )
    Form toForm(Contract contract);
}
