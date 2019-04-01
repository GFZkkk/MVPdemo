package com.example.gaofengze.demo.base.bean;

import java.util.List;

/**
 * Created by gaofengze on 2019/3/14
 */
public class PageList<T> {
    private List<T> list;
    private int nextPage;
    private boolean isLastPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
