package com.my.test.service.impl;

import java.io.Serializable;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname Pagination <br>
 * @description TODO <br>
 * @created 2018/11/5 14:21 <br>
 * ********************************************************.<br>
 */
public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int PAGE_SIZE = 15;
    protected boolean isPage;
    protected Integer currentPage = Integer.valueOf(1);
    protected Integer pageSize = Integer.valueOf(15);
    protected Integer totalPage = Integer.valueOf(0);
    protected Integer totalRecord = Integer.valueOf(0);

    public Pagination() {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer value) {
        this.totalPage = value;
    }

    public Integer getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
        if(this.currentPage.intValue() < 1) {
            this.currentPage = Integer.valueOf(1);
        }

        if(this.pageSize.intValue() < 1) {
            this.pageSize = Integer.valueOf(15);
        }

        int totalPage = totalRecord.intValue() % this.pageSize.intValue() == 0?totalRecord.intValue() / this.pageSize.intValue():totalRecord.intValue() / this.pageSize.intValue() + 1;
        this.totalPage = Integer.valueOf(totalPage);
        if(this.currentPage.intValue() > this.totalPage.intValue()) {
            this.currentPage = this.totalPage;
        }

    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer value) {
        this.currentPage = value;
    }

    public boolean isFirst() {
        return this.currentPage.intValue() <= 1;
    }

    public boolean isLast() {
        return this.currentPage.intValue() >= this.totalPage.intValue();
    }

    public int getPreviousPage() {
        return this.currentPage.intValue() > 1?this.currentPage.intValue() - 1:1;
    }

    public int getNextPage() {
        return this.currentPage.intValue() < this.totalPage.intValue()?this.currentPage.intValue() + 1:this.totalPage.intValue();
    }

    public int getIndexFrom() {
        return this.pageSize.intValue() * (this.currentPage.intValue() - 1) + 1;
    }

    public int getIndexTo() {
        return Math.min(this.pageSize.intValue() * this.currentPage.intValue(), this.totalRecord.intValue());
    }

    public boolean isPage() {
        return this.isPage;
    }

    public void setPage(boolean isPage) {
        this.isPage = isPage;
    }

    @Override
    public String toString() {
        return "Pagination [isPage=" + this.isPage + ", currentPage=" + this.currentPage + ", pageSize=" + this.pageSize + ", totalPage=" + this.totalPage + ", totalRecord=" + this.totalRecord + "]";
    }
}
