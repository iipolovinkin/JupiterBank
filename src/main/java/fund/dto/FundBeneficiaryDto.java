package fund.dto;

import lombok.Data;

@Data
public class FundBeneficiaryDto {
    Long id;
    Long fundId;
    String docSeries;
    String docNumber;
    String comment;
}
