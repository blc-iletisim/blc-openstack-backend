package com.blc.customerInterface.graphql.role.query.pagination;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class RolePageable extends PaginationResult<Role> {
    public RolePageable(Page<Role> page) {
        super(page);
    }
}
