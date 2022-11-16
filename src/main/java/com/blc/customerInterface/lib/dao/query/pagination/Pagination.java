package com.blc.customerInterface.lib.dao.query.pagination;


import com.blc.customerInterface.lib.dao.query.sort.SortBy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class Pagination implements Serializable {
    private int page;
    private int rowsPerPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public static PageRequest toPageRequest(Pagination pagination, Collection<SortBy> sortBy) {
        return PageRequest.of(pagination.getPage(), pagination.getRowsPerPage(), Sort.by(sortBy.stream().map(SortBy::toOrder).collect(Collectors.toList())));
    }
}
