package com.blc.customerInterface.lib.dao.query.pagination;


import com.blc.customerInterface.lib.dao.domain.BaseDomain;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationResult<T extends BaseDomain> {
    private final Page<T> page;

    public PaginationResult(Page<T> page) {
        this.page = page;
    }

    public PageInfo getPageInfo() {
        return new PageInfo(page);
    }

    public List<T> getContent() {
        return page.getContent();
    }
}
