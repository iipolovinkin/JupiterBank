package fund.mapper;

import fund.dto.FundBeneficiaryDto;
import fund.model.FundBeneficiaryBean;
import fund.service.FundBeneficiaryService;
import fund.service.FundService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class FundBeneficiaryMapper {
    @Autowired
    private FundBeneficiaryService service;
    @Autowired
    private FundService fundService;

    public List<FundBeneficiaryDto> toDto(List<FundBeneficiaryBean> searchByFund) {
        return searchByFund.stream().map(this::toDto).collect(Collectors.toList());
    }

    public FundBeneficiaryDto toDto(FundBeneficiaryBean fundBeneficiaryBean) {
        FundBeneficiaryDto dto = new FundBeneficiaryDto();

        dto.setId(fundBeneficiaryBean.getFundBeneficiaryId());
        dto.setFundId(fundBeneficiaryBean.getFundId());
        dto.setDocNumber(fundBeneficiaryBean.getDocNumber());
        dto.setDocSeries(fundBeneficiaryBean.getDocSeries());
        dto.setComment(fundBeneficiaryBean.getComment());

        return dto;
    }

    public FundBeneficiaryBean toEntity(FundBeneficiaryDto dto, Long fundId) {
        FundBeneficiaryBean beneficiary = getBeneficiary(dto.getId());

        setFundIfNeed(beneficiary, fundId);

        beneficiary.setDocSeries(dto.getDocSeries());
        beneficiary.setDocNumber(dto.getDocNumber());
        beneficiary.setComment(dto.getComment());

        return beneficiary;
    }

    private void setFundIfNeed(FundBeneficiaryBean beneficiary, Long fundId) {
        if (beneficiary.getFundId() == null) {
            beneficiary.setFundId(fundService.getByIdOrThrow(fundId).getFundId());
        }
    }

    private FundBeneficiaryBean getBeneficiary(Long beneficiaryId) {
        return beneficiaryId != null
                ? service.getByIdOrThrow(beneficiaryId)
                : new FundBeneficiaryBean();
    }
}
