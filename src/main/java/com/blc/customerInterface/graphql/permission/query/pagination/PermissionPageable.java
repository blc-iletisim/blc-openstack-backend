package com.blc.customerInterface.graphql.permission.query.pagination;


import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class PermissionPageable extends PaginationResult<Permission> {
    public PermissionPageable(Page<Permission> page) {
        super(page);
    }
}
