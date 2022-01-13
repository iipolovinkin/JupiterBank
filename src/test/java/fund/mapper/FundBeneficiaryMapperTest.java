package fund.mapper;

import fund.dto.FundBeneficiaryDto;
import fund.model.FundBean;
import fund.model.FundBeneficiaryBean;
import fund.service.FundBeneficiaryService;
import fund.service.FundService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FundBeneficiaryMapperTest {

    @Mock
    private FundBeneficiaryService service;
    @Mock
    private FundService fundService;
    @InjectMocks
    private FundBeneficiaryMapper fundBeneficiaryMapper = new FundBeneficiaryMapper();
    @InjectMocks
    private FundBenMapper fundBenMapper = new FundBenMapperImpl();

    @Test
    void toDto() {
        FundBeneficiaryBean bean = newFundBeneficiaryBean(3L, 4L, "123", "23", "3");
        FundBeneficiaryDto fundBeneficiaryDto = fundBeneficiaryMapper.toDto(bean);
        FundBeneficiaryDto dto = fundBenMapper.toDto(bean);

        Assertions.assertEquals(fundBeneficiaryDto, dto);
    }

    @NotNull
    private FundBeneficiaryBean newFundBeneficiaryBean(long id, long fundId, String series, String number, String comment) {
        FundBeneficiaryBean bean = new FundBeneficiaryBean();
        bean.setComment(comment);
        bean.setDocSeries(series);
        bean.setDocNumber(number);
        bean.setFundBeneficiaryId(id);
        bean.setFundId(fundId);
        return bean;
    }

    @Test
    void testToDto() {
        FundBeneficiaryBean bean1 = newFundBeneficiaryBean(3L, 4L, "123", "23", "3");
        FundBeneficiaryBean bean2 = newFundBeneficiaryBean(43L, 45L, "1222", "2223", "33");
        List<FundBeneficiaryBean> list = Arrays.asList(bean1, bean2);
        List<FundBeneficiaryDto> dtoList = fundBeneficiaryMapper.toDto(list);
        List<FundBeneficiaryDto> dtoList2 = fundBenMapper.beneficiariesToDto(list);

        Assertions.assertEquals(dtoList, dtoList2);
    }

    @Test
    void toEntityFoundedInDataBase() {
        Long fundId = 17L;

        FundBeneficiaryDto dto = new FundBeneficiaryDto();
        dto.setComment("fghdfghdfhbdfghbdfghdfh");
        dto.setDocSeries("2123");
        dto.setDocNumber("111111");
        dto.setId(4533L);

        Mockito.when(service.getByIdOrThrow(4533L))
                .thenReturn(newFundBeneficiaryBean(4533L, fundId, "21", "22", "comment"));


        FundBeneficiaryBean fundBeneficiaryBean2 = fundBenMapper.toBean(dto, fundId);
        FundBeneficiaryBean fundBeneficiaryBean1 = fundBeneficiaryMapper.toEntity(dto, fundId);
        System.out.println("fundBeneficiaryBean1 = " + fundBeneficiaryBean1);

        Assertions.assertEquals(fundBeneficiaryBean1, fundBeneficiaryBean2);
    }

    @Test
    void toEntity() {
        Long fundId = 17L;

        FundBeneficiaryDto dto = new FundBeneficiaryDto();
        dto.setComment("fghdfghdfhbdfghbdfghdfh");
        dto.setDocSeries("2123");
        dto.setDocNumber("111111");

//        Mockito.when(service.getByIdOrThrow(4533L)).thenThrow(new RuntimeException());
        FundBean value = new FundBean(fundId);
//        Mockito.when(fundService.getById(fundId)).thenReturn(value);
        Mockito.when(fundService.getByIdOrThrow(fundId)).thenReturn(value);


        FundBeneficiaryBean fundBeneficiaryBean2 = fundBenMapper.toBean(dto, fundId);

        System.out.println("fundBeneficiaryBean2 = " + fundBeneficiaryBean2);
        FundBeneficiaryBean fundBeneficiaryBean1 = fundBeneficiaryMapper.toEntity(dto, fundId);

        Assertions.assertEquals(fundBeneficiaryBean1, fundBeneficiaryBean2);
    }

}