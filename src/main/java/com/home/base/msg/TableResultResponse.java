package com.home.base.msg;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-14 22:40
 */
public class TableResultResponse<T> {
    int total;
    int totalPages;
    int page;
    int pageSize;
    List<T> rows;

    public TableResultResponse(int total,int page,int pageSize, List<T> rows) {
        this.total = total;
        if ((total % pageSize) == 0) {
            totalPages = total / pageSize;
        } else {
            totalPages = total / pageSize + 1;
        }
        this.page=page;
        this.pageSize=pageSize;
        this.rows = rows;
    }

    public TableResultResponse() {
    }

    TableResultResponse<T> total(int total){
        this.total = total;
        return this;
    }
    TableResultResponse<T> total(List<T> rows){
        this.rows = rows;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
