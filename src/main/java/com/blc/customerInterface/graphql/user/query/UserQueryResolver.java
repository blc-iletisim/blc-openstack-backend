package com.blc.customerInterface.graphql.user.query;

import com.blc.customerInterface.graphql.user.domain.User;
import com.blc.customerInterface.graphql.user.query.pagination.UserPageable;
import com.blc.customerInterface.graphql.user.repo.critera.UserCriteria;
import com.blc.customerInterface.graphql.user.service.UserService;
import com.blc.customerInterface.lib.dao.query.pagination.Pagination;
import com.blc.customerInterface.lib.dao.query.sort.SortBy;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserService userService;

    @Autowired
    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    public User user(UUID id){
        return userService.findById(id).orElse(null);
    }

    public List<User> users(UserCriteria criteria, List<SortBy> sortBy){
        return userService.filterWithSort(ObjectUtils.defaultIfNull(criteria, new UserCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy, new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }

    public UserPageable paginateUsers(Pagination pagination, UserCriteria criteria, List<SortBy> sortBy) {
        return new UserPageable(userService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria, new UserCriteria()),
                Pagination.toPageRequest(pagination, sortBy)));
    }

}
