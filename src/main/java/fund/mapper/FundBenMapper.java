package fund.mapper;

import fund.dto.FundBeneficiaryDto;
import fund.model.FundBeneficiaryBean;
import fund.service.FundBeneficiaryService;
import fund.service.FundService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FundBenMapper {
    @Autowired
    FundBeneficiaryService service;
    @Autowired
    FundService fundService;

    @Mapping(target = "id", source = "fundBeneficiaryId")
    public abstract FundBeneficiaryDto toDto(FundBeneficiaryBean data);

    public abstract List<FundBeneficiaryDto> beneficiariesToDto(List<FundBeneficiaryBean> data);

    @Mapping(target = "fundId", ignore = true)
    public abstract FundBeneficiaryBean toBean(FundBeneficiaryDto dto, Long myFundId);

    @ObjectFactory
    public FundBeneficiaryBean getBeneficiary(FundBeneficiaryDto dto, Long myFundId, @TargetType Class<FundBeneficiaryBean> entityClass) {
        FundBeneficiaryBean beneficiary = getBeneficiary(dto.getId());
        setFundIfNeed(beneficiary, myFundId);
        return beneficiary;
    }

    private FundBeneficiaryBean getBeneficiary(Long beneficiaryId) {
        return beneficiaryId != null
                ? service.getByIdOrThrow(beneficiaryId)
                : new FundBeneficiaryBean();
    }

    private void setFundIfNeed(FundBeneficiaryBean beneficiary, Long fundId) {
        if (beneficiary.getFundId() == null) {
            beneficiary.setFundId(fundService.getByIdOrThrow(fundId).getFundId());
        }
    }

}
