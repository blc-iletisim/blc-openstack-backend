package com.blc.customerInterface.graphql.user.query.pagination;


import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.lib.dao.query.pagination.PaginationResult;
import org.springframework.data.domain.Page;

public class UserPageable extends PaginationResult<User> {
    public UserPageable(Page<User> page) {
        super(page);
    }
}
