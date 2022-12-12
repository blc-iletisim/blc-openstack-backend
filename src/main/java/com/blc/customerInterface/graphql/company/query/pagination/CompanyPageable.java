package com.blc.customerInterface.graphql.company.query.pagination;

import com.blc.customerInterface.graphql.company.domain.Company;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class CompanyPageable extends PaginationResult<Company> {
    public CompanyPageable(Page<Company> page) {
        super(page);
    }
}

