package com.blc.customerInterface.graphql.pem.query.pagination;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class PemPageable extends PaginationResult<Pem> {
    public PemPageable(Page<Pem> page) {
        super(page);
    }
}
