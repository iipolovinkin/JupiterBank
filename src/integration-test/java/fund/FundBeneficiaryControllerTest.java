package fund;

import com.fasterxml.jackson.databind.ObjectMapper;
import fund.dto.FundBeneficiaryDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:controllerITContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
class FundBeneficiaryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getFundBeneficiary1() throws Exception {
        mockMvc.perform(get("/mobile/funds/beneficiary/1")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getFundBeneficiary2() throws Exception {
        mockMvc.perform(get("/mobile/funds/beneficiary/2")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        Long fundId = 1L;
        FundBeneficiaryDto fundBeneficiaryDto = new FundBeneficiaryDto();
        fundBeneficiaryDto.setComment("Ivanov Ivan Ivanovich");
        fundBeneficiaryDto.setDocNumber("001200");
        fundBeneficiaryDto.setDocSeries("0021");

        String jsonContent = objectMapper.writeValueAsString(fundBeneficiaryDto);


        mockMvc.perform(post("/mobile/funds/beneficiary/1/save")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteFundBeneficiary1() throws Exception {
        mockMvc.perform(delete("/mobile/funds/beneficiary/1")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteFundBeneficiary2() throws Exception {
        mockMvc.perform(delete("/mobile/funds/beneficiary/2")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}