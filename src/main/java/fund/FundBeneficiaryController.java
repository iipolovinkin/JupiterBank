package fund;

import fund.dto.FundBeneficiaryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController("mobileFundBeneficiaryController")
@RequestMapping("/mobile/funds/beneficiary")
@Slf4j
public class FundBeneficiaryController {

    private AtomicLong aLong = new AtomicLong();
    private Map<Long, FundBeneficiaryDto> mapBeneficiary = new HashMap<>();

    @GetMapping("/{fundBeneficiaryId}")
    public FundBeneficiaryDto getFundBeneficiary(@PathVariable Long fundBeneficiaryId) {
        log.info("getFundBeneficiary. {}", fundBeneficiaryId);
        mapBeneficiary.put(2L, new FundBeneficiaryDto());
        return mapBeneficiary.get(fundBeneficiaryId);
    }

    @PostMapping(value = "/{fundId}/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FundBeneficiaryDto save(@PathVariable Long fundId, @RequestBody @Valid FundBeneficiaryDto dto) {
        log.info("save. fundId: {}, dto: {}", fundId, dto);

        dto.setId(aLong.incrementAndGet());
        dto.setFundId(fundId);
        mapBeneficiary.put(dto.getId(), dto);
        log.info("save.end. fundId: {}, dto: {}", fundId, dto);
        return dto;
    }

    @DeleteMapping("/{fundBeneficiaryId}")
    public void deleteBeneficiary(@PathVariable Long fundBeneficiaryId) {
        log.info("deleteBeneficiary. fundBeneficiaryId: {}", fundBeneficiaryId);
        mapBeneficiary.remove(fundBeneficiaryId);
    }

    @GetMapping(value = "funds", params = {"docSeries", "docNumber", "noOnline=true"})
    public String getFundsForBeneficiaryDoc(
                                                                @RequestParam(value = "docSeries") String docSeries,
                                                                @RequestParam(value = "docNumber") String docNumber) {
        return "noOnline=true";
    }

    @GetMapping(value = "funds", params = {"!docSeries", "!docNumber"})
    public String getFunds(
                                               @RequestParam(value = "swapFrom", required = false) Long swapFrom,
                                               @RequestParam(value = "noOnline", required = false) boolean noOnline) {
        return "!docSeries";
    }
}
