package com.blc.customerInterface.graphql.instance.query.pagination;

import com.blc.customerInterface.graphql.instance.domain.Instance;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class InstancePageable extends PaginationResult<Instance> {
    public InstancePageable(Page<Instance> page) {
        super(page);
    }
}
