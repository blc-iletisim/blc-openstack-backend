package com.blc.customerInterface.lib.dao.query.pagination;

import org.springframework.data.domain.Page;

import java.io.Serializable;

public class PageInfo implements Serializable {
    private final long totalElements;
    private final int totalPages;
    private final int numberOfElements;
    private final int pageNumber;
    private final int pageSize;

    public PageInfo(@SuppressWarnings("rawtypes") Page page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
