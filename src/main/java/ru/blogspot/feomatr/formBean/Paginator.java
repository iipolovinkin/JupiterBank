package ru.blogspot.feomatr.formBean;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class realized pagination.
 *
 * @author iipolovinkin
 * @since 16.09.2015
 */
@Setter
@Getter
public class Paginator {

    private static final Logger log = LoggerFactory.getLogger(Paginator.class);

    public static final int ROWS_COUNT_PER_PAGE = 10;

    private int size;
    private int pageCount;
    private int countPerPage;

    private int currentPage;
    private int prevPage;
    private int nextPage;

    public Paginator(int currentPage, int count, int size) {
        this.currentPage = currentPage;
        this.countPerPage = count;
        this.size = size;

        if (count < 1 || currentPage < 1) {
            this.size = -1;
            return;
        }
        pageCount = size / count;
        pageCount = size % count > 0 ? ++pageCount : pageCount;

        if (currentPage > pageCount) {
            this.size = -1;
            return;
        }

        prevPage = currentPage > 1 ? currentPage - 1 : -1;
        nextPage = currentPage < pageCount ? currentPage + 1 : -1;
    }

    public void increase() {
        if (currentPage == pageCount) {
            return;
        }
        ++currentPage;
        prevPage = currentPage - 1;
        nextPage = currentPage == pageCount ? -1 : currentPage + 1;
    }

    public void decrease() {
        if (currentPage == 1) {
            return;
        }
        --currentPage;
        nextPage = currentPage + 1;
        prevPage = currentPage == 1 ? -1 : currentPage - 1;
    }

    public int getFirstIndex() {
        return (currentPage - 1) * countPerPage;
    }

    public int getLastIndex() {
        int toIndex = currentPage * countPerPage;
        toIndex = toIndex > size ? size : toIndex;
        return toIndex;
    }
}
