package fund.model;

import lombok.Data;

@Data
public class FundBeneficiaryBean {
    private Long fundBeneficiaryId;
    private Long fundId;
    private String docSeries;
    private String docNumber;
    private String comment;
}
