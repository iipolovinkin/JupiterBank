package ru.blogspot.feomatr.formBean;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author iipolovinkin
 * @since 16.09.2015
 */
public class PaginatorTest {

    private Paginator paginator;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetPaginatorForFirstPage() throws Exception {
        paginator = new Paginator(1, 10, 23);

        assertThat(paginator.getPrevPage(), is(-1));
        assertThat(paginator.getNextPage(), is(2));
    }

    @Test
    public void testGetPaginatorForSecondPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getPageCount(), is(3));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    public void testIncreasePage() throws Exception {
        paginator = new Paginator(1, 10, 23);

        paginator.increase();

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getCurrentPage(), is(2));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    public void testIncreaseToLastPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        paginator.increase();

        assertThat(paginator.getPrevPage(), is(2));
        assertThat(paginator.getCurrentPage(), is(3));
        assertThat(paginator.getNextPage(), is(-1));
    }

    @Test
    public void testDecreasePage() throws Exception {
        paginator = new Paginator(3, 10, 23);

        paginator.decrease();

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getCurrentPage(), is(2));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    public void testDecreaseToFirstPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        paginator.decrease();

        assertThat(paginator.getPrevPage(), is(-1));
        assertThat(paginator.getCurrentPage(), is(1));
        assertThat(paginator.getNextPage(), is(2));
    }

    @Test
    public void testGetIndexesForFirstPage() throws Exception {
        int actualFirstIndex = new Paginator(1, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(1, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(0));
        assertThat(actualLastIndex, is(10));
    }

    @Test
    public void testGetIndexesForMiddlePage() throws Exception {
        int actualFirstIndex = new Paginator(2, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(2, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(10));
        assertThat(actualLastIndex, is(20));
    }

    @Test
    public void testGetIndexesForLastPage() throws Exception {
        int actualFirstIndex = new Paginator(3, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(3, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(20));
        assertThat(actualLastIndex, is(23));
    }
}