package fund.model;

public class FundBean {
    private final Long fundId;

    public FundBean(Long fundId) {
        this.fundId = fundId;
    }

    public Long getFundId() {
        return fundId;
    }
}
