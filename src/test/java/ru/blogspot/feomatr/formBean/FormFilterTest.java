package ru.blogspot.feomatr.formBean;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * TODO: comment
 *
 * @author iipolovinkin
 * @since 25.09.2015
 */
public class FormFilterTest {

    @Test
    public void testDefaultConstructor() throws Exception {
        FormFilter formFilter = new FormFilter();

        assertNull(formFilter.getIdFrom());
        assertNull(formFilter.getIdTo());
        assertThat(formFilter.getStartTime(), is(""));
        assertThat(formFilter.getEndTime(), is(""));
    }

    @Test
    public void testToString() throws Exception {
        FormFilter formFilter = new FormFilter();
        formFilter.setIdFrom(1L);
        formFilter.setIdTo(2L);

        assertThat(formFilter.toString(), is("FormFilter(idFrom=1, idTo=2, startTime=, endTime=)"));
    }
}