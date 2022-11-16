package com.blc.customerInterface.graphql.role.query;

import com.blc.customerInterface.graphql.role.domain.Role;
import com.blc.customerInterface.graphql.role.query.pagination.RolePageable;
import com.blc.customerInterface.graphql.role.repo.criteria.RoleCriteria;
import com.blc.customerInterface.graphql.role.service.RoleService;
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
public class RoleQueryResolver implements GraphQLQueryResolver {
    private final RoleService roleService;

    @Autowired
    public RoleQueryResolver(RoleService roleService) {
        this.roleService = roleService;
    }

    public Role role(UUID id){
        return roleService.findById(id).orElse(null);
    }

    public List<Role> roles(RoleCriteria criteria, List<SortBy> sortBy){
        return roleService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new RoleCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public RolePageable paginateRoles(Pagination pagination, RoleCriteria criteria, List<SortBy> sortBy){
        return new RolePageable(roleService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new RoleCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));
    }
}
