package com.blc.customerInterface.graphql.flavor.query.pagination;

import com.blc.customerInterface.graphql.flavor.domain.Flavor;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;


public class FlavorPageable extends PaginationResult<Flavor> {
    public FlavorPageable(Page<Flavor> page) {
        super(page);
    }
}
