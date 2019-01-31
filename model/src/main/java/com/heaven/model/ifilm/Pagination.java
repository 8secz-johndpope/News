package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/21.
 */

public class Pagination {
    /**
     * result : null
     * pageSize : 2
     * currentPage : 1
     * totalPages : 7
     * totalRows : 13
     * pageStartRow : 0
     * pageEndRow : 2
     * next : true
     * previous : false
     * prePage : 1
     * nextPage : 2
     */

    private Object result;
    private int pageSize;
    private int currentPage;
    private int totalPages;
    private int totalRows;
    private int pageStartRow;
    private int pageEndRow;
    private boolean next;
    private boolean previous;
    private int prePage;
    private int nextPage;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
