package com.blc.customerInterface.graphql.permission.query;

import com.blc.customerInterface.graphql.permission.domain.Permission;
import com.blc.customerInterface.graphql.permission.query.pagination.PermissionPageable;
import com.blc.customerInterface.graphql.permission.repo.criteria.PermissionCriteria;
import com.blc.customerInterface.graphql.permission.service.PermissionService;
import com.blc.customerInterface.lib.dao.query.pagination.Pagination;
import com.blc.customerInterface.lib.dao.query.sort.SortBy;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PermissionQueryResolver implements GraphQLQueryResolver {

    private final PermissionService permissionService;

    @Autowired
    public PermissionQueryResolver(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public Permission permission(UUID id){
        return permissionService.findById(id).orElse(null);
    }

    public List<Permission> permissions(PermissionCriteria criteria, List<SortBy> sortBy){
        return permissionService.filterWithSort(ObjectUtils.defaultIfNull(criteria,new PermissionCriteria()),
                Sort.by(ObjectUtils.defaultIfNull(sortBy,new ArrayList<SortBy>())
                        .stream()
                        .map(SortBy::toOrder)
                        .collect(Collectors.toList())));
    }
    public PermissionPageable paginatePermissions(Pagination pagination, PermissionCriteria criteria, List<SortBy> sortBy){
        return new PermissionPageable(permissionService.filterWithPaginate(ObjectUtils.defaultIfNull(criteria,new PermissionCriteria()),
                Pagination.toPageRequest(pagination,sortBy)));
    }
}
