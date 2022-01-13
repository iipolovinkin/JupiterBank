package fund.service;

import fund.model.FundBeneficiaryBean;

public class FundBeneficiaryService {
    public FundBeneficiaryBean getByIdOrThrow(Long beneficiaryId) {
        FundBeneficiaryBean bean = new FundBeneficiaryBean();
        bean.setFundBeneficiaryId(beneficiaryId);
        return bean;
    }
//    public FundBeneficiaryBean create(Long beneficiaryId) {
//        FundBeneficiaryBean bean = new FundBeneficiaryBean();
//        bean.setFundBeneficiaryId(beneficiaryId);
//        return bean;
//    }
}
