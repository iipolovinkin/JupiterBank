package ru.blogspot.feomatr.formBean;

import org.junit.jupiter.api.Test;
import ru.blogspot.feomatr.entity.AccountNo;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO: comment
 *
 * @author iipolovinkin
 * @since 25.09.2015
 */
public class FormFilterTest {

    @Test
    void testDefaultConstructor() throws Exception {
        FormFilter formFilter = new FormFilter();

        assertNull(formFilter.getSenderAccountNo());
        assertNull(formFilter.getReceiverAccountNo());
        assertThat(formFilter.getStartTime(), is(""));
        assertThat(formFilter.getEndTime(), is(""));
    }

    @Test
    void testToString() throws Exception {
        FormFilter formFilter = new FormFilter();
        String senderAccountNo = AccountNo.generatePrivateBankAccountNo();
        String receiverAccountNo = AccountNo.generatePrivateBankAccountNo();
        formFilter.setSenderAccountNo(senderAccountNo);
        formFilter.setReceiverAccountNo(receiverAccountNo);

        assertThat(formFilter.toString(), is("FormFilter(senderAccountNo=" + senderAccountNo + ", receiverAccountNo=" + receiverAccountNo
		         + ", startTime=, endTime=)"));
    }
}