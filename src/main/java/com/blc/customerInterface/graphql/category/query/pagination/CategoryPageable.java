package com.blc.customerInterface.graphql.category.query.pagination;

import com.blc.customerInterface.graphql.category.domain.Category;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class CategoryPageable extends PaginationResult<Category> {
    public CategoryPageable(Page<Category> page) {
        super(page);
    }
}
