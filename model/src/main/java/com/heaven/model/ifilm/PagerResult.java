package com.heaven.model.ifilm;

import java.util.List;

/**
 * Created by leo on 2015/6/2.
 */
public class PagerResult<T> {

    private List<T> list;
    private PagerModel page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagerModel getPage() {
        return page;
    }

    public void setPage(PagerModel page) {
        this.page = page;
    }


}
