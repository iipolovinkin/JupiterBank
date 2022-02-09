package ru.blogspot.feomatr.formBean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author iipolovinkin
 * @since 16.09.2015
 */
public class PaginatorTest {

    private Paginator paginator;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testGetPaginatorForFirstPage() throws Exception {
        paginator = new Paginator(1, 10, 23);

        assertThat(paginator.getPrevPage(), is(-1));
        assertThat(paginator.getNextPage(), is(2));
    }

    @Test
    void testGetPaginatorForSecondPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getPageCount(), is(3));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    void testIncreasePage() throws Exception {
        paginator = new Paginator(1, 10, 23);

        paginator.increase();

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getCurrentPage(), is(2));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    void testIncreaseToLastPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        paginator.increase();

        assertThat(paginator.getPrevPage(), is(2));
        assertThat(paginator.getCurrentPage(), is(3));
        assertThat(paginator.getNextPage(), is(-1));
    }

    @Test
    void testIncreaseLastPage() throws Exception {
        paginator = new Paginator(3, 10, 23);

        paginator.increase();

        assertThat(paginator.getPrevPage(), is(2));
        assertThat(paginator.getCurrentPage(), is(3));
        assertThat(paginator.getNextPage(), is(-1));
    }

    @Test
    void testDecreasePage() throws Exception {
        paginator = new Paginator(3, 10, 23);

        paginator.decrease();

        assertThat(paginator.getPrevPage(), is(1));
        assertThat(paginator.getCurrentPage(), is(2));
        assertThat(paginator.getNextPage(), is(3));
    }

    @Test
    void testDecreaseToFirstPage() throws Exception {
        paginator = new Paginator(2, 10, 23);

        paginator.decrease();

        assertThat(paginator.getPrevPage(), is(-1));
        assertThat(paginator.getCurrentPage(), is(1));
        assertThat(paginator.getNextPage(), is(2));
    }

    @Test
    void testDecreaseFirstPage() throws Exception {
        paginator = new Paginator(1, 10, 23);

        paginator.decrease();

        assertThat(paginator.getPrevPage(), is(-1));
        assertThat(paginator.getCurrentPage(), is(1));
        assertThat(paginator.getNextPage(), is(2));
    }

    @Test
    void testGetIndexesForFirstPage() throws Exception {
        int actualFirstIndex = new Paginator(1, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(1, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(0));
        assertThat(actualLastIndex, is(10));
    }

    @Test
    void testGetIndexesForMiddlePage() throws Exception {
        int actualFirstIndex = new Paginator(2, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(2, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(10));
        assertThat(actualLastIndex, is(20));
    }

    @Test
    void testGetIndexesForLastPage() throws Exception {
        int actualFirstIndex = new Paginator(3, 10, 23).getFirstIndex();
        int actualLastIndex = new Paginator(3, 10, 23).getLastIndex();

        assertThat(actualFirstIndex, is(20));
        assertThat(actualLastIndex, is(23));
    }

    @Test
    void testGetPaginatorForZeroPageCount() throws Exception {
        paginator = new Paginator(1, 0, 23);

        assertThat(paginator.getSize(), is(-1));
    }

    @Test
    void testGetPaginatorForZeroSize() throws Exception {
        paginator = new Paginator(1, 0, 23);

        assertThat(paginator.getSize(), is(-1));
    }


    @Test
    void testGetPaginatorForCurrentPageGreaterPageCount() throws Exception {
        paginator = new Paginator(10, 5, 23);

        assertThat(paginator.getSize(), is(-1));
    }

}