package com.dxj.skc.util;

import java.util.List;

public class PageInfoUtil<T> {

    /**
     * 页码
     */
    private int pageNum = 1;
    /**
     * 返回条数
     */
    private int pageSize = 10;
    /**
     * 总记录数
     */
    private long total = -1;
    /**
     * 总页数
     */
    private int pages = -1;
    /**
     * 结果集
     */
    protected List<T> list;

    public PageInfoUtil() {
    }

    public PageInfoUtil(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public PageInfoUtil(Integer pageNum) {
        if (pageNum != null) {
            this.pageNum = pageNum;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        setPageSize(this.pageSize);
    }

    private int getPageSize() {
        return pageSize;
    }

    private void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        computeTotalPage();
    }

    private long getTotal() {
        return total;
    }

    public int getPages() {
        return pages;
    }

    public void setTotal(long totalRecord) {
        this.total = totalRecord;
        computeTotalPage();
    }

    public int getStartIndex() {
        return (pageNum - 1) * pageSize;
    }

    private void computeTotalPage() {
        if (getPageSize() > 0 && getTotal() > -1) {
            this.pages = (int) (getTotal() % getPageSize() == 0 ? getTotal()
                    / getPageSize()
                    : getTotal() / getPageSize() + 1);
        }
        if (this.pages == 0) {
            this.pages = 1;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> resultList) {
        this.list = resultList;
    }

}
