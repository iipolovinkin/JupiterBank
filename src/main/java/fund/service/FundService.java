package fund.service;

import fund.model.FundBean;

import static java.util.Optional.ofNullable;

public class FundService {
    public FundBean getById(Long fundId) {
        FundBean fundBean = new FundBean(fundId);
        return fundBean;
    }

    public FundBean getByIdOrThrow(Long fundId) {
        return ofNullable(getById(fundId))
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти фонд с указанным идентификатором " + fundId));
    }
}
